package com.tencent.rtmp.downloader;

import android.net.Uri;
import android.text.TextUtils;
import com.tencent.ijk.media.player.IjkDownloadCenter;
import com.tencent.ijk.media.player.IjkDownloadCenter.OnDownloadListener;
import com.tencent.ijk.media.player.IjkDownloadMedia;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.network.f;
import com.tencent.liteav.network.g;
import com.tencent.liteav.network.i;
import com.tencent.liteav.network.j;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXPlayerAuthBuilder;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public class TXVodDownloadManager {
    public static final int DOWNLOAD_AUTH_FAILED = -5001;
    public static final int DOWNLOAD_DISCONNECT = -5005;
    public static final int DOWNLOAD_FORMAT_ERROR = -5004;
    public static final int DOWNLOAD_HLS_KEY_ERROR = -5006;
    public static final int DOWNLOAD_NO_FILE = -5003;
    public static final int DOWNLOAD_PATH_ERROR = -5007;
    public static final int DOWNLOAD_SUCCESS = 0;
    private static final int IJKDM_EVT_FILE_OPEN_ERROR = 1008;
    private static final int IJKDM_EVT_HLS_KEY_ERROR = 1008;
    private static final int IJKDM_EVT_NET_DISCONNECT = 1001;
    private static final String TAG = "TXVodDownloadManager";
    private static TXVodDownloadManager instance;
    protected IjkDownloadCenter mDownloadCenter = IjkDownloadCenter.getInstance();
    OnDownloadListener mDownloadCenterListener = new OnDownloadListener() {
        public void downloadBegin(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            if (convertMedia != null) {
                String str = TXVodDownloadManager.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("downloadBegin ");
                stringBuilder.append(convertMedia.playPath);
                TXCLog.i(str, stringBuilder.toString());
                TXVodDownloadManager.this.mListener.onDownloadStart(convertMedia);
                if (new File(convertMedia.playPath).isFile()) {
                    TXCLog.d(TXVodDownloadManager.TAG, "file state ok");
                } else {
                    TXCLog.e(TXVodDownloadManager.TAG, "file not create!");
                }
            }
        }

        public void downloadEnd(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            if (convertMedia != null) {
                String str = TXVodDownloadManager.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("downloadEnd ");
                stringBuilder.append(convertMedia.playPath);
                TXCLog.i(str, stringBuilder.toString());
                TXVodDownloadManager.this.mMediaInfoArray.remove(convertMedia);
                TXVodDownloadManager.this.mListener.onDownloadStop(convertMedia);
            }
        }

        public void downloadFinish(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            if (convertMedia != null) {
                String str = TXVodDownloadManager.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("downloadFinish ");
                stringBuilder.append(convertMedia.playPath);
                TXCLog.i(str, stringBuilder.toString());
                TXVodDownloadManager.this.mMediaInfoArray.remove(convertMedia);
                if (new File(convertMedia.playPath).isFile()) {
                    TXVodDownloadManager.this.mListener.onDownloadFinish(convertMedia);
                } else {
                    TXVodDownloadManager.this.mListener.onDownloadError(convertMedia, TXVodDownloadManager.DOWNLOAD_NO_FILE, "文件已被删除");
                }
            }
        }

        public void downloadError(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia, int i, String str) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            if (convertMedia != null) {
                String str2 = TXVodDownloadManager.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("downloadError ");
                stringBuilder.append(convertMedia.playPath);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                TXCLog.e(str2, stringBuilder.toString());
                TXVodDownloadManager.this.mMediaInfoArray.remove(convertMedia);
                if (convertMedia.isStop) {
                    TXVodDownloadManager.this.mListener.onDownloadStop(convertMedia);
                } else if (i == TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER) {
                    TXVodDownloadManager.this.mListener.onDownloadError(convertMedia, TXVodDownloadManager.DOWNLOAD_HLS_KEY_ERROR, str);
                } else {
                    TXVodDownloadManager.this.mListener.onDownloadError(convertMedia, TXVodDownloadManager.DOWNLOAD_DISCONNECT, str);
                }
            }
        }

        public void downloadProgress(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            if (convertMedia != null) {
                TXVodDownloadManager.this.mListener.onDownloadProgress(convertMedia);
            }
        }

        public int hlsKeyVerify(IjkDownloadCenter ijkDownloadCenter, IjkDownloadMedia ijkDownloadMedia, String str, byte[] bArr) {
            TXVodDownloadMediaInfo convertMedia = TXVodDownloadManager.this.convertMedia(ijkDownloadMedia);
            return convertMedia != null ? TXVodDownloadManager.this.mListener.hlsKeyVerify(convertMedia, str, bArr) : 0;
        }
    };
    protected String mDownloadPath;
    protected ITXVodDownloadListener mListener;
    protected ArrayList<TXVodDownloadMediaInfo> mMediaInfoArray;

    private TXVodDownloadManager() {
        this.mDownloadCenter.setListener(this.mDownloadCenterListener);
        this.mMediaInfoArray = new ArrayList();
    }

    public static TXVodDownloadManager getInstance() {
        if (instance == null) {
            instance = new TXVodDownloadManager();
        }
        return instance;
    }

    public void setDownloadPath(String str) {
        if (str != null) {
            new File(str).mkdirs();
            this.mDownloadPath = str;
        }
    }

    public void setListener(ITXVodDownloadListener iTXVodDownloadListener) {
        this.mListener = iTXVodDownloadListener;
    }

    public TXVodDownloadMediaInfo startDownloadUrl(String str) {
        TXVodDownloadMediaInfo tXVodDownloadMediaInfo = new TXVodDownloadMediaInfo();
        tXVodDownloadMediaInfo.url = str;
        this.mMediaInfoArray.add(tXVodDownloadMediaInfo);
        downloadMedia(tXVodDownloadMediaInfo);
        return tXVodDownloadMediaInfo;
    }

    public TXVodDownloadMediaInfo startDownload(final TXVodDownloadDataSource tXVodDownloadDataSource) {
        final TXVodDownloadMediaInfo tXVodDownloadMediaInfo = new TXVodDownloadMediaInfo();
        tXVodDownloadMediaInfo.dataSource = tXVodDownloadDataSource;
        if (tXVodDownloadDataSource.authBuilder != null) {
            TXPlayerAuthBuilder tXPlayerAuthBuilder = tXVodDownloadDataSource.authBuilder;
            f fVar = new f();
            fVar.a(tXPlayerAuthBuilder.isHttps());
            fVar.a(new g() {
                public void onNetSuccess(f fVar) {
                    if (tXVodDownloadMediaInfo.isStop) {
                        TXVodDownloadManager.this.mMediaInfoArray.remove(tXVodDownloadMediaInfo);
                        if (TXVodDownloadManager.this.mListener != null) {
                            TXVodDownloadManager.this.mListener.onDownloadStop(tXVodDownloadMediaInfo);
                        }
                        TXCLog.w(TXVodDownloadManager.TAG, "已取消下载任务");
                        return;
                    }
                    j classificationSource = TXVodDownloadManager.this.getClassificationSource(fVar.a(), tXVodDownloadDataSource.quality);
                    if (classificationSource == null) {
                        TXVodDownloadManager.this.mMediaInfoArray.remove(tXVodDownloadMediaInfo);
                        if (TXVodDownloadManager.this.mListener != null) {
                            TXVodDownloadManager.this.mListener.onDownloadError(tXVodDownloadMediaInfo, TXVodDownloadManager.DOWNLOAD_NO_FILE, "无此清晰度");
                        }
                        return;
                    }
                    tXVodDownloadMediaInfo.url = classificationSource.b();
                    tXVodDownloadMediaInfo.size = classificationSource.d();
                    tXVodDownloadMediaInfo.duration = classificationSource.c();
                    TXVodDownloadManager.this.downloadMedia(tXVodDownloadMediaInfo);
                }

                public void onNetFailed(f fVar, String str, int i) {
                    TXVodDownloadManager.this.mMediaInfoArray.remove(tXVodDownloadMediaInfo);
                    if (TXVodDownloadManager.this.mListener != null) {
                        TXVodDownloadManager.this.mListener.onDownloadError(tXVodDownloadMediaInfo, TXVodDownloadManager.DOWNLOAD_AUTH_FAILED, str);
                    }
                }
            });
            if (fVar.a(tXPlayerAuthBuilder.getAppId(), tXPlayerAuthBuilder.getFileId(), tXPlayerAuthBuilder.getTimeout(), tXPlayerAuthBuilder.getUs(), tXPlayerAuthBuilder.getExper(), tXPlayerAuthBuilder.getSign()) == 0) {
                tXVodDownloadMediaInfo.netApi = fVar;
                this.mMediaInfoArray.add(tXVodDownloadMediaInfo);
                return tXVodDownloadMediaInfo;
            }
            TXCLog.e(TAG, "unable to getPlayInfo");
        }
        return null;
    }

    public void stopDownload(TXVodDownloadMediaInfo tXVodDownloadMediaInfo) {
        if (tXVodDownloadMediaInfo != null) {
            tXVodDownloadMediaInfo.isStop = true;
            if (tXVodDownloadMediaInfo.tid < 0) {
                TXCLog.w(TAG, "stop download not start task");
                return;
            }
            this.mDownloadCenter.stop(tXVodDownloadMediaInfo.tid);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("stop download ");
            stringBuilder.append(tXVodDownloadMediaInfo.url);
            TXCLog.d(str, stringBuilder.toString());
        }
    }

    public boolean deleteDownloadFile(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("delete file ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        Iterator it = this.mMediaInfoArray.iterator();
        while (it.hasNext()) {
            TXVodDownloadMediaInfo tXVodDownloadMediaInfo = (TXVodDownloadMediaInfo) it.next();
            if (tXVodDownloadMediaInfo.playPath != null && tXVodDownloadMediaInfo.playPath.equals(str)) {
                TXCLog.e(TAG, "file is downloading, can not be delete");
                return false;
            }
        }
        new File(str).delete();
        TXCLog.e(TAG, "delete success");
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void downloadMedia(TXVodDownloadMediaInfo tXVodDownloadMediaInfo) {
        String str = tXVodDownloadMediaInfo.url;
        if (str != null) {
            StringBuilder stringBuilder;
            String str2;
            if (Uri.parse(str).getPath().endsWith(".m3u8")) {
                tXVodDownloadMediaInfo.playPath = makePlayPath(str);
                if (tXVodDownloadMediaInfo.playPath == null) {
                    if (this.mListener != null) {
                        this.mListener.onDownloadError(tXVodDownloadMediaInfo, DOWNLOAD_PATH_ERROR, "本地路径创建失败");
                    }
                    return;
                }
                if (!(tXVodDownloadMediaInfo.dataSource == null || tXVodDownloadMediaInfo.dataSource.token == null)) {
                    String[] split = str.split("/");
                    if (split.length > 0) {
                        int lastIndexOf = str.lastIndexOf(split[split.length - 1]);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str.substring(0, lastIndexOf));
                        stringBuilder.append("voddrm.token.");
                        stringBuilder.append(tXVodDownloadMediaInfo.dataSource.token);
                        stringBuilder.append(".");
                        stringBuilder.append(str.substring(lastIndexOf));
                        str = stringBuilder.toString();
                    }
                }
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("download hls ");
                stringBuilder.append(str);
                stringBuilder.append(" to ");
                stringBuilder.append(tXVodDownloadMediaInfo.playPath);
                TXCLog.d(str2, stringBuilder.toString());
                tXVodDownloadMediaInfo.tid = this.mDownloadCenter.downloadHls(str, tXVodDownloadMediaInfo.playPath);
                if (tXVodDownloadMediaInfo.tid < 0) {
                    TXCLog.e(TAG, "start download failed");
                    if (this.mListener != null) {
                        this.mListener.onDownloadError(tXVodDownloadMediaInfo, DOWNLOAD_FORMAT_ERROR, "Internal error");
                    }
                }
                return;
            }
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("format error: ");
            stringBuilder.append(str);
            TXCLog.e(str2, stringBuilder.toString());
            if (this.mListener != null) {
                this.mListener.onDownloadError(tXVodDownloadMediaInfo, DOWNLOAD_FORMAT_ERROR, "No support format");
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public String makePlayPath(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mDownloadPath);
        stringBuilder.append("/txdownload");
        String stringBuilder2 = stringBuilder.toString();
        File file = new File(stringBuilder2);
        StringBuilder stringBuilder3;
        if ((!file.exists() || !file.isDirectory()) && !file.mkdir()) {
            str = TAG;
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("创建下载路径失败 ");
            stringBuilder3.append(stringBuilder2);
            TXCLog.e(str, stringBuilder3.toString());
            return null;
        } else if (Uri.parse(str).getPath().endsWith(".m3u8")) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("/");
            stringBuilder3.append(md5(str));
            stringBuilder3.append(".m3u8.sqlite");
            return stringBuilder3.toString();
        } else {
            TXCLog.e(TAG, "不支持格式");
            return null;
        }
    }

    protected static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                StringBuilder stringBuilder;
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(toHexString);
                    toHexString = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(toHexString);
                str2 = stringBuilder.toString();
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* Access modifiers changed, original: 0000 */
    public j getClassificationSource(i iVar, int i) {
        if (i == 0) {
            return iVar.d();
        }
        return iVar.a(TXVodDownloadDataSource.qualityToId(i), "hls");
    }

    /* Access modifiers changed, original: 0000 */
    public TXVodDownloadMediaInfo convertMedia(IjkDownloadMedia ijkDownloadMedia) {
        Iterator it = this.mMediaInfoArray.iterator();
        while (it.hasNext()) {
            TXVodDownloadMediaInfo tXVodDownloadMediaInfo = (TXVodDownloadMediaInfo) it.next();
            if (tXVodDownloadMediaInfo.tid == ijkDownloadMedia.tid) {
                tXVodDownloadMediaInfo.downloadSize = ijkDownloadMedia.downloadSize;
                if (tXVodDownloadMediaInfo.size == 0) {
                    tXVodDownloadMediaInfo.size = ijkDownloadMedia.size;
                }
                return tXVodDownloadMediaInfo;
            }
        }
        return null;
    }
}
