package com.tencent.rtmp.sharp.jni;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Process;
import com.tencent.liteav.basic.log.TXCLog;

public class TraeAudioSession extends BroadcastReceiver {
    static int s_nSessionIdAllocator;
    final String TRAE_ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";
    private boolean _canSwtich2Earphone = true;
    private String _connectedDev = TraeAudioManager.DEVICE_NONE;
    private a mCallback;
    private Context mContext;
    private boolean mIsHostside = false;
    private long mSessionId = Long.MIN_VALUE;

    public interface a {
        void a(int i);

        void a(int i, int i2);

        void a(int i, String str);

        void a(int i, String str, boolean z);

        void a(int i, boolean z);

        void a(int i, String[] strArr, String str, String str2, String str3);

        void a(String str, long j);

        void a(String str, String str2);

        void a(boolean z);

        void a(String[] strArr, String str, String str2, String str3);

        void b(int i);

        void b(int i, String str);

        void b(boolean z);

        void c(int i, String str);
    }

    public static long requestSessionId() {
        long myPid = ((long) Process.myPid()) << 32;
        int i = s_nSessionIdAllocator + 1;
        s_nSessionIdAllocator = i;
        return myPid + ((long) i);
    }

    public static void ExConnectDevice(Context context, String str) {
        if (context != null && str != null && str.length() > 0) {
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
            intent.putExtra(TraeAudioManager.PARAM_SESSIONID, Long.MIN_VALUE);
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECTDEVICE);
            intent.putExtra(TraeAudioManager.CONNECTDEVICE_DEVICENAME, str);
            context.sendBroadcast(intent);
        }
    }

    public TraeAudioSession(Context context, a aVar) {
        boolean z = false;
        TXCLog.w("TRAE", "TraeAudioSession create");
        if (Process.myPid() == TraeAudioManager._gHostProcessId) {
            z = true;
        }
        this.mIsHostside = z;
        this.mSessionId = requestSessionId();
        this.mCallback = aVar;
        this.mContext = context;
        if (context == null && QLog.isColorLevel()) {
            String str = "TRAE";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AudioSession | Invalid parameters: ctx = ");
            stringBuilder.append(context == null ? "null" : "{object}");
            stringBuilder.append("; cb = ");
            stringBuilder.append(aVar == null ? "null" : "{object}");
            QLog.w(str, 2, stringBuilder.toString());
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES);
        intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
        if (context != null) {
            try {
                context.registerReceiver(this, intentFilter);
            } catch (Exception unused) {
            }
        }
        registerAudioSession(true);
    }

    public void release() {
        if (this.mContext != null) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (Exception unused) {
            }
        }
        registerAudioSession(false);
        this.mContext = null;
        this.mCallback = null;
    }

    public void setCallback(a aVar) {
        this.mCallback = aVar;
    }

    private int registerAudioSession(boolean z) {
        if (this.mContext == null) {
            return -1;
        }
        if (this.mIsHostside) {
            return TraeAudioManager.registerAudioSession(z, this.mSessionId, this.mContext);
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_REGISTERAUDIOSESSION);
        intent.putExtra(TraeAudioManager.REGISTERAUDIOSESSION_ISREGISTER, z);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startService(String str) {
        if (str == null || str.length() <= 0) {
            str = "internal_disable_dev_switch";
        }
        if (this.mIsHostside) {
            return TraeAudioManager.startService(TraeAudioManager.OPERATION_STARTSERVICE, this.mSessionId, this.mIsHostside, str);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTSERVICE);
        intent.putExtra(TraeAudioManager.EXTRA_DATA_DEVICECONFIG, str);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int stopService() {
        if (this.mIsHostside) {
            return TraeAudioManager.stopService(TraeAudioManager.OPERATION_STOPSERVICE, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STOPSERVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getDeviceList() {
        if (this.mIsHostside) {
            return TraeAudioManager.getDeviceList(TraeAudioManager.OPERATION_GETDEVICELIST, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETDEVICELIST);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getStreamType() {
        if (this.mIsHostside) {
            return TraeAudioManager.getStreamType(TraeAudioManager.OPERATION_GETSTREAMTYPE, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETSTREAMTYPE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int connectDevice(String str) {
        if (this.mIsHostside) {
            return TraeAudioManager.connectDevice(TraeAudioManager.OPERATION_CONNECTDEVICE, this.mSessionId, this.mIsHostside, str);
        }
        if (this.mContext == null || str == null || str.length() <= 0) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECTDEVICE);
        intent.putExtra(TraeAudioManager.CONNECTDEVICE_DEVICENAME, str);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int connectHighestPriorityDevice() {
        if (this.mIsHostside) {
            return TraeAudioManager.connectHighestPriorityDevice(TraeAudioManager.OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int EarAction(int i) {
        if (this.mIsHostside) {
            return TraeAudioManager.earAction(TraeAudioManager.OPERATION_EARACTION, this.mSessionId, this.mIsHostside, i);
        }
        if (this.mContext == null || (i != 0 && i != 1)) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_EARACTION);
        intent.putExtra(TraeAudioManager.EXTRA_EARACTION, i);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int isDeviceChangabled() {
        if (this.mIsHostside) {
            return TraeAudioManager.isDeviceChangabled(TraeAudioManager.OPERATION_ISDEVICECHANGABLED, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_ISDEVICECHANGABLED);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getConnectedDevice() {
        if (this.mIsHostside) {
            return TraeAudioManager.getConnectedDevice(TraeAudioManager.OPERATION_GETCONNECTEDDEVICE, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETCONNECTEDDEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int getConnectingDevice() {
        if (this.mIsHostside) {
            return TraeAudioManager.getConnectingDevice(TraeAudioManager.OPERATION_GETCONNECTINGDEVICE, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_GETCONNECTINGDEVICE);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallPreprocess(int i, int i2) {
        if (this.mIsHostside) {
            return TraeAudioManager.voicecallPreprocess(TraeAudioManager.OPERATION_VOICECALL_PREPROCESS, this.mSessionId, this.mIsHostside, i, i2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_MODEPOLICY, i);
        intent.putExtra(TraeAudioManager.PARAM_STREAMTYPE, i2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_PREPROCESS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallPostprocess() {
        if (this.mIsHostside) {
            return TraeAudioManager.voicecallPostprocess(TraeAudioManager.OPERATION_VOICECALL_POSTPROCESS, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_POSTPROCESS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int voiceCallAudioParamChanged(int i, int i2) {
        if (this.mIsHostside) {
            return TraeAudioManager.voiceCallAudioParamChanged(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST, this.mSessionId, this.mIsHostside, i, i2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_MODEPOLICY, i);
        intent.putExtra(TraeAudioManager.PARAM_STREAMTYPE, i2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_VOICECALL_AUDIOPARAM_CHANGED);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z) {
        if (this.mIsHostside) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, this.mIsHostside, i, i2, uri, str, z, 1, "normal-ring", false);
        } else if (this.mContext == null) {
            return -1;
        } else {
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
            intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
            int i3 = i;
            intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
            intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
            intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
            intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
            intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
            intent.putExtra(TraeAudioManager.PARAM_RING_MODE, false);
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, "normal-ring");
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
            this.mContext.sendBroadcast(intent);
            return 0;
        }
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z, int i3, String str2) {
        if (this.mIsHostside) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, this.mIsHostside, i, i2, uri, str, z, i3, str2, false);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        int i4 = i;
        intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
        intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
        intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
        intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOPCOUNT, i3);
        intent.putExtra(TraeAudioManager.PARAM_RING_MODE, false);
        intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, str2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int startRing(int i, int i2, Uri uri, String str, boolean z, int i3, String str2, boolean z2) {
        if (this.mIsHostside) {
            return TraeAudioManager.startRing(TraeAudioManager.OPERATION_STARTRING, this.mSessionId, this.mIsHostside, i, i2, uri, str, z, i3, str2, z2);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        int i4 = i;
        intent.putExtra(TraeAudioManager.PARAM_RING_DATASOURCE, i);
        intent.putExtra(TraeAudioManager.PARAM_RING_RSID, i2);
        intent.putExtra(TraeAudioManager.PARAM_RING_URI, uri);
        intent.putExtra(TraeAudioManager.PARAM_RING_FILEPATH, str);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOP, z);
        intent.putExtra(TraeAudioManager.PARAM_RING_LOOPCOUNT, i3);
        intent.putExtra(TraeAudioManager.PARAM_RING_MODE, z2);
        intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, str2);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STARTRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int stopRing() {
        if (this.mIsHostside) {
            return TraeAudioManager.stopRing(TraeAudioManager.OPERATION_STOPRING, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_STOPRING);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int requestReleaseAudioFocus() {
        if (this.mIsHostside) {
            return TraeAudioManager.requestReleaseAudioFocus(TraeAudioManager.OPERATION_REQUEST_RELEASE_AUDIO_FOCUS, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_REQUEST_RELEASE_AUDIO_FOCUS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public int recoverAudioFocus() {
        if (this.mIsHostside) {
            return TraeAudioManager.recoverAudioFocus(TraeAudioManager.OPERATION_RECOVER_AUDIO_FOCUS, this.mSessionId, this.mIsHostside);
        }
        if (this.mContext == null) {
            return -1;
        }
        Intent intent = new Intent();
        intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
        intent.putExtra(TraeAudioManager.PARAM_SESSIONID, this.mSessionId);
        intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.OPERATION_RECOVER_AUDIO_FOCUS);
        this.mContext.sendBroadcast(intent);
        return 0;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            StringBuilder stringBuilder;
            try {
                long longExtra = intent.getLongExtra(TraeAudioManager.PARAM_SESSIONID, Long.MIN_VALUE);
                String stringExtra = intent.getStringExtra(TraeAudioManager.PARAM_OPERATION);
                boolean z = false;
                int intExtra = intent.getIntExtra(TraeAudioManager.PARAM_RES_ERRCODE, 0);
                boolean z2 = true;
                boolean booleanExtra;
                String str;
                String[] stringArrayExtra;
                int intExtra2;
                String stringExtra2;
                if (TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY.equals(intent.getAction())) {
                    if (TraeAudioManager.NOTIFY_SERVICE_STATE.equals(stringExtra)) {
                        booleanExtra = intent.getBooleanExtra(TraeAudioManager.NOTIFY_SERVICE_STATE_DATE, false);
                        if (QLog.isColorLevel()) {
                            str = "TRAE";
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onServiceStateUpdate]");
                            stringBuilder.append(booleanExtra ? "on" : "off");
                            QLog.w(str, 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(booleanExtra);
                        }
                    } else if (TraeAudioManager.NOTIFY_DEVICELIST_UPDATE.equals(stringExtra)) {
                        stringArrayExtra = intent.getStringArrayExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
                        str = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
                        stringExtra = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
                        String stringExtra3 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME);
                        String str2 = "\n";
                        int i = 0;
                        while (i < stringArrayExtra.length) {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str2);
                            stringBuilder2.append("AudioSession|    ");
                            stringBuilder2.append(i);
                            stringBuilder2.append(" ");
                            stringBuilder2.append(stringArrayExtra[i]);
                            stringBuilder2.append("\n");
                            str2 = stringBuilder2.toString();
                            if (stringArrayExtra[i].equals(TraeAudioManager.DEVICE_WIREDHEADSET) || stringArrayExtra[i].equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
                                z2 = false;
                            }
                            i++;
                        }
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(str2);
                        stringBuilder3.append("\n");
                        String stringBuilder4 = stringBuilder3.toString();
                        if (QLog.isColorLevel()) {
                            StringBuilder stringBuilder5 = new StringBuilder();
                            stringBuilder5.append("AudioSession|[onDeviceListUpdate]  connected:");
                            stringBuilder5.append(str);
                            stringBuilder5.append(" prevConnected:");
                            stringBuilder5.append(stringExtra);
                            stringBuilder5.append(" bt:");
                            stringBuilder5.append(stringExtra3);
                            stringBuilder5.append(" Num:");
                            stringBuilder5.append(stringArrayExtra.length);
                            stringBuilder5.append(stringBuilder4);
                            QLog.w("TRAE", 2, stringBuilder5.toString());
                        }
                        this._canSwtich2Earphone = z2;
                        this._connectedDev = str;
                        if (this.mCallback != null) {
                            this.mCallback.a(stringArrayExtra, str, stringExtra, stringExtra3);
                        }
                    } else if (TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE.equals(stringExtra)) {
                        booleanExtra = intent.getBooleanExtra(TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE_DATE, true);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onDeviceChangabledUpdate]");
                            stringBuilder.append(booleanExtra);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.b(booleanExtra);
                        }
                    } else if (TraeAudioManager.NOTIFY_STREAMTYPE_UPDATE.equals(stringExtra)) {
                        intExtra2 = intent.getIntExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, -1);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onStreamTypeUpdate] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" st:");
                            stringBuilder.append(intExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(intExtra2);
                        }
                    } else if (TraeAudioManager.NOTIFY_ROUTESWITCHSTART.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHSTART_FROM);
                        str = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHSTART_TO);
                        if (!(this.mCallback == null || stringExtra2 == null || str == null)) {
                            this.mCallback.a(stringExtra2, str);
                        }
                    } else if (TraeAudioManager.NOTIFY_ROUTESWITCHEND.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHEND_DEV);
                        long longExtra2 = intent.getLongExtra(TraeAudioManager.EXTRA_DATA_ROUTESWITCHEND_TIME, -1);
                        if (!(this.mCallback == null || stringExtra2 == null || longExtra2 == -1)) {
                            this.mCallback.a(stringExtra2, longExtra2);
                        }
                    }
                } else if (!TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES.equals(intent.getAction()) || this.mSessionId != longExtra) {
                } else {
                    StringBuilder stringBuilder6;
                    if (TraeAudioManager.OPERATION_GETDEVICELIST.equals(stringExtra)) {
                        StringBuilder stringBuilder7;
                        stringArrayExtra = intent.getStringArrayExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
                        String stringExtra4 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
                        String stringExtra5 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
                        String stringExtra6 = intent.getStringExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME);
                        stringExtra = "\n";
                        int i2 = 0;
                        while (i2 < stringArrayExtra.length) {
                            stringBuilder7 = new StringBuilder();
                            stringBuilder7.append(stringExtra);
                            stringBuilder7.append("AudioSession|    ");
                            stringBuilder7.append(i2);
                            stringBuilder7.append(" ");
                            stringBuilder7.append(stringArrayExtra[i2]);
                            stringBuilder7.append("\n");
                            stringExtra = stringBuilder7.toString();
                            if (stringArrayExtra[i2].equals(TraeAudioManager.DEVICE_WIREDHEADSET) || stringArrayExtra[i2].equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
                                z2 = false;
                            }
                            i2++;
                        }
                        stringBuilder6 = new StringBuilder();
                        stringBuilder6.append(stringExtra);
                        stringBuilder6.append("\n");
                        str = stringBuilder6.toString();
                        this._canSwtich2Earphone = z2;
                        this._connectedDev = stringExtra4;
                        if (QLog.isColorLevel()) {
                            stringBuilder7 = new StringBuilder();
                            stringBuilder7.append("AudioSession|[onGetDeviceListRes] err:");
                            stringBuilder7.append(intExtra);
                            stringBuilder7.append(" connected:");
                            stringBuilder7.append(stringExtra4);
                            stringBuilder7.append(" prevConnected:");
                            stringBuilder7.append(stringExtra5);
                            stringBuilder7.append(" bt:");
                            stringBuilder7.append(stringExtra6);
                            stringBuilder7.append(" Num:");
                            stringBuilder7.append(stringArrayExtra.length);
                            stringBuilder7.append(str);
                            QLog.w("TRAE", 2, stringBuilder7.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(intExtra, stringArrayExtra, stringExtra4, stringExtra5, stringExtra6);
                        }
                    } else if (TraeAudioManager.OPERATION_CONNECTDEVICE.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.CONNECTDEVICE_RESULT_DEVICENAME);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onConnectDeviceRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" dev:");
                            stringBuilder.append(stringExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            a aVar = this.mCallback;
                            if (intExtra == 0) {
                                z = true;
                            }
                            aVar.a(intExtra, stringExtra2, z);
                        }
                    } else if (TraeAudioManager.OPERATION_EARACTION.equals(stringExtra)) {
                        intExtra2 = intent.getIntExtra(TraeAudioManager.EXTRA_EARACTION, -1);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onConnectDeviceRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" earAction:");
                            stringBuilder.append(intExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        a aVar2 = this.mCallback;
                    } else if (TraeAudioManager.OPERATION_ISDEVICECHANGABLED.equals(stringExtra)) {
                        booleanExtra = intent.getBooleanExtra(TraeAudioManager.ISDEVICECHANGABLED_RESULT_ISCHANGABLED, false);
                        if (QLog.isColorLevel()) {
                            str = "TRAE";
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onIsDeviceChangabledRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" Changabled:");
                            stringBuilder.append(booleanExtra ? "Y" : "N");
                            QLog.w(str, 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(intExtra, booleanExtra);
                        }
                    } else if (TraeAudioManager.OPERATION_GETCONNECTEDDEVICE.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.GETCONNECTEDDEVICE_RESULT_LIST);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onGetConnectedDeviceRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" dev:");
                            stringBuilder.append(stringExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(intExtra, stringExtra2);
                        }
                    } else if (TraeAudioManager.OPERATION_GETCONNECTINGDEVICE.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.GETCONNECTINGDEVICE_RESULT_LIST);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onGetConnectingDeviceRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" dev:");
                            stringBuilder.append(stringExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.b(intExtra, stringExtra2);
                        }
                    } else if (TraeAudioManager.OPERATION_GETSTREAMTYPE.equals(stringExtra)) {
                        intExtra2 = intent.getIntExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, -1);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onGetStreamTypeRes] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" st:");
                            stringBuilder.append(intExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.a(intExtra, intExtra2);
                        }
                    } else if (TraeAudioManager.NOTIFY_RING_COMPLETION.equals(stringExtra)) {
                        stringExtra2 = intent.getStringExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING);
                        if (QLog.isColorLevel()) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("AudioSession|[onRingCompletion] err:");
                            stringBuilder.append(intExtra);
                            stringBuilder.append(" userData:");
                            stringBuilder.append(stringExtra2);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.c(intExtra, stringExtra2);
                        }
                    } else if (TraeAudioManager.OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                        if (QLog.isColorLevel()) {
                            stringBuilder6 = new StringBuilder();
                            stringBuilder6.append("AudioSession|[onVoicecallPreprocess] err:");
                            stringBuilder6.append(intExtra);
                            QLog.w("TRAE", 2, stringBuilder6.toString());
                        }
                        if (this.mCallback != null) {
                            this.mCallback.b(intExtra);
                        }
                    }
                }
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("AudioSession| nSessinId = ");
                    stringBuilder.append(this.mSessionId);
                    stringBuilder.append(" onReceive::intent:");
                    stringBuilder.append(intent.toString());
                    stringBuilder.append(" intent.getAction():");
                    stringBuilder.append(intent.getAction());
                    stringBuilder.append(" Exception:");
                    stringBuilder.append(e.getMessage());
                    QLog.e("TRAE", 2, stringBuilder.toString());
                }
            }
        }
    }
}
