#ifndef FFMPEGDEMO_PLAYERSTATUS_H
#define FFMPEGDEMO_PLAYERSTATUS_H

class PushStatus {
public:
    /**
     * 是否退出，打算用这个变量来做退出(销毁)
     */
    bool isExit = false;

};

#endif //FFMPEGDEMO_PLAYERSTATUS_H
