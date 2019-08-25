
#include "LivePush.h"
#include "ErrorCode.h"

LivePush::LivePush(const char *liveUrl, PushJniCall *pJniCall) {
    this->liveUrl = (char *) malloc(strlen(liveUrl) + 1);
    strcpy(this->liveUrl, liveUrl);
    this->pJniCall = pJniCall;
    pPacketQueue = new PacketQueue();
}

LivePush::~LivePush() {
    if (pRtmp != NULL) {
        RTMP_Close(pRtmp);
        free(pRtmp);
        pRtmp = NULL;
    }

    if (liveUrl != NULL) {
        free(liveUrl);
        liveUrl = NULL;
    }

    if (pPacketQueue != NULL) {
        delete (pPacketQueue);
        pPacketQueue = NULL;
    }
}


void *initConnectFun(void *context) {

    LivePush *pLivePush = (LivePush *)context;
    // 1. 创建 RTMP
    pLivePush->pRtmp = RTMP_Alloc();
    // 2. 初始化
    RTMP_Init(pLivePush->pRtmp);
    // 3. 设置参数，连接的超时时间等
    pLivePush->pRtmp->Link.timeout = 5;
    pLivePush->pRtmp->Link.lFlags |= RTMP_LF_LIVE;
    RTMP_SetupURL(pLivePush->pRtmp, pLivePush->liveUrl);
    RTMP_EnableWrite(pLivePush->pRtmp);
    // 开始连接
    if (!RTMP_Connect(pLivePush->pRtmp, NULL)) {
        // 回调到 java 层，这个错误一般是手机没网络，或者服务器没打开
        LOGE("rtmp connect error,url = %s",pLivePush->liveUrl);
        pLivePush->pJniCall->callConnectError(THREAD_CHILD, INIT_RTMP_CONNECT_ERROR_CODE,
                                              "rtmp connect error");
        return (void *) INIT_RTMP_CONNECT_ERROR_CODE;
    }

    if (!RTMP_ConnectStream(pLivePush->pRtmp, 0)) {
        // 回调到 java 层
        LOGE("rtmp connect stream error");
        pLivePush->pJniCall->callConnectError(THREAD_CHILD, INIT_RTMP_CONNECT_STREAM_ERROR_CODE,
                                              "rtmp connect stream error");
        return (void *) INIT_RTMP_CONNECT_STREAM_ERROR_CODE;
    }
    LOGW("rtmp 连接成功，回调给java层");
    pLivePush->pJniCall->callConnectSuccess(THREAD_CHILD);
    pLivePush->startTime = RTMP_GetTime();
    while (pLivePush->isPushing) {
        // 从队列读，不断的往流媒体服务器上推（生产者消费者模式）
        RTMPPacket *pPacket = pLivePush->pPacketQueue->pop();
        if (pPacket != NULL) {
            RTMP_SendPacket(pLivePush->pRtmp, pPacket, 1);
            RTMPPacket_Free(pPacket);
            free(pPacket);
        }
    }

    LOGE("推流结束，线程停止了");
    return 0;
}


void LivePush::initConnect() {
    // 创建一个线程，去连接推流服务器和推流
    pthread_create(&initConnectTid, NULL, initConnectFun, this);

}

