package com.tencent.liteav.network;

import android.os.Bundle;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/* compiled from: TXCMultiStreamDownloader */
public class d implements h {
    private h a = null;
    private b b = null;
    private long c = 0;
    private long d = 0;
    private b e = null;
    private a f;
    private long g = 0;
    private long h = 0;

    /* compiled from: TXCMultiStreamDownloader */
    public interface a {
        void onSwitchFinish(TXIStreamDownloader tXIStreamDownloader, boolean z);
    }

    /* compiled from: TXCMultiStreamDownloader */
    private static class b implements com.tencent.liteav.basic.d.a, h {
        private final int a = 2;
        private long b = 0;
        private long c = 0;
        private int d = 0;
        private boolean e = false;
        private long f = 0;
        private long g = 0;
        private long h = 0;
        private ArrayList<com.tencent.liteav.basic.g.b> i = new ArrayList();
        private ArrayList<com.tencent.liteav.basic.g.a> j = new ArrayList();
        private TXIStreamDownloader k = null;
        private WeakReference<d> l;
        private h m;

        public b(TXIStreamDownloader tXIStreamDownloader, d dVar) {
            this.l = new WeakReference(dVar);
            this.k = tXIStreamDownloader;
            this.k.setListener(this);
        }

        public void a(long j) {
            this.d = 0;
            this.b = j;
            this.k.setListener(this);
            this.k.setNotifyListener(this);
        }

        public void b(long j) {
            this.b = 0;
            this.f = j;
            this.h = 0;
            this.g = 0;
            if (this.k != null && this.f == 0) {
                this.k.stopDownload();
                this.k = null;
            }
        }

        public void a(h hVar) {
            this.m = hVar;
        }

        public void onPullAudio(com.tencent.liteav.basic.g.a aVar) {
            if (this.b > 0) {
                a(aVar);
            } else if (this.f > 0) {
                b(aVar);
            } else if (this.m != null) {
                this.m.onPullAudio(aVar);
            }
        }

        public void onPullNAL(com.tencent.liteav.basic.g.b bVar) {
            if (bVar != null) {
                if (this.b > 0) {
                    a(bVar);
                } else if (this.f > 0) {
                    b(bVar);
                } else if (this.m != null) {
                    this.m.onPullNAL(bVar);
                }
            }
        }

        private void a(com.tencent.liteav.basic.g.a aVar) {
            if (aVar != null && aVar.timestamp >= this.c && aVar.timestamp >= this.b) {
                if (this.m == null || this.c <= 0 || aVar.timestamp < this.c) {
                    this.j.add(aVar);
                } else {
                    this.m.onPullAudio(aVar);
                }
            }
        }

        private void b(com.tencent.liteav.basic.g.a aVar) {
            if (this.h <= 0) {
                if (this.g <= 0 || aVar == null || aVar.timestamp < this.g) {
                    if (this.m != null) {
                        this.m.onPullAudio(aVar);
                    }
                    return;
                }
                this.h = aVar.timestamp;
            }
        }

