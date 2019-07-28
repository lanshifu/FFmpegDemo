package com.tencent.liteav.audio.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TXCAudioRouteMgr */
public class a implements e {
    private final String a;
    private Context b;
    private BroadcastReceiver c;
    private ServiceListener d;
    private BluetoothHeadset e;
    private List<d> f;
    private Handler g;
    private boolean h;
    private int i;
    private boolean j;
    private AudioManager k;

    /* compiled from: TXCAudioRouteMgr */
    private static class a {
        private static final a a = new a();
    }

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static void a(String str) {
        TXCTraeJNI.nativeSetTraeConfig(str);
    }

    static {
        b.e();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(a.class.getSimpleName());
        this.a = stringBuilder.toString();
        this.i = TXEAudioDef.TXE_AUDIO_MODE_SPEAKER;
        this.j = false;
        this.f = new ArrayList();
    }

    public void a(Context context) {
        if (this.c == null) {
            TXCLog.i(this.a, "init");
            this.b = context.getApplicationContext();
            this.k = (AudioManager) context.getSystemService("audio");
            this.g = new Handler(Looper.getMainLooper());
            c.a().a(this.b);
            c.a().a((e) this);
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    String a = a.this.a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onReceive, action = ");
                    stringBuilder.append(action);
                    TXCLog.i(a, stringBuilder.toString());
                    if (action.equals("android.intent.action.HEADSET_PLUG")) {
                        a.this.b(intent);
                    } else if (action.equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
                        a.this.a(intent);
                    } else {
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (bluetoothDevice != null && a.this.e != null) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            a.this.a(bluetoothDevice);
                        }
                    }
                }
            };
            this.d = new ServiceListener() {
                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    TXCLog.i(a.this.a, "onServiceConnected");
                    if (i == 1) {
                        a.this.e = (BluetoothHeadset) bluetoothProfile;
                        a.this.b();
                    }
                }

                public void onServiceDisconnected(int i) {
                    TXCLog.i(a.this.a, "onServiceDisconnected");
                    if (i == 1) {
                        a.this.e = null;
                        a.this.a(a.this.i);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            this.b.registerReceiver(this.c, intentFilter);
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    defaultAdapter.getProfileProxy(this.b, this.d, 1);
                }
            } catch (Exception e) {
                String str = this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("BluetoothAdapter getProfileProxy: ");
                stringBuilder.append(e);
                TXCLog.e(str, stringBuilder.toString());
            }
        }
    }

    private void b() {
        List list = null;
        try {
            list = this.e.getConnectedDevices();
        } catch (Exception e) {
            String str = this.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getConnectedDevices exception = ");
            stringBuilder.append(e);
            TXCLog.e(str, stringBuilder.toString());
            this.e = null;
        }
        if (list != null && list.size() > 0) {
            a((BluetoothDevice) list.get(0));
        }
    }

    private void a(Intent intent) {
        int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
        String str = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Audio SCO state: ");
        stringBuilder.append(intExtra);
        stringBuilder.append(", thread id = ");
        stringBuilder.append(Thread.currentThread().getId());
        Log.d(str, stringBuilder.toString());
        if (intExtra == 1) {
            TXCLog.i(this.a, "SCO connected, yeah!");
            this.j = true;
            this.k.setBluetoothScoOn(true);
            a(this.i);
        } else if (intExtra == 2) {
            TXCLog.i(this.a, "SCO connecting");
        } else if (intExtra == 0) {
            TXCLog.i(this.a, "SCO disconnect");
            if (this.e != null && this.j) {
                TXCLog.i(this.a, "mLastBTScoConnected true, retry once");
                this.j = false;
                this.g.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            a.this.k.startBluetoothSco();
                        } catch (NullPointerException e) {
                            String a = a.this.a;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("startBluetoothSco exception = ");
                            stringBuilder.append(e);
                            TXCLog.i(a, stringBuilder.toString());
                            e.printStackTrace();
                        }
                    }
                }, 1000);
            }
        }
    }

    private void b(Intent intent) {
        if (intent.hasExtra("state")) {
            int intExtra = intent.getIntExtra("state", 0);
            if (intExtra == 0) {
                this.h = false;
                a(false);
                a(this.i);
                TXCLog.d(this.a, "pull out wired headset");
            } else if (1 == intExtra) {
                this.h = true;
                a(true);
                a(this.i);
                TXCLog.d(this.a, "insert wired headset");
            }
        }
    }

    private void a(BluetoothDevice bluetoothDevice) {
        String str;
        StringBuilder stringBuilder;
        if (bluetoothDevice != null && this.e != null) {
            int connectionState;
            try {
                connectionState = this.e.getConnectionState(bluetoothDevice);
            } catch (Exception e) {
                str = this.a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("getConnectionState exception: ");
                stringBuilder.append(e);
                TXCLog.e(str, stringBuilder.toString());
                connectionState = 0;
            }
            str = this.a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("BluetoothHeadset state：");
            stringBuilder.append(connectionState);
            TXCLog.d(str, stringBuilder.toString());
            if (connectionState == 0) {
                a(false);
                TXCLog.d(this.a, "BluetoothHeadset disconnect");
                this.k.setBluetoothScoOn(false);
                this.k.stopBluetoothSco();
                a(this.i);
            } else if (connectionState == 2) {
                a(true);
                TXCLog.d(this.a, "BluetoothHeadset connect");
                if (this.k.isBluetoothScoAvailableOffCall()) {
                    try {
                        this.k.startBluetoothSco();
                    } catch (NullPointerException e2) {
                        String str2 = this.a;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("startBluetoothSco exception = ");
                        stringBuilder2.append(e2);
                        TXCLog.i(str2, stringBuilder2.toString());
                        e2.printStackTrace();
                    }
                } else {
                    TXCLog.e(this.a, "not support BTHeadset sco");
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:26:0x006a, code skipped:
            return;
     */
    public synchronized void a(int r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r3.i = r4;	 Catch:{ all -> 0x006b }
        r0 = r3.h;	 Catch:{ all -> 0x006b }
        r1 = 0;
        if (r0 == 0) goto L_0x001b;
    L_0x0008:
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setMode(r1);	 Catch:{ all -> 0x006b }
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setSpeakerphoneOn(r1);	 Catch:{ all -> 0x006b }
        r4 = r3.a;	 Catch:{ all -> 0x006b }
        r0 = "setAudioMode, is wiredHeadsetOn, set MODE_NORMAL and speakerphoneOn false";
        com.tencent.liteav.basic.log.TXCLog.d(r4, r0);	 Catch:{ all -> 0x006b }
        monitor-exit(r3);
        return;
    L_0x001b:
        r0 = r3.e;	 Catch:{ all -> 0x006b }
        r2 = 3;
        if (r0 == 0) goto L_0x003b;
    L_0x0020:
        r0 = r3.k;	 Catch:{ all -> 0x006b }
        r0 = r0.isBluetoothScoOn();	 Catch:{ all -> 0x006b }
        if (r0 == 0) goto L_0x003b;
    L_0x0028:
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setMode(r2);	 Catch:{ all -> 0x006b }
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setSpeakerphoneOn(r1);	 Catch:{ all -> 0x006b }
        r4 = r3.a;	 Catch:{ all -> 0x006b }
        r0 = "setAudioMode, is bluetoothHeadset connect and isBluetoothScoOn true, set mode MODE_IN_COMMUNICATION and speakerphoneOn false";
        com.tencent.liteav.basic.log.TXCLog.d(r4, r0);	 Catch:{ all -> 0x006b }
        monitor-exit(r3);
        return;
    L_0x003b:
        r0 = r3.k;	 Catch:{ all -> 0x006b }
        if (r0 != 0) goto L_0x0041;
    L_0x003f:
        monitor-exit(r3);
        return;
    L_0x0041:
        r0 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_MODE_RECEIVER;	 Catch:{ all -> 0x006b }
        if (r4 != r0) goto L_0x0057;
    L_0x0045:
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setMode(r2);	 Catch:{ all -> 0x006b }
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setSpeakerphoneOn(r1);	 Catch:{ all -> 0x006b }
        r4 = r3.a;	 Catch:{ all -> 0x006b }
        r0 = "AudioCenter setAudioMode to receiver, MODE_IN_COMMUNICATION, speakerphoneOn false";
        com.tencent.liteav.basic.log.TXCLog.i(r4, r0);	 Catch:{ all -> 0x006b }
        goto L_0x0069;
    L_0x0057:
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r4.setMode(r1);	 Catch:{ all -> 0x006b }
        r4 = r3.k;	 Catch:{ all -> 0x006b }
        r0 = 1;
        r4.setSpeakerphoneOn(r0);	 Catch:{ all -> 0x006b }
        r4 = r3.a;	 Catch:{ all -> 0x006b }
        r0 = "AudioCenter setAudioMode to speaker, MODE_NORMAL, speakerphoneOn true";
        com.tencent.liteav.basic.log.TXCLog.i(r4, r0);	 Catch:{ all -> 0x006b }
    L_0x0069:
        monitor-exit(r3);
        return;
    L_0x006b:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.audio.impl.a.a(int):void");
    }

    public synchronized void a(d dVar) {
        if (dVar != null) {
            this.f.add(dVar);
            if (this.h) {
                dVar.OnHeadsetState(true);
            } else if (this.e == null || this.k == null || !this.k.isBluetoothScoOn()) {
                dVar.OnHeadsetState(false);
            } else {
                dVar.OnHeadsetState(true);
            }
        }
    }

    public synchronized void b(d dVar) {
        if (dVar != null) {
            this.f.remove(dVar);
        }
    }

    private synchronized void a(boolean z) {
        for (d OnHeadsetState : this.f) {
            OnHeadsetState.OnHeadsetState(z);
        }
    }

    public void b(int i) {
        String str = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCallStateChanged, state = ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        if (this.k.isBluetoothScoAvailableOffCall()) {
            if (i == 0 && this.e != null) {
                TXCLog.i(this.a, "to restartBluetoothSco");
                this.g.postDelayed(new Runnable() {
                    public void run() {
                        a.this.b();
                    }
                }, 1000);
            }
            return;
        }
        TXCLog.e(this.a, "not support BTHeadset sco");
    }
}