void LivePush::pushSpsPps(jbyte *spsData, jint spsLen, jbyte *ppsData, jint ppsLen) {
    // frame type : 1关键帧，2 非关键帧 (4bit)
    // CodecID : 7表示 AVC (4bit)  , 与 frame type 组合起来刚好是 1 个字节  0x17
    // fixed : 0x00 0x00 0x00 0x00 (4byte)  -固定的
    // configurationVersion  (1byte)  0x01版本  -固定的
    // AVCProfileIndication  (1byte)  sps[1] profile
    // profile_compatibility (1byte)  sps[2] compatibility
    // AVCLevelIndication    (1byte)  sps[3] Profile level
    // lengthSizeMinusOne    (1byte)  0xff   包长数据所使用的字节数，传最大

    // sps + pps 的数据
    // sps number            (1byte)  0xe1   sps 个数
    // sps data length       (2byte)  sps 长度
    // sps data                       sps 的内容
    // pps number            (1byte)  0x01   pps 个数
    // pps data length       (2byte)  pps 长度
    // pps data                       pps 的内容

    // 数据的长度（大小） = sps 大小 + pps 大小 + 16字节
    int bodySize = spsLen + ppsLen + 16;
    // 构建 RTMPPacket
    RTMPPacket *pPacket = (RTMPPacket *) malloc(sizeof(RTMPPacket));
    RTMPPacket_Alloc(pPacket, bodySize);
    RTMPPacket_Reset(pPacket);

    // 构建 body 按上面的一个一个开始赋值
    char *body = pPacket->m_body;
    int index = 0;
    // CodecID : 7表示 AVC (4bit)  , 与 frame type 组合起来刚好是 1 个字节  0x17
    body[index++] = 0x17;
    // fixed : 0x00 0x00 0x00 0x00 (4byte)
    body[index++] = 0x00;
    body[index++] = 0x00;
    body[index++] = 0x00;
    body[index++] = 0x00;
    // configurationVersion  (1byte)  0x01版本
    body[index++] = 0x01;
    // AVCProfileIndication  (1byte)  sps[1] profile
    body[index++] = spsData[1];  ///sps第1个字节
    // profile_compatibility (1byte)  sps[2] compatibility
    body[index++] = spsData[2];  ///sps第2个字节
    // AVCLevelIndication    (1byte)  sps[3] Profile level
    body[index++] = spsData[3];  /// ///sps第3个字节
    // lengthSizeMinusOne    (1byte)  0xff   包长数据所使用的字节数
    body[index++] = 0xff;
    // sps + pps 的数据
    // sps number            (1byte)  0xe1   sps 个数
    body[index++] = 0xe1;
    // sps data length       (2byte)  sps 长度
    body[index++] = (spsLen >> 8) & 0xFF; ///sps长度用两个字节表示，第一个字节表示高八位，256 -> 0000 0001 0000 0000 右移8位 -> 0000 0001
    body[index++] = spsLen & 0xFF; ///第二个字节放低八位，比如256，如果只放一个字节，前面的1会被干掉，变成 0000 0000
    // sps data                       sps 的内容
    memcpy(&body[index], spsData, spsLen);  ///拷贝sps到body
    index += spsLen;
    // pps number            (1byte)  0x01   pps 个数
    body[index++] = 0x01;
    // pps data length       (2byte)  pps 长度
    body[index++] = (ppsLen >> 8) & 0xFF;
    body[index++] = ppsLen & 0xFF;
    // pps data                       pps 的内容
    memcpy(&body[index], ppsData, ppsLen); ///拷贝pps到body

    // RTMPPacket 设置一些信息
    pPacket->m_hasAbsTimestamp = 0;
    pPacket->m_nTimeStamp = 0;
    pPacket->m_headerType = RTMP_PACKET_SIZE_MEDIUM;
    pPacket->m_packetType = RTMP_PACKET_TYPE_VIDEO;
    pPacket->m_nBodySize = bodySize;
    pPacket->m_nChannel = 0x04;
    pPacket->m_nInfoField2 = this->pRtmp->m_stream_id;

    pPacketQueue->push(pPacket);
}