        private void a(com.tencent.liteav.basic.g.b bVar) {
            StringBuilder stringBuilder;
            d dVar = (d) this.l.get();
            if (bVar.nalType == 0 && !this.e) {
                this.d++;
                if (dVar != null && (dVar.d <= bVar.pts || this.d == 2)) {
                    this.b = dVar.a(bVar.pts);
                    this.e = true;
                }
                if (dVar != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" stream_switch pre start begin gop ");
                    stringBuilder.append(this.d);
                    stringBuilder.append(" last iframe ts ");
                    stringBuilder.append(dVar.d);
                    stringBuilder.append(" pts ");
                    stringBuilder.append(bVar.pts);
                    stringBuilder.append(" from ");
                    stringBuilder.append(this.b);
                    stringBuilder.append(" type ");
                    stringBuilder.append(bVar.nalType);
                    TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
                }
            }
            if (this.e) {
                if (dVar != null) {
                    dVar.b(bVar.pts);
                }
                if (bVar.pts >= this.b) {
                    StringBuilder stringBuilder2;
                    if (bVar.nalType == 0 && this.c == 0) {
                        this.c = bVar.pts;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(" stream_switch pre start end ");
                        stringBuilder2.append(bVar.pts);
                        stringBuilder2.append(" from ");
                        stringBuilder2.append(this.b);
                        stringBuilder2.append(" type ");
                        stringBuilder2.append(bVar.nalType);
                        TXCLog.w("TXCMultiStreamDownloader", stringBuilder2.toString());
                    }
                    if (this.c > 0) {
                        if (this.m != null) {
                            Iterator it;
                            if (!this.j.isEmpty()) {
                                it = this.j.iterator();
                                while (it.hasNext()) {
                                    com.tencent.liteav.basic.g.a aVar = (com.tencent.liteav.basic.g.a) it.next();
                                    if (aVar.timestamp >= this.c) {
                                        stringBuilder2 = new StringBuilder();
                                        stringBuilder2.append(" stream_switch pre start cache audio pts ");
                                        stringBuilder2.append(aVar.timestamp);
                                        stringBuilder2.append(" from ");
                                        stringBuilder2.append(this.c);
                                        TXCLog.i("TXCMultiStreamDownloader", stringBuilder2.toString());
                                        this.m.onPullAudio(aVar);
                                    }
                                }
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(" stream_switch pre start end audio cache  ");
                                stringBuilder.append(this.j.size());
                                TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
                                this.j.clear();
                            }
                            if (!this.i.isEmpty()) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(" stream_switch pre start end video cache  ");
                                stringBuilder.append(this.i.size());
                                TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
                                it = this.i.iterator();
                                while (it.hasNext()) {
                                    this.m.onPullNAL((com.tencent.liteav.basic.g.b) it.next());
                                }
                                this.i.clear();
                            }
                            this.m.onPullNAL(bVar);
                            this.m = null;
                            if (dVar != null) {
                                dVar.a(this.k, true);
                            }
                            this.k.setNotifyListener(null);
                        } else {
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(" stream_switch pre start cache video pts ");
                            stringBuilder3.append(bVar.pts);
                            stringBuilder3.append(" from ");
                            stringBuilder3.append(this.c);
                            stringBuilder3.append(" type ");
                            stringBuilder3.append(bVar.nalType);
                            TXCLog.i("TXCMultiStreamDownloader", stringBuilder3.toString());
                            this.i.add(bVar);
                        }
                    }
                }
            }
        }

        private void b(com.tencent.liteav.basic.g.b bVar) {
            d dVar = (d) this.l.get();
            if (dVar != null) {
                dVar.c(bVar.pts);
            }
            if (bVar.pts >= this.f) {
                if (bVar.nalType == 0) {
                    this.g = bVar.pts;
                }
                if (this.g > 0) {
                    StringBuilder stringBuilder;
                    if (this.h > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(" stream_switch delay stop end video pts ");
                        stringBuilder.append(this.g);
                        stringBuilder.append(" audio ts ");
                        stringBuilder.append(this.h);
                        stringBuilder.append(" from ");
                        stringBuilder.append(this.f);
                        TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
                        if (dVar != null) {
                            dVar.b();
                        }
                        this.m = null;
                        this.k.setListener(null);
                        this.k.stopDownload();
                        return;
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" stream_switch delay stop video end wait audio end video pts ");
                    stringBuilder.append(bVar.pts);
                    stringBuilder.append(" from ");
                    stringBuilder.append(this.f);
                    stringBuilder.append(" type ");
                    stringBuilder.append(bVar.nalType);
                    TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
                } else if (this.m != null) {
                    this.m.onPullNAL(bVar);
                }
            } else if (this.m != null) {
                this.m.onPullNAL(bVar);
            }
        }

        public void onNotifyEvent(int i, Bundle bundle) {
            if (i == TXCStreamDownloader.TXE_DOWNLOAD_ERROR_DISCONNECT || i == TXCStreamDownloader.TXE_DOWNLOAD_ERROR_CONNECT_FAILED) {
                d dVar = (d) this.l.get();
                if (dVar != null) {
                    dVar.a(this.k, false);
                }
                this.k.setNotifyListener(null);
            }
        }
    }

    public void a(h hVar) {
        this.a = hVar;
    }

    public d(a aVar) {
        this.f = aVar;
    }

    public void a() {
        if (this.b != null) {
            this.b.b(0);
        }
        if (this.e != null) {
            this.e.b(0);
        }
    }

    public void a(TXIStreamDownloader tXIStreamDownloader, TXIStreamDownloader tXIStreamDownloader2, long j, long j2, String str) {
        this.c = j;
        this.d = j2;
        this.b = new b(tXIStreamDownloader, this);
        this.b.a((h) this);
        Vector vector = new Vector();
        vector.add(new e(str, false));
        tXIStreamDownloader2.setOriginUrl(str);
        tXIStreamDownloader2.startDownload(vector, false, false, tXIStreamDownloader.mEnableMessage);
        this.e = new b(tXIStreamDownloader2, this);
        this.e.a(this.c);
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        long j;
        long j2;
        this.b.a(null);
        this.e.a((h) this);
        this.b = this.e;
        this.e = null;
        String str = "TXCMultiStreamDownloader";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" stream_switch end at ");
        stringBuilder.append(this.c);
        stringBuilder.append(" stop ts ");
        stringBuilder.append(this.h);
        stringBuilder.append(" start ts ");
        stringBuilder.append(this.g);
        stringBuilder.append(" diff ts ");
        if (this.h > this.g) {
            j = this.h;
            j2 = this.g;
        } else {
            j = this.g;
            j2 = this.h;
        }
        stringBuilder.append(j - j2);
        TXCLog.w(str, stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(TXIStreamDownloader tXIStreamDownloader, boolean z) {
        if (this.f != null) {
            this.f.onSwitchFinish(tXIStreamDownloader, z);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public long a(long j) {
        if (this.b != null) {
            this.b.b(this.c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" stream_switch delay stop begin from ");
        stringBuilder.append(this.c);
        TXCLog.w("TXCMultiStreamDownloader", stringBuilder.toString());
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(long j) {
        this.g = j;
    }

    /* Access modifiers changed, original: 0000 */
    public void c(long j) {
        this.h = j;
    }

    public void onPullAudio(com.tencent.liteav.basic.g.a aVar) {
        if (this.a != null) {
            this.a.onPullAudio(aVar);
        }
    }

    public void onPullNAL(com.tencent.liteav.basic.g.b bVar) {
        this.c = bVar.pts;
        if (bVar.nalType == 0) {
            this.d = bVar.pts;
        }
        if (this.a != null) {
            this.a.onPullNAL(bVar);
        }
    }
}