void LivePush::pushVideo(jbyte *videoData, jint dataLen, jboolean keyFrame) {
    // frame type : 1关键帧，2 非关键帧 (4bit)
    // CodecID : 7表示 AVC (4bit)  , 与 frame type 组合起来刚好是 1 个字节  0x17
    // fixed : 0x01 0x00 0x00 0x00 (4byte)  0x01  表示 NALU 单元

    // video data length       (4byte)  video 长度
    // video data
    // 数据的长度（大小） =  dataLen + 9
    int bodySize = dataLen + 9;
    // 构建 RTMPPacket
    RTMPPacket *pPacket = (RTMPPacket *) malloc(sizeof(RTMPPacket));
    RTMPPacket_Alloc(pPacket, bodySize);
    RTMPPacket_Reset(pPacket);

    // 构建 body 按上面的一个一个开始赋值
    char *body = pPacket->m_body;
    int index = 0;
    // frame type : 1关键帧，2 非关键帧 (4bit)
    // CodecID : 7表示 AVC (4bit)  , 与 frame type 组合起来刚好是 1 个字节  0x17
    if (keyFrame) {
        body[index++] = 0x17;
    } else {
        body[index++] = 0x27;
    }

    // fixed : 0x01 0x00 0x00 0x00 (4byte)  0x01  表示 NALU 单元
    body[index++] = 0x01;
    body[index++] = 0x00;
    body[index++] = 0x00;
    body[index++] = 0x00;

    // video data length       (4byte)  video 长度
    body[index++] = (dataLen >> 24) & 0xFF;
    body[index++] = (dataLen >> 16) & 0xFF;
    body[index++] = (dataLen >> 8) & 0xFF;
    body[index++] = dataLen & 0xFF;
    // video data
    memcpy(&body[index], videoData, dataLen);

    // RTMPPacket 设置一些信息
    pPacket->m_headerType = RTMP_PACKET_SIZE_LARGE;
    pPacket->m_packetType = RTMP_PACKET_TYPE_VIDEO;
    pPacket->m_hasAbsTimestamp = 0;
    pPacket->m_nTimeStamp = RTMP_GetTime() - startTime; //时间戳
    pPacket->m_nBodySize = bodySize;
    pPacket->m_nChannel = 0x04;
    pPacket->m_nInfoField2 = this->pRtmp->m_stream_id;

    pPacketQueue->push(pPacket);
}

void LivePush::pushAudio(jbyte *audioData, jint dataLen) {
    // 2 字节头信息
    // 前四位表示音频数据格式 AAC  10  ->  1010  ->  A
    // 五六位表示采样率 0 = 5.5k  1 = 11k  2 = 22k  3(11) = 44k
    // 七位表示采样采样的精度 0 = 8bits  1 = 16bits
    // 八位表示音频类型  0 = mono  1 = stereo
    // 我们这里算出来第一个字节是 0xAF   1010   11 11

    // 数据的长度（大小） =  dataLen + 2
    int bodySize = dataLen + 2;
    // 构建 RTMPPacket
    RTMPPacket *pPacket = (RTMPPacket *) malloc(sizeof(RTMPPacket));
    RTMPPacket_Alloc(pPacket, bodySize);
    RTMPPacket_Reset(pPacket);

    // 构建 body 按上面的一个一个开始赋值
    char *body = pPacket->m_body;
    int index = 0;
    // 我们这里算出来第一个字节是 0xAF
    body[index++] = 0xAF;
    // 0x01 代表 aac 原始数据
    body[index++] = 0x01;
    // audio data
    memcpy(&body[index], audioData, dataLen);

    // RTMPPacket 设置一些信息
    pPacket->m_headerType = RTMP_PACKET_SIZE_LARGE;
    pPacket->m_packetType = RTMP_PACKET_TYPE_AUDIO;
    pPacket->m_hasAbsTimestamp = 0;
    pPacket->m_nTimeStamp = RTMP_GetTime() - startTime;
    pPacket->m_nBodySize = bodySize;
    pPacket->m_nChannel = 0x04;
    pPacket->m_nInfoField2 = this->pRtmp->m_stream_id;

    pPacketQueue->push(pPacket);
}

void LivePush::stop() {
    isPushing = false;
    pPacketQueue->notify();
    pthread_join(initConnectTid, NULL);
    LOGE("等待停止了");
}
