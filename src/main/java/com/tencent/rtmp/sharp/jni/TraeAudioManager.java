package com.tencent.rtmp.sharp.jni;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

@SuppressLint({"NewApi"})
public class TraeAudioManager extends BroadcastReceiver {
    public static final String ACTION_TRAEAUDIOMANAGER_NOTIFY = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_NOTIFY";
    public static final String ACTION_TRAEAUDIOMANAGER_REQUEST = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_REQUEST";
    public static final String ACTION_TRAEAUDIOMANAGER_RES = "com.tencent.sharp.ACTION_TRAEAUDIOMANAGER_RES";
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP = 128;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO = 16;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    static final int AUDIO_DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    static final int AUDIO_DEVICE_OUT_EARPIECE = 1;
    static final int AUDIO_DEVICE_OUT_SPEAKER = 2;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADPHONE = 8;
    static final int AUDIO_DEVICE_OUT_WIRED_HEADSET = 4;
    public static final int AUDIO_MANAGER_ACTIVE_NONE = 0;
    public static final int AUDIO_MANAGER_ACTIVE_RING = 2;
    public static final int AUDIO_MANAGER_ACTIVE_VOICECALL = 1;
    static final String AUDIO_PARAMETER_STREAM_ROUTING = "routing";
    public static final String CONNECTDEVICE_DEVICENAME = "CONNECTDEVICE_DEVICENAME";
    public static final String CONNECTDEVICE_RESULT_DEVICENAME = "CONNECTDEVICE_RESULT_DEVICENAME";
    public static final String DEVICE_BLUETOOTHHEADSET = "DEVICE_BLUETOOTHHEADSET";
    public static final String DEVICE_EARPHONE = "DEVICE_EARPHONE";
    public static final String DEVICE_NONE = "DEVICE_NONE";
    public static final String DEVICE_SPEAKERPHONE = "DEVICE_SPEAKERPHONE";
    public static final int DEVICE_STATUS_CONNECTED = 2;
    public static final int DEVICE_STATUS_CONNECTING = 1;
    public static final int DEVICE_STATUS_DISCONNECTED = 0;
    public static final int DEVICE_STATUS_DISCONNECTING = 3;
    public static final int DEVICE_STATUS_ERROR = -1;
    public static final int DEVICE_STATUS_UNCHANGEABLE = 4;
    public static final String DEVICE_WIREDHEADSET = "DEVICE_WIREDHEADSET";
    public static final int EARACTION_AWAY = 0;
    public static final int EARACTION_CLOSE = 1;
    public static final String EXTRA_DATA_AVAILABLEDEVICE_LIST = "EXTRA_DATA_AVAILABLEDEVICE_LIST";
    public static final String EXTRA_DATA_CONNECTEDDEVICE = "EXTRA_DATA_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_DEVICECONFIG = "EXTRA_DATA_DEVICECONFIG";
    public static final String EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME = "EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME";
    public static final String EXTRA_DATA_PREV_CONNECTEDDEVICE = "EXTRA_DATA_PREV_CONNECTEDDEVICE";
    public static final String EXTRA_DATA_ROUTESWITCHEND_DEV = "EXTRA_DATA_ROUTESWITCHEND_DEV";
    public static final String EXTRA_DATA_ROUTESWITCHEND_TIME = "EXTRA_DATA_ROUTESWITCHEND_TIME";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_FROM = "EXTRA_DATA_ROUTESWITCHSTART_FROM";
    public static final String EXTRA_DATA_ROUTESWITCHSTART_TO = "EXTRA_DATA_ROUTESWITCHSTART_TO";
    public static final String EXTRA_DATA_STREAMTYPE = "EXTRA_DATA_STREAMTYPE";
    public static final String EXTRA_EARACTION = "EXTRA_EARACTION";
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_DEFAULT = 0;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_NONE = 0;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FOR_COMMUNICATION = 0;
    public static final int FOR_DOCK = 3;
    public static final int FOR_MEDIA = 1;
    public static final int FOR_RECORD = 2;
    public static final String GETCONNECTEDDEVICE_RESULT_LIST = "GETCONNECTEDDEVICE_REULT_LIST";
    public static final String GETCONNECTINGDEVICE_RESULT_LIST = "GETCONNECTINGDEVICE_REULT_LIST";
    public static final String ISDEVICECHANGABLED_RESULT_ISCHANGABLED = "ISDEVICECHANGABLED_REULT_ISCHANGABLED";
    public static boolean IsMusicScene = false;
    public static boolean IsUpdateSceneFlag = false;
    public static final int MODE_MUSIC_PLAYBACK = 2;
    public static final int MODE_MUSIC_PLAY_RECORD = 1;
    public static final int MODE_MUSIC_PLAY_RECORD_HIGH_QUALITY = 3;
    public static final int MODE_VOICE_CHAT = 0;
    public static final String MUSIC_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_WIREDHEADSET;DEVICE_BLUETOOTHHEADSET;";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE = "NOTIFY_DEVICECHANGABLE_UPDATE";
    public static final String NOTIFY_DEVICECHANGABLE_UPDATE_DATE = "NOTIFY_DEVICECHANGABLE_UPDATE_DATE";
    public static final String NOTIFY_DEVICELIST_UPDATE = "NOTIFY_DEVICELISTUPDATE";
    public static final String NOTIFY_RING_COMPLETION = "NOTIFY_RING_COMPLETION";
    public static final String NOTIFY_ROUTESWITCHEND = "NOTIFY_ROUTESWITCHEND";
    public static final String NOTIFY_ROUTESWITCHSTART = "NOTIFY_ROUTESWITCHSTART";
    public static final String NOTIFY_SERVICE_STATE = "NOTIFY_SERVICE_STATE";
    public static final String NOTIFY_SERVICE_STATE_DATE = "NOTIFY_SERVICE_STATE_DATE";
    public static final String NOTIFY_STREAMTYPE_UPDATE = "NOTIFY_STREAMTYPE_UPDATE";
    private static final int NUM_FORCE_CONFIG = 11;
    private static final int NUM_FORCE_USE = 4;
    public static final String OPERATION_CONNECTDEVICE = "OPERATION_CONNECTDEVICE";
    public static final String OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE = "OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE";
    public static final String OPERATION_EARACTION = "OPERATION_EARACTION";
    public static final String OPERATION_GETCONNECTEDDEVICE = "OPERATION_GETCONNECTEDDEVICE";
    public static final String OPERATION_GETCONNECTINGDEVICE = "OPERATION_GETCONNECTINGDEVICE";
    public static final String OPERATION_GETDEVICELIST = "OPERATION_GETDEVICELIST";
    public static final String OPERATION_GETSTREAMTYPE = "OPERATION_GETSTREAMTYPE";
    public static final String OPERATION_ISDEVICECHANGABLED = "OPERATION_ISDEVICECHANGABLED";
    public static final String OPERATION_RECOVER_AUDIO_FOCUS = "OPERATION_RECOVER_AUDIO_FOCUS";
    public static final String OPERATION_REGISTERAUDIOSESSION = "OPERATION_REGISTERAUDIOSESSION";
    public static final String OPERATION_REQUEST_RELEASE_AUDIO_FOCUS = "OPERATION_REQUEST_RELEASE_AUDIO_FOCUS";
    public static final String OPERATION_STARTRING = "OPERATION_STARTRING";
    public static final String OPERATION_STARTSERVICE = "OPERATION_STARTSERVICE";
    public static final String OPERATION_STOPRING = "OPERATION_STOPRING";
    public static final String OPERATION_STOPSERVICE = "OPERATION_STOPSERVICE";
    public static final String OPERATION_VOICECALL_AUDIOPARAM_CHANGED = "OPERATION_VOICECALL_AUDIOPARAM_CHANGED";
    public static final String OPERATION_VOICECALL_POSTPROCESS = "OPERATION_VOICECALL_POSTROCESS";
    public static final String OPERATION_VOICECALL_PREPROCESS = "OPERATION_VOICECALL_PREPROCESS";
    public static final String PARAM_DEVICE = "PARAM_DEVICE";
    public static final String PARAM_ERROR = "PARAM_ERROR";
    public static final String PARAM_ISHOSTSIDE = "PARAM_ISHOSTSIDE";
    public static final String PARAM_MODEPOLICY = "PARAM_MODEPOLICY";
    public static final String PARAM_OPERATION = "PARAM_OPERATION";
    public static final String PARAM_RES_ERRCODE = "PARAM_RES_ERRCODE";
    public static final String PARAM_RING_DATASOURCE = "PARAM_RING_DATASOURCE";
    public static final String PARAM_RING_FILEPATH = "PARAM_RING_FILEPATH";
    public static final String PARAM_RING_LOOP = "PARAM_RING_LOOP";
    public static final String PARAM_RING_LOOPCOUNT = "PARAM_RING_LOOPCOUNT";
    public static final String PARAM_RING_MODE = "PARAM_RING_MODE";
    public static final String PARAM_RING_RSID = "PARAM_RING_RSID";
    public static final String PARAM_RING_URI = "PARAM_RING_URI";
    public static final String PARAM_RING_USERDATA_STRING = "PARAM_RING_USERDATA_STRING";
    public static final String PARAM_SESSIONID = "PARAM_SESSIONID";
    public static final String PARAM_STATUS = "PARAM_STATUS";
    public static final String PARAM_STREAMTYPE = "PARAM_STREAMTYPE";
    public static final String REGISTERAUDIOSESSION_ISREGISTER = "REGISTERAUDIOSESSION_ISREGISTER";
    public static final int RES_ERRCODE_DEVICE_BTCONNCECTED_TIMEOUT = 10;
    public static final int RES_ERRCODE_DEVICE_NOT_VISIABLE = 8;
    public static final int RES_ERRCODE_DEVICE_UNCHANGEABLE = 9;
    public static final int RES_ERRCODE_DEVICE_UNKOWN = 7;
    public static final int RES_ERRCODE_NONE = 0;
    public static final int RES_ERRCODE_RING_NOT_EXIST = 5;
    public static final int RES_ERRCODE_SERVICE_OFF = 1;
    public static final int RES_ERRCODE_STOPRING_INTERRUPT = 4;
    public static final int RES_ERRCODE_VOICECALLPOST_INTERRUPT = 6;
    public static final int RES_ERRCODE_VOICECALL_EXIST = 2;
    public static final int RES_ERRCODE_VOICECALL_NOT_EXIST = 3;
    public static final String VIDEO_CONFIG = "DEVICE_EARPHONE;DEVICE_SPEAKERPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    public static final String VOICECALL_CONFIG = "DEVICE_SPEAKERPHONE;DEVICE_EARPHONE;DEVICE_BLUETOOTHHEADSET;DEVICE_WIREDHEADSET;";
    static int _gHostProcessId = -1;
    static TraeAudioManager _ginstance = null;
    static ReentrantLock _glock = new ReentrantLock();
    static final String[] forceName = new String[]{"FORCE_NONE", "FORCE_SPEAKER", "FORCE_HEADPHONES", "FORCE_BT_SCO", "FORCE_BT_A2DP", "FORCE_WIRED_ACCESSORY", "FORCE_BT_CAR_DOCK", "FORCE_BT_DESK_DOCK", "FORCE_ANALOG_DOCK", "FORCE_NO_BT_A2DP", "FORCE_DIGITAL_DOCK"};
    boolean IsBluetoothA2dpExisted = true;
    int _activeMode = 0;
    AudioManager _am = null;
    TraeAudioSessionHost _audioSessionHost = null;
    d _bluetoothCheck = null;
    Context _context = null;
    e _deviceConfigManager = null;
    ReentrantLock _lock = new ReentrantLock();
    int _modePolicy = -1;
    int _prevMode = 0;
    int _streamType = 0;
    k _switchThread = null;
    f mTraeAudioManagerLooper = null;
    String sessionConnectedDev = DEVICE_NONE;

    abstract class d {
        public abstract void a();

        public abstract void a(Context context, Intent intent);

        public abstract void a(IntentFilter intentFilter);

        public abstract boolean a(Context context, e eVar);

        public abstract boolean b();

        public abstract String c();

        d() {
        }

        public void b(IntentFilter intentFilter) {
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            a(intentFilter);
        }

        public void a(Context context, Intent intent, e eVar) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                StringBuilder stringBuilder;
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("BT ACTION_STATE_CHANGED|   EXTRA_STATE ");
                    stringBuilder.append(a(intExtra));
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("BT ACTION_STATE_CHANGED|   EXTRA_PREVIOUS_STATE ");
                    stringBuilder.append(a(intExtra2));
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (intExtra == 10) {
                    if (QLog.isColorLevel()) {
                        QLog.w("TRAE", 2, "    BT off");
                    }
                    eVar.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                } else if (intExtra == 12 && QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "BT OFF-->ON,Visiable it...");
                }
            } else if ("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction()) && VERSION.SDK_INT < 11) {
            } else {
                if (!"android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) || VERSION.SDK_INT >= 11) {
                    a(context, intent);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public String a(int i) {
            String str;
            switch (i) {
                case 10:
                    str = "STATE_OFF";
                    break;
                case 11:
                    str = "STATE_TURNING_ON";
                    break;
                case 12:
                    str = "STATE_ON";
                    break;
                case 13:
                    str = "STATE_TURNING_OFF";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }

        /* Access modifiers changed, original: 0000 */
        public String b(int i) {
            String str;
            switch (i) {
                case -1:
                    str = "SCO_AUDIO_STATE_ERROR";
                    break;
                case 0:
                    str = "SCO_AUDIO_STATE_DISCONNECTED";
                    break;
                case 1:
                    str = "SCO_AUDIO_STATE_CONNECTED";
                    break;
                case 2:
                    str = "SCO_AUDIO_STATE_CONNECTING";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }

        /* Access modifiers changed, original: 0000 */
        public String c(int i) {
            String str;
            switch (i) {
                case 0:
                    str = "STATE_DISCONNECTED";
                    break;
                case 1:
                    str = "STATE_CONNECTING";
                    break;
                case 2:
                    str = "STATE_CONNECTED";
                    break;
                case 3:
                    str = "STATE_DISCONNECTING";
                    break;
                default:
                    str = "unknow";
                    break;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }
    }

    class e {
        HashMap<String, a> a = new HashMap();
        String b = TraeAudioManager.DEVICE_NONE;
        String c = TraeAudioManager.DEVICE_NONE;
        String d = TraeAudioManager.DEVICE_NONE;
        ReentrantLock e = new ReentrantLock();
        boolean f = false;
        String g = "unknow";

        public class a {
            String a = TraeAudioManager.DEVICE_NONE;
            boolean b = false;
            int c = 0;

            /* JADX WARNING: Missing block: B:9:0x0017, code skipped:
            return false;
     */
            public boolean a(java.lang.String r4, int r5) {
                /*
                r3 = this;
                r0 = 0;
                if (r4 == 0) goto L_0x0017;
            L_0x0003:
                r1 = r4.length();
                if (r1 > 0) goto L_0x000a;
            L_0x0009:
                goto L_0x0017;
            L_0x000a:
                r1 = com.tencent.rtmp.sharp.jni.TraeAudioManager.checkDevName(r4);
                r2 = 1;
                if (r1 == r2) goto L_0x0012;
            L_0x0011:
                return r0;
            L_0x0012:
                r3.a = r4;
                r3.c = r5;
                return r2;
            L_0x0017:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$e$a.a(java.lang.String, int):boolean");
            }

            public String a() {
                return this.a;
            }

            public boolean b() {
                return this.b;
            }

            public int c() {
                return this.c;
            }

            public void a(boolean z) {
                this.b = z;
            }
        }

        public boolean a(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" strConfigs:");
            stringBuilder.append(str);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            int i = 0;
            if (str == null || str.length() <= 0) {
                return false;
            }
            str = str.replace("\n", "").replace("\r", "");
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (str.indexOf(";") < 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(";");
                str = stringBuilder2.toString();
            }
            String[] split = str.split(";");
            if (split == null || 1 > split.length) {
                return false;
            }
            this.e.lock();
            while (i < split.length) {
                a(split[i], i);
                i++;
            }
            this.e.unlock();
            TraeAudioManager.this.printDevices();
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(String str, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" devName:");
            stringBuilder.append(str);
            stringBuilder.append(" priority:");
            stringBuilder.append(i);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            a aVar = new a();
            if (!aVar.a(str, i)) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " err dev init!");
                }
                return false;
            } else if (this.a.containsKey(str)) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "err dev exist!");
                }
                return false;
            } else {
                this.a.put(str, aVar);
                this.f = true;
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" n");
                    stringBuilder.append(e());
                    stringBuilder.append(" 0:");
                    stringBuilder.append(a(0));
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                AudioDeviceInterface.LogTraceExit();
                return true;
            }
        }

        public void a() {
            this.e.lock();
            this.a.clear();
            this.b = TraeAudioManager.DEVICE_NONE;
            this.c = TraeAudioManager.DEVICE_NONE;
            this.d = TraeAudioManager.DEVICE_NONE;
            this.e.unlock();
        }

        public boolean b() {
            this.e.lock();
            boolean z = this.f;
            this.e.unlock();
            return z;
        }

        public void c() {
            this.e.lock();
            this.f = false;
            this.e.unlock();
        }

        public boolean a(String str, boolean z) {
            this.e.lock();
            a aVar = (a) this.a.get(str);
            boolean z2 = true;
            if (aVar == null || aVar.b() == z) {
                z2 = false;
            } else {
                aVar.a(z);
                this.f = true;
                if (QLog.isColorLevel()) {
                    String str2 = "TRAE";
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(" ++setVisible:");
                    stringBuilder.append(str);
                    stringBuilder.append(z ? " Y" : " N");
                    QLog.w(str2, 2, stringBuilder.toString());
                }
            }
            this.e.unlock();
            return z2;
        }

        public void b(String str) {
            if (str == null) {
                this.g = "unknow";
                return;
            }
            if (str.isEmpty()) {
                this.g = "unknow";
            } else {
                this.g = str;
            }
        }

        public String d() {
            return this.g;
        }

        public boolean c(String str) {
            this.e.lock();
            a aVar = (a) this.a.get(str);
            boolean b = aVar != null ? aVar.b() : false;
            this.e.unlock();
            return b;
        }

        public int d(String str) {
            this.e.lock();
            a aVar = (a) this.a.get(str);
            int c = aVar != null ? aVar.c() : -1;
            this.e.unlock();
            return c;
        }

        public int e() {
            this.e.lock();
            int size = this.a.size();
            this.e.unlock();
            return size;
        }

        public String a(int i) {
            a aVar;
            String str = TraeAudioManager.DEVICE_NONE;
            this.e.lock();
            int i2 = 0;
            for (Entry entry : this.a.entrySet()) {
                if (i2 == i) {
                    aVar = (a) entry.getValue();
                    break;
                }
                i2++;
            }
            aVar = null;
            if (aVar != null) {
                str = aVar.a();
            }
            this.e.unlock();
            return str;
        }

        public String e(String str) {
            this.e.lock();
            a aVar = null;
            for (Entry entry : this.a.entrySet()) {
                entry.getKey();
                entry.getValue();
                a aVar2 = (a) entry.getValue();
                if (aVar2 != null) {
                    if (aVar2.b()) {
                        if (!aVar2.a().equals(str)) {
                            if (aVar == null || aVar2.c() >= aVar.c()) {
                                aVar = aVar2;
                            }
                        }
                    }
                }
            }
            this.e.unlock();
            return aVar != null ? aVar.a() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String f() {
            this.e.lock();
            a aVar = null;
            for (Entry entry : this.a.entrySet()) {
                entry.getKey();
                entry.getValue();
                a aVar2 = (a) entry.getValue();
                if (aVar2 != null) {
                    if (aVar2.b()) {
                        if (aVar == null || aVar2.c() >= aVar.c()) {
                            aVar = aVar2;
                        }
                    }
                }
            }
            this.e.unlock();
            return aVar != null ? aVar.a() : TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public String g() {
            this.e.lock();
            a aVar = (a) this.a.get(this.d);
            String str = (aVar == null || !aVar.b()) ? null : this.d;
            this.e.unlock();
            return str;
        }

        public String h() {
            this.e.lock();
            String m = m();
            this.e.unlock();
            return m;
        }

        public String i() {
            this.e.lock();
            String n = n();
            this.e.unlock();
            return n;
        }

        public boolean f(String str) {
            boolean z;
            this.e.lock();
            a aVar = (a) this.a.get(str);
            if (aVar == null || !aVar.b()) {
                z = false;
            } else {
                this.d = str;
                z = true;
            }
            this.e.unlock();
            return z;
        }

        public boolean g(String str) {
            boolean z;
            this.e.lock();
            a aVar = (a) this.a.get(str);
            if (aVar == null || !aVar.b()) {
                z = false;
            } else {
                if (!(this.c == null || this.c.equals(str))) {
                    this.b = this.c;
                }
                this.c = str;
                this.d = "";
                z = true;
            }
            this.e.unlock();
            return z;
        }

        public HashMap<String, Object> j() {
            HashMap hashMap = new HashMap();
            this.e.lock();
            hashMap.put(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, l());
            hashMap.put(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, m());
            hashMap.put(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, n());
            this.e.unlock();
            return hashMap;
        }

        public ArrayList<String> k() {
            ArrayList arrayList = new ArrayList();
            this.e.lock();
            arrayList = l();
            this.e.unlock();
            return arrayList;
        }

        /* Access modifiers changed, original: 0000 */
        public ArrayList<String> l() {
            ArrayList arrayList = new ArrayList();
            for (Entry value : this.a.entrySet()) {
                a aVar = (a) value.getValue();
                if (aVar != null) {
                    if (aVar.b()) {
                        arrayList.add(aVar.a());
                    }
                }
            }
            return arrayList;
        }

        /* Access modifiers changed, original: 0000 */
        public String m() {
            String str = TraeAudioManager.DEVICE_NONE;
            a aVar = (a) this.a.get(this.c);
            return (aVar == null || !aVar.b()) ? str : this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public String n() {
            String str = TraeAudioManager.DEVICE_NONE;
            a aVar = (a) this.a.get(this.b);
            return (aVar == null || !aVar.b()) ? str : this.b;
        }
    }

    class f extends Thread {
        Handler a = null;
        TraeMediaPlayer b = null;
        long c = -1;
        String d = "";
        String e = "";
        final boolean[] f = new boolean[]{false};
        boolean g = false;
        TraeAudioManager h = null;
        String i = "";
        int j = 0;
        int k = 0;
        long l = -1;
        String m = "";
        OnAudioFocusChangeListener n = null;
        int o = 0;

        /* JADX WARNING: Removed duplicated region for block: B:23:? A:{SYNTHETIC, RETURN} */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0060  */
        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0059 */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|(2:8|9)|10|11) */
        public f(com.tencent.rtmp.sharp.jni.TraeAudioManager r7) {
            /*
            r5 = this;
            com.tencent.rtmp.sharp.jni.TraeAudioManager.this = r6;
            r5.<init>();
            r6 = 0;
            r5.a = r6;
            r5.b = r6;
            r0 = -1;
            r5.c = r0;
            r2 = "";
            r5.d = r2;
            r2 = "";
            r5.e = r2;
            r2 = 1;
            r2 = new boolean[r2];
            r3 = 0;
            r2[r3] = r3;
            r5.f = r2;
            r5.g = r3;
            r5.h = r6;
            r2 = "";
            r5.i = r2;
            r5.j = r3;
            r5.k = r3;
            r5.l = r0;
            r0 = "";
            r5.m = r0;
            r5.n = r6;
            r5.o = r3;
            r5.h = r7;
            r6 = android.os.SystemClock.elapsedRealtime();
            r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            r1 = 2;
            if (r0 == 0) goto L_0x0048;
        L_0x0041:
            r0 = "TRAE";
            r2 = "TraeAudioManagerLooper start...";
            com.tencent.rtmp.sharp.jni.QLog.e(r0, r1, r2);
        L_0x0048:
            r5.start();
            r0 = r5.f;
            monitor-enter(r0);
            r2 = r5.f;	 Catch:{ all -> 0x0081 }
            r2 = r2[r3];	 Catch:{ all -> 0x0081 }
            if (r2 != 0) goto L_0x0059;
        L_0x0054:
            r2 = r5.f;	 Catch:{ InterruptedException -> 0x0059 }
            r2.wait();	 Catch:{ InterruptedException -> 0x0059 }
        L_0x0059:
            monitor-exit(r0);	 Catch:{ all -> 0x0081 }
            r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r0 == 0) goto L_0x0080;
        L_0x0060:
            r0 = "TRAE";
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            r3 = "  start used:";
            r2.append(r3);
            r3 = android.os.SystemClock.elapsedRealtime();
            r3 = r3 - r6;
            r2.append(r3);
            r6 = "ms";
            r2.append(r6);
            r6 = r2.toString();
            com.tencent.rtmp.sharp.jni.QLog.e(r0, r1, r6);
        L_0x0080:
            return;
        L_0x0081:
            r6 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0081 }
            throw r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$f.<init>(com.tencent.rtmp.sharp.jni.TraeAudioManager, com.tencent.rtmp.sharp.jni.TraeAudioManager):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0029 */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|7|(2:9|10)|11|12) */
        public void a() {
            /*
            r7 = this;
            r0 = "";
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceEntry(r0);
            r0 = r7.a;
            if (r0 != 0) goto L_0x000a;
        L_0x0009:
            return;
        L_0x000a:
            r0 = android.os.SystemClock.elapsedRealtime();
            r2 = r7.a;
            r2 = r2.getLooper();
            r2.quit();
            r2 = r7.f;
            monitor-enter(r2);
            r3 = r7.f;	 Catch:{ all -> 0x0058 }
            r4 = 0;
            r3 = r3[r4];	 Catch:{ all -> 0x0058 }
            r4 = 1;
            if (r3 != r4) goto L_0x0029;
        L_0x0022:
            r3 = r7.f;	 Catch:{ InterruptedException -> 0x0029 }
            r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            r3.wait(r4);	 Catch:{ InterruptedException -> 0x0029 }
        L_0x0029:
            monitor-exit(r2);	 Catch:{ all -> 0x0058 }
            r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r2 == 0) goto L_0x0051;
        L_0x0030:
            r2 = "TRAE";
            r3 = 2;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "  quit used:";
            r4.append(r5);
            r5 = android.os.SystemClock.elapsedRealtime();
            r5 = r5 - r0;
            r4.append(r5);
            r0 = "ms";
            r4.append(r0);
            r0 = r4.toString();
            com.tencent.rtmp.sharp.jni.QLog.e(r2, r3, r0);
        L_0x0051:
            r0 = 0;
            r7.a = r0;
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceExit();
            return;
        L_0x0058:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0058 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$f.a():void");
        }

        public int a(int i, HashMap<String, Object> hashMap) {
            int i2 = -1;
            if (this.a == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" fail mMsgHandler==null _enabled:");
                stringBuilder.append(this.g ? "Y" : "N");
                stringBuilder.append(" activeMode:");
                stringBuilder.append(TraeAudioManager.this._activeMode);
                stringBuilder.append(" msg:");
                stringBuilder.append(i);
                AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
                return -1;
            }
            if (this.a.sendMessage(Message.obtain(this.a, i, hashMap))) {
                i2 = 0;
            }
            return i2;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(HashMap<String, Object> hashMap) {
            String str = (String) hashMap.get(TraeAudioManager.EXTRA_DATA_DEVICECONFIG);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startService cfg:");
            stringBuilder.append(str);
            TXCLog.w("TRAE", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(" _enabled:");
            stringBuilder2.append(this.g ? "Y" : "N");
            stringBuilder2.append(" activeMode:");
            stringBuilder2.append(TraeAudioManager.this._activeMode);
            stringBuilder2.append(" cfg:");
            stringBuilder2.append(str);
            AudioDeviceInterface.LogTraceEntry(stringBuilder2.toString());
            if (TraeAudioManager.this._context != null) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("   startService:");
                stringBuilder3.append(str);
                QLog.w("TRAE", 2, stringBuilder3.toString());
                if (!(this.g && this.i.equals(str)) && TraeAudioManager.this._activeMode == 0) {
                    if (this.g) {
                        b();
                    }
                    d();
                    AudioManager audioManager = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                    TraeAudioManager.this._deviceConfigManager.a();
                    TraeAudioManager.this._deviceConfigManager.a(str);
                    this.i = str;
                    if (TraeAudioManager.this._am != null) {
                        this.j = TraeAudioManager.this._am.getMode();
                    }
                    this.g = true;
                    if (this.b == null) {
                        this.b = new TraeMediaPlayer(TraeAudioManager.this._context, new com.tencent.rtmp.sharp.jni.TraeMediaPlayer.a() {
                            public void a() {
                                if (QLog.isColorLevel()) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("_ringPlayer onCompletion _activeMode:");
                                    stringBuilder.append(TraeAudioManager.this._activeMode);
                                    stringBuilder.append(" _preRingMode:");
                                    stringBuilder.append(f.this.k);
                                    QLog.w("TRAE", 2, stringBuilder.toString());
                                }
                                HashMap hashMap = new HashMap();
                                hashMap.put(TraeAudioManager.PARAM_ISHOSTSIDE, Boolean.valueOf(true));
                                f.this.a(32783, hashMap);
                                f.this.j();
                            }
                        });
                    }
                    a(this.g);
                    TraeAudioManager.this.updateDeviceStatus();
                    AudioDeviceInterface.LogTraceExit();
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" _enabled:");
            stringBuilder.append(this.g ? "Y" : "N");
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (this.g) {
                if (TraeAudioManager.this._activeMode == 1) {
                    h();
                } else if (TraeAudioManager.this._activeMode == 2) {
                    i();
                }
                if (TraeAudioManager.this._switchThread != null) {
                    if (QLog.isColorLevel()) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("_switchThread:");
                        stringBuilder2.append(TraeAudioManager.this._switchThread.b());
                        QLog.w("TRAE", 2, stringBuilder2.toString());
                    }
                    TraeAudioManager.this._switchThread.f();
                    TraeAudioManager.this._switchThread = null;
                }
                if (this.b != null) {
                    this.b.stopRing();
                }
                this.b = null;
                this.g = false;
                a(this.g);
                if (!(TraeAudioManager.this._am == null || TraeAudioManager.this._context == null)) {
                    try {
                        TraeAudioManager.this.InternalSetMode(0);
                    } catch (Exception unused) {
                    }
                }
                e();
                AudioDeviceInterface.LogTraceExit();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int a(boolean z) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            Intent intent = new Intent();
            intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
            intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_SERVICE_STATE);
            intent.putExtra(TraeAudioManager.NOTIFY_SERVICE_STATE_DATE, z);
            if (TraeAudioManager.this._context != null) {
                TraeAudioManager.this._context.sendBroadcast(intent);
            }
            return 0;
        }

        public void run() {
            AudioDeviceInterface.LogTraceEntry("");
            Looper.prepare();
            this.a = new Handler() {
                public void handleMessage(Message message) {
                    HashMap hashMap;
                    StringBuilder stringBuilder;
                    try {
                        hashMap = (HashMap) message.obj;
                    } catch (Exception unused) {
                        hashMap = null;
                    }
                    if (QLog.isColorLevel()) {
                        String str = "TRAE";
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("TraeAudioManagerLooper msg:");
                        stringBuilder.append(message.what);
                        stringBuilder.append(" _enabled:");
                        stringBuilder.append(f.this.g ? "Y" : "N");
                        QLog.w(str, 2, stringBuilder.toString());
                    }
                    if (message.what == 32772) {
                        f.this.a(hashMap);
                    } else if (f.this.g) {
                        String f;
                        switch (message.what) {
                            case 32773:
                                f.this.b();
                                return;
                            case 32774:
                                f.this.b(hashMap);
                                return;
                            case 32775:
                                TraeAudioManager.this.InternalSessionConnectDevice(hashMap);
                                return;
                            case 32776:
                                TraeAudioManager.this.InternalSessionEarAction(hashMap);
                                return;
                            case 32777:
                                TraeAudioManager.this.InternalSessionIsDeviceChangabled(hashMap);
                                return;
                            case 32778:
                                TraeAudioManager.this.InternalSessionGetConnectedDevice(hashMap);
                                return;
                            case 32779:
                                TraeAudioManager.this.InternalSessionGetConnectingDevice(hashMap);
                                return;
                            case 32780:
                                f.this.c(hashMap);
                                return;
                            case 32781:
                                f.this.d(hashMap);
                                return;
                            case 32782:
                                f.this.e(hashMap);
                                return;
                            case 32783:
                                f.this.f(hashMap);
                                return;
                            case 32784:
                                f.this.g(hashMap);
                                return;
                            case 32785:
                            case 32789:
                                f = TraeAudioManager.this._deviceConfigManager.f();
                                String h = TraeAudioManager.this._deviceConfigManager.h();
                                if (QLog.isColorLevel()) {
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("MESSAGE_AUTO_DEVICELIST_UPDATE  connectedDev:");
                                    stringBuilder2.append(h);
                                    stringBuilder2.append(" highestDev");
                                    stringBuilder2.append(f);
                                    QLog.w("TRAE", 2, stringBuilder2.toString());
                                }
                                if (TraeAudioManager.IsUpdateSceneFlag) {
                                    if (!TraeAudioManager.IsMusicScene || TraeAudioManager.this.IsBluetoothA2dpExisted) {
                                        TraeAudioManager.this.InternalConnectDevice(f, null, true);
                                        return;
                                    } else {
                                        TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.e(TraeAudioManager.DEVICE_BLUETOOTHHEADSET), null, true);
                                        return;
                                    }
                                } else if (f.equals(h)) {
                                    TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                                    return;
                                } else {
                                    TraeAudioManager.this.InternalConnectDevice(f, null, false);
                                    return;
                                }
                            case 32786:
                                f = (String) hashMap.get(TraeAudioManager.PARAM_DEVICE);
                                if (TraeAudioManager.this.InternalConnectDevice(f, null, false) != 0) {
                                    if (QLog.isColorLevel()) {
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append(" plugin dev:");
                                        stringBuilder.append(f);
                                        stringBuilder.append(" sessionConnectedDev:");
                                        stringBuilder.append(TraeAudioManager.this.sessionConnectedDev);
                                        stringBuilder.append(" connected fail,auto switch!");
                                        QLog.w("TRAE", 2, stringBuilder.toString());
                                    }
                                    TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.f(), null, false);
                                    return;
                                }
                                return;
                            case 32787:
                                if (TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this.sessionConnectedDev, null, false) != 0) {
                                    f = (String) hashMap.get(TraeAudioManager.PARAM_DEVICE);
                                    if (QLog.isColorLevel()) {
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append(" plugout dev:");
                                        stringBuilder.append(f);
                                        stringBuilder.append(" sessionConnectedDev:");
                                        stringBuilder.append(TraeAudioManager.this.sessionConnectedDev);
                                        stringBuilder.append(" connected fail,auto switch!");
                                        QLog.w("TRAE", 2, stringBuilder.toString());
                                    }
                                    TraeAudioManager.this.InternalConnectDevice(TraeAudioManager.this._deviceConfigManager.f(), null, false);
                                    return;
                                }
                                return;
                            case 32788:
                                Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
                                if (num != null) {
                                    TraeAudioManager.this._streamType = num.intValue();
                                    f.this.b(num.intValue());
                                    return;
                                } else if (QLog.isColorLevel()) {
                                    QLog.e("TRAE", 2, " MESSAGE_VOICECALL_AUIDOPARAM_CHANGED params.get(PARAM_STREAMTYPE)==null!!");
                                    return;
                                } else {
                                    return;
                                }
                            case 32790:
                                f.this.g();
                                return;
                            case 32791:
                                f.this.a(TraeAudioManager.this._streamType);
                                return;
                            default:
                                return;
                        }
                    } else {
                        if (QLog.isColorLevel()) {
                            QLog.w("TRAE", 2, "******* disabled ,skip msg******");
                        }
                        TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 1);
                    }
                }
            };
            c();
            synchronized (this.f) {
                this.f[0] = true;
                this.f.notify();
            }
            Looper.loop();
            f();
            synchronized (this.f) {
                this.f[0] = false;
                this.f.notify();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        /* Access modifiers changed, original: 0000 */
        public void c() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                TraeAudioManager.this._audioSessionHost = new TraeAudioSessionHost();
                TraeAudioManager.this._deviceConfigManager = new e();
                TraeAudioManager._gHostProcessId = Process.myPid();
                TraeAudioManager.this._am = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                TraeAudioManager.this._bluetoothCheck = TraeAudioManager.this.CreateBluetoothCheck(TraeAudioManager.this._context, TraeAudioManager.this._deviceConfigManager);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.b(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this.h, intentFilter);
            } catch (Exception unused) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "======7");
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        /* Access modifiers changed, original: 0000 */
        public void d() {
            try {
                TraeAudioManager.this._am = (AudioManager) TraeAudioManager.this._context.getSystemService("audio");
                if (TraeAudioManager.this._bluetoothCheck == null) {
                    TraeAudioManager.this._bluetoothCheck = TraeAudioManager.this.CreateBluetoothCheck(TraeAudioManager.this._context, TraeAudioManager.this._deviceConfigManager);
                }
                TraeAudioManager.this._context.unregisterReceiver(this.h);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
                TraeAudioManager.this._bluetoothCheck.b(intentFilter);
                intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                TraeAudioManager.this._context.registerReceiver(this.h, intentFilter);
            } catch (Exception unused) {
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void e() {
            try {
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.a();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this.h);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_REQUEST);
                    TraeAudioManager.this._context.registerReceiver(this.h, intentFilter);
                }
            } catch (Exception unused) {
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            AudioDeviceInterface.LogTraceEntry("");
            try {
                b();
                if (TraeAudioManager.this._bluetoothCheck != null) {
                    TraeAudioManager.this._bluetoothCheck.a();
                }
                TraeAudioManager.this._bluetoothCheck = null;
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.unregisterReceiver(this.h);
                    TraeAudioManager.this._context = null;
                }
                if (TraeAudioManager.this._deviceConfigManager != null) {
                    TraeAudioManager.this._deviceConfigManager.a();
                }
                TraeAudioManager.this._deviceConfigManager = null;
            } catch (Exception unused) {
            }
            AudioDeviceInterface.LogTraceExit();
        }

        /* Access modifiers changed, original: 0000 */
        public int b(HashMap<String, Object> hashMap) {
            Intent intent = new Intent();
            HashMap j = TraeAudioManager.this._deviceConfigManager.j();
            ArrayList arrayList = (ArrayList) j.get(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST);
            String str = (String) j.get(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE);
            String str2 = (String) j.get(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
            intent.putExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, str);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
            intent.putExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, TraeAudioManager.this._deviceConfigManager.d());
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            return 0;
        }

        /* Access modifiers changed, original: 0000 */
        @TargetApi(8)
        public void a(int i) {
            if (VERSION.SDK_INT > 8 && this.n == null) {
                this.n = new OnAudioFocusChangeListener() {
                    @TargetApi(8)
                    public void onAudioFocusChange(int i) {
                        if (QLog.isColorLevel()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("focusChange:");
                            stringBuilder.append(i);
                            stringBuilder.append(" _focusSteamType:");
                            stringBuilder.append(f.this.o);
                            stringBuilder.append(" currMode:");
                            stringBuilder.append(TraeAudioManager.this._am.getMode());
                            stringBuilder.append(" _activeMode:");
                            stringBuilder.append(TraeAudioManager.this._activeMode);
                            QLog.w("TRAE", 2, stringBuilder.toString());
                        }
                        if (i != -1 && i != -2) {
                        }
                    }
                };
                if (TraeAudioManager.this._am != null) {
                    int requestAudioFocus = TraeAudioManager.this._am.requestAudioFocus(this.n, i, 1);
                    if (requestAudioFocus != 1 && QLog.isColorLevel()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("request audio focus fail. ");
                        stringBuilder.append(requestAudioFocus);
                        stringBuilder.append(" mode:");
                        stringBuilder.append(TraeAudioManager.this._am.getMode());
                        QLog.e("TRAE", 2, stringBuilder.toString());
                    }
                    this.o = i;
                    if (QLog.isColorLevel()) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("-------requestAudioFocus _focusSteamType:");
                        stringBuilder2.append(this.o);
                        QLog.w("TRAE", 2, stringBuilder2.toString());
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        @TargetApi(8)
        public void g() {
            if (!(VERSION.SDK_INT <= 8 || TraeAudioManager.this._am == null || this.n == null)) {
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("-------abandonAudioFocus _focusSteamType:");
                    stringBuilder.append(this.o);
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                TraeAudioManager.this._am.abandonAudioFocus(this.n);
                this.n = null;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int c(HashMap<String, Object> hashMap) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (hashMap == null) {
                return -1;
            }
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalVoicecallPreprocess am==null!!");
                }
                return -1;
            } else if (TraeAudioManager.this._activeMode == 1) {
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 2);
                return -1;
            } else {
                this.l = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
                this.m = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
                TraeAudioManager.this._activeMode = 1;
                TraeAudioManager.this._prevMode = TraeAudioManager.this._am.getMode();
                Integer.valueOf(-1);
                Integer.valueOf(0);
                Integer num = (Integer) hashMap.get(TraeAudioManager.PARAM_MODEPOLICY);
                if (num == null) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 2, " params.get(PARAM_MODEPOLICY)==null!!");
                    }
                    TraeAudioManager.this._modePolicy = -1;
                } else {
                    TraeAudioManager.this._modePolicy = num.intValue();
                }
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("  _modePolicy:");
                    stringBuilder2.append(TraeAudioManager.this._modePolicy);
                    QLog.e("TRAE", 2, stringBuilder2.toString());
                }
                Integer num2 = (Integer) hashMap.get(TraeAudioManager.PARAM_STREAMTYPE);
                if (num2 == null) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 2, " params.get(PARAM_STREAMTYPE)==null!!");
                    }
                    TraeAudioManager.this._streamType = 0;
                } else {
                    TraeAudioManager.this._streamType = num2.intValue();
                }
                if (!TraeAudioManager.isCloseSystemAPM(TraeAudioManager.this._modePolicy) || TraeAudioManager.this._activeMode == 2 || TraeAudioManager.this._deviceConfigManager == null) {
                    TraeAudioManager.this.InternalSetMode(TraeAudioManager.getCallAudioMode(TraeAudioManager.this._modePolicy));
                    a(TraeAudioManager.this._streamType);
                } else if (TraeAudioManager.this._deviceConfigManager.h().equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    TraeAudioManager.this.InternalSetMode(0);
                    a(3);
                } else {
                    TraeAudioManager.this.InternalSetMode(3);
                    a(0);
                }
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 0);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int d(HashMap<String, Object> hashMap) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalVoicecallPostprocess am==null!!");
                }
                return -1;
            } else if (TraeAudioManager.this._activeMode != 1) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " not ACTIVE_VOICECALL!!");
                }
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 3);
                return -1;
            } else {
                TraeAudioManager.this._activeMode = 0;
                g();
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int h() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " am==null!!");
                }
                return -1;
            } else if (TraeAudioManager.this._activeMode != 1) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " not ACTIVE_RING!!");
                }
                return -1;
            } else {
                TraeAudioManager.this._activeMode = 0;
                if (TraeAudioManager.this._prevMode != -1) {
                    TraeAudioManager.this.InternalSetMode(TraeAudioManager.this._prevMode);
                }
                HashMap hashMap = new HashMap();
                hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this.l));
                hashMap.put(TraeAudioManager.PARAM_OPERATION, this.m);
                TraeAudioManager.this.sendResBroadcast(new Intent(), hashMap, 6);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int e(HashMap<String, Object> hashMap) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalStartRing am==null!!");
                }
                return -1;
            }
            if (TraeAudioManager.this._activeMode == 2) {
                i();
            }
            try {
                this.c = ((Long) hashMap.get(TraeAudioManager.PARAM_SESSIONID)).longValue();
                this.d = (String) hashMap.get(TraeAudioManager.PARAM_OPERATION);
                this.e = (String) hashMap.get(TraeAudioManager.PARAM_RING_USERDATA_STRING);
                int intValue = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_DATASOURCE)).intValue();
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("  dataSource:");
                    stringBuilder2.append(intValue);
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                int intValue2 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_RSID)).intValue();
                Uri uri = (Uri) hashMap.get(TraeAudioManager.PARAM_RING_URI);
                String str = (String) hashMap.get(TraeAudioManager.PARAM_RING_FILEPATH);
                boolean booleanValue = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_LOOP)).booleanValue();
                int intValue3 = ((Integer) hashMap.get(TraeAudioManager.PARAM_RING_LOOPCOUNT)).intValue();
                boolean booleanValue2 = ((Boolean) hashMap.get(TraeAudioManager.PARAM_RING_MODE)).booleanValue();
                if (TraeAudioManager.this._activeMode != 1) {
                    TraeAudioManager.this._activeMode = 2;
                }
                Intent intent = new Intent();
                intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this.e);
                TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
                this.k = TraeAudioManager.this._am.getMode();
                this.b.playRing(intValue, intValue2, uri, str, booleanValue, intValue3, booleanValue2, TraeAudioManager.this._activeMode == 1, TraeAudioManager.this._streamType);
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" _ringUserdata:");
                    stringBuilder.append(this.e);
                    stringBuilder.append(" DurationMS:");
                    stringBuilder.append(this.b.getDuration());
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (!this.b.hasCall()) {
                    a(this.b.getStreamType());
                }
                b(this.b.getStreamType());
                AudioDeviceInterface.LogTraceExit();
                return 0;
            } catch (Exception unused) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " startRing err params");
                }
                return -1;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public int f(HashMap<String, Object> hashMap) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            stringBuilder.append(" _preRingMode:");
            stringBuilder.append(this.k);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null || this.b == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalStopRing am==null!!");
                }
                return -1;
            }
            this.b.stopRing();
            if (!this.b.hasCall() && TraeAudioManager.this._activeMode == 2) {
                g();
                TraeAudioManager.this._activeMode = 0;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this.e);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        /* Access modifiers changed, original: 0000 */
        public int g(HashMap<String, Object> hashMap) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            stringBuilder.append(" _preRingMode:");
            stringBuilder.append(this.k);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalStopRing am==null!!");
                }
                return -1;
            }
            int streamType;
            if (TraeAudioManager.this._activeMode == 2) {
                streamType = this.b.getStreamType();
            } else {
                streamType = TraeAudioManager.this._streamType;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, streamType);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }

        /* Access modifiers changed, original: 0000 */
        public int b(final int i) {
            if (TraeAudioManager.this._context == null) {
                return -1;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                    intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_STREAMTYPE_UPDATE);
                    intent.putExtra(TraeAudioManager.EXTRA_DATA_STREAMTYPE, i);
                    if (TraeAudioManager.this._context != null) {
                        TraeAudioManager.this._context.sendBroadcast(intent);
                    }
                }
            });
            return 0;
        }

        /* Access modifiers changed, original: 0000 */
        public int i() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" activeMode:");
            stringBuilder.append(TraeAudioManager.this._activeMode);
            stringBuilder.append(" _preRingMode:");
            stringBuilder.append(this.k);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (TraeAudioManager.this._am == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " interruptRing am==null!!");
                }
                return -1;
            } else if (TraeAudioManager.this._activeMode != 2) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " not ACTIVE_RING!!");
                }
                return -1;
            } else {
                this.b.stopRing();
                g();
                TraeAudioManager.this._activeMode = 0;
                HashMap hashMap = new HashMap();
                hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this.c));
                hashMap.put(TraeAudioManager.PARAM_OPERATION, this.d);
                Intent intent = new Intent();
                intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this.e);
                TraeAudioManager.this.sendResBroadcast(intent, hashMap, 4);
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void j() {
            HashMap hashMap = new HashMap();
            hashMap.put(TraeAudioManager.PARAM_SESSIONID, Long.valueOf(this.c));
            hashMap.put(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_RING_COMPLETION);
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.PARAM_RING_USERDATA_STRING, this.e);
            TraeAudioManager.this.sendResBroadcast(intent, hashMap, 0);
        }
    }

    abstract class k extends Thread {
        boolean b = true;
        boolean[] c = new boolean[]{false};
        HashMap<String, Object> d = null;
        long e = 0;

        public abstract void a();

        public abstract String b();

        public abstract void c();

        k() {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" ++switchThread:");
                stringBuilder.append(b());
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
        }

        public void a(HashMap<String, Object> hashMap) {
            this.d = hashMap;
        }

        /* Access modifiers changed, original: 0000 */
        public void e() {
            TraeAudioManager.this._deviceConfigManager.g(b());
            a(0);
        }

        /* Access modifiers changed, original: 0000 */
        public void a(int i) {
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b());
            stringBuilder.append(" err:");
            stringBuilder.append(i);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (this.d == null) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                return;
            }
            TraeAudioManager.this.sessionConnectedDev = TraeAudioManager.this._deviceConfigManager.h();
            Long l = (Long) this.d.get(TraeAudioManager.PARAM_SESSIONID);
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(" sessonID:");
                stringBuilder2.append(l);
                QLog.w("TRAE", 2, stringBuilder2.toString());
            }
            if (l == null || l.longValue() == Long.MIN_VALUE) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "processDeviceConnectRes sid null,don't send res");
                }
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(TraeAudioManager.CONNECTDEVICE_RESULT_DEVICENAME, (String) this.d.get(TraeAudioManager.PARAM_DEVICE));
            if (TraeAudioManager.this.sendResBroadcast(intent, this.d, i) == 0) {
                TraeAudioManager.this.InternalNotifyDeviceListUpdate();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        public void run() {
            AudioDeviceInterface.LogTraceEntry(b());
            TraeAudioManager.this._deviceConfigManager.f(b());
            TraeAudioManager.this.InternalNotifyDeviceChangableUpdate();
            a();
            synchronized (this.c) {
                this.c[0] = true;
                this.c.notify();
            }
            AudioDeviceInterface.LogTraceExit();
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0049 */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|(2:8|9)|10|11) */
        public void f() {
            /*
            r5 = this;
            r0 = r5.b();
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceEntry(r0);
            r0 = 0;
            r5.b = r0;
            r1 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r1 == 0) goto L_0x0035;
        L_0x0010:
            r1 = "TRAE";
            r2 = 2;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = " quit:";
            r3.append(r4);
            r4 = r5.b();
            r3.append(r4);
            r4 = " _running:";
            r3.append(r4);
            r4 = r5.b;
            r3.append(r4);
            r3 = r3.toString();
            com.tencent.rtmp.sharp.jni.QLog.w(r1, r2, r3);
        L_0x0035:
            r5.interrupt();
            r5.c();
            r1 = r5.c;
            monitor-enter(r1);
            r2 = r5.c;	 Catch:{ all -> 0x004e }
            r0 = r2[r0];	 Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x0049;
        L_0x0044:
            r0 = r5.c;	 Catch:{ InterruptedException -> 0x0049 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0049 }
        L_0x0049:
            monitor-exit(r1);	 Catch:{ all -> 0x004e }
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceExit();
            return;
        L_0x004e:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x004e }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$k.f():void");
        }
    }

    @TargetApi(11)
    class a extends d implements ServiceListener {
        Context a = null;
        e b = null;
        BluetoothAdapter c = null;
        BluetoothProfile d = null;
        private final ReentrantLock f = new ReentrantLock();

        public String c() {
            return "BluetoohHeadsetCheck";
        }

        a() {
            super();
        }

        @TargetApi(11)
        public boolean a(Context context, e eVar) {
            AudioDeviceInterface.LogTraceEntry("");
            if (context == null || eVar == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " err ctx==null||_devCfg==null");
                }
                return false;
            }
            this.a = context;
            this.b = eVar;
            this.c = BluetoothAdapter.getDefaultAdapter();
            if (this.c == null) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " err getDefaultAdapter fail!");
                }
                return false;
            }
            this.f.lock();
            try {
                if (this.c.isEnabled() && this.d == null && !this.c.getProfileProxy(this.a, this, 1)) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 2, "BluetoohHeadsetCheck: getProfileProxy HEADSET fail!");
                    }
                    this.f.unlock();
                    return false;
                }
                this.f.unlock();
                AudioDeviceInterface.LogTraceExit();
                return true;
            } catch (Throwable th) {
                this.f.unlock();
            }
        }

        public void a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_profile:");
            stringBuilder.append(this.d);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            this.f.lock();
            try {
                if (this.c != null) {
                    if (this.d != null) {
                        this.c.closeProfileProxy(1, this.d);
                    }
                    this.d = null;
                }
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" closeProfileProxy:e:");
                    stringBuilder2.append(e.getMessage());
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
            } catch (Throwable th) {
                this.f.unlock();
            }
            this.f.unlock();
            AudioDeviceInterface.LogTraceExit();
        }

        public boolean b() {
            this.f.lock();
            boolean z;
            try {
                z = false;
                if (this.d != null) {
                    List connectedDevices = this.d.getConnectedDevices();
                    if (connectedDevices == null) {
                        return z;
                    }
                    if (connectedDevices.size() > 0) {
                        z = true;
                    }
                }
                this.f.unlock();
                return z;
            } finally {
                z = this.f;
                z.unlock();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x00c1 A:{SYNTHETIC, Splitter:B:34:0x00c1} */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00fa A:{SYNTHETIC} */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A:{Catch:{ all -> 0x013f }} */
        @android.annotation.TargetApi(11)
        public void onServiceConnected(int r10, android.bluetooth.BluetoothProfile r11) {
            /*
            r9 = this;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r1 = "_profile:";
            r0.append(r1);
            r1 = r9.d;
            r0.append(r1);
            r1 = " profile:";
            r0.append(r1);
            r0.append(r10);
            r1 = " proxy:";
            r0.append(r1);
            r0.append(r11);
            r0 = r0.toString();
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceEntry(r0);
            r0 = 1;
            if (r10 != r0) goto L_0x0146;
        L_0x0029:
            r10 = r9.f;
            r10.lock();
            r10 = r9.d;	 Catch:{ all -> 0x013f }
            r1 = 0;
            r2 = 2;
            if (r10 == 0) goto L_0x0067;
        L_0x0034:
            r10 = r9.d;	 Catch:{ all -> 0x013f }
            if (r10 == r11) goto L_0x0067;
        L_0x0038:
            r10 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ all -> 0x013f }
            if (r10 == 0) goto L_0x005e;
        L_0x003e:
            r10 = "TRAE";
            r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013f }
            r3.<init>();	 Catch:{ all -> 0x013f }
            r4 = "BluetoohHeadsetCheck: HEADSET Connected proxy:";
            r3.append(r4);	 Catch:{ all -> 0x013f }
            r3.append(r11);	 Catch:{ all -> 0x013f }
            r4 = " _profile:";
            r3.append(r4);	 Catch:{ all -> 0x013f }
            r4 = r9.d;	 Catch:{ all -> 0x013f }
            r3.append(r4);	 Catch:{ all -> 0x013f }
            r3 = r3.toString();	 Catch:{ all -> 0x013f }
            com.tencent.rtmp.sharp.jni.QLog.w(r10, r2, r3);	 Catch:{ all -> 0x013f }
        L_0x005e:
            r10 = r9.c;	 Catch:{ all -> 0x013f }
            r3 = r9.d;	 Catch:{ all -> 0x013f }
            r10.closeProfileProxy(r0, r3);	 Catch:{ all -> 0x013f }
            r9.d = r1;	 Catch:{ all -> 0x013f }
        L_0x0067:
            r9.d = r11;	 Catch:{ all -> 0x013f }
            r10 = r9.d;	 Catch:{ all -> 0x013f }
            if (r10 == 0) goto L_0x0074;
        L_0x006d:
            r10 = r9.d;	 Catch:{ all -> 0x013f }
            r10 = r10.getConnectedDevices();	 Catch:{ all -> 0x013f }
            goto L_0x0075;
        L_0x0074:
            r10 = r1;
        L_0x0075:
            r11 = 0;
            if (r10 == 0) goto L_0x00fd;
        L_0x0078:
            r3 = r9.d;	 Catch:{ all -> 0x013f }
            if (r3 == 0) goto L_0x00fd;
        L_0x007c:
            r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ all -> 0x013f }
            if (r3 == 0) goto L_0x00a6;
        L_0x0082:
            r3 = "TRAE";
            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013f }
            r4.<init>();	 Catch:{ all -> 0x013f }
            r5 = "TRAEBluetoohProxy: HEADSET Connected devs:";
            r4.append(r5);	 Catch:{ all -> 0x013f }
            r5 = r10.size();	 Catch:{ all -> 0x013f }
            r4.append(r5);	 Catch:{ all -> 0x013f }
            r5 = " _profile:";
            r4.append(r5);	 Catch:{ all -> 0x013f }
            r5 = r9.d;	 Catch:{ all -> 0x013f }
            r4.append(r5);	 Catch:{ all -> 0x013f }
            r4 = r4.toString();	 Catch:{ all -> 0x013f }
            com.tencent.rtmp.sharp.jni.QLog.w(r3, r2, r4);	 Catch:{ all -> 0x013f }
        L_0x00a6:
            r3 = 0;
        L_0x00a7:
            r4 = r10.size();	 Catch:{ all -> 0x013f }
            if (r3 >= r4) goto L_0x00fd;
        L_0x00ad:
            r4 = r10.get(r3);	 Catch:{ all -> 0x013f }
            r4 = (android.bluetooth.BluetoothDevice) r4;	 Catch:{ all -> 0x013f }
            r5 = r9.d;	 Catch:{ Exception -> 0x00be }
            if (r5 == 0) goto L_0x00be;
        L_0x00b7:
            r5 = r9.d;	 Catch:{ Exception -> 0x00be }
            r5 = r5.getConnectionState(r4);	 Catch:{ Exception -> 0x00be }
            goto L_0x00bf;
        L_0x00be:
            r5 = 0;
        L_0x00bf:
            if (r5 != r2) goto L_0x00ca;
        L_0x00c1:
            r6 = r9.b;	 Catch:{ all -> 0x013f }
            r7 = r4.getName();	 Catch:{ all -> 0x013f }
            r6.b(r7);	 Catch:{ all -> 0x013f }
        L_0x00ca:
            r6 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ all -> 0x013f }
            if (r6 == 0) goto L_0x00fa;
        L_0x00d0:
            r6 = "TRAE";
            r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x013f }
            r7.<init>();	 Catch:{ all -> 0x013f }
            r8 = "   ";
            r7.append(r8);	 Catch:{ all -> 0x013f }
            r7.append(r3);	 Catch:{ all -> 0x013f }
            r8 = " ";
            r7.append(r8);	 Catch:{ all -> 0x013f }
            r4 = r4.getName();	 Catch:{ all -> 0x013f }
            r7.append(r4);	 Catch:{ all -> 0x013f }
            r4 = " ConnectionState:";
            r7.append(r4);	 Catch:{ all -> 0x013f }
            r7.append(r5);	 Catch:{ all -> 0x013f }
            r4 = r7.toString();	 Catch:{ all -> 0x013f }
            com.tencent.rtmp.sharp.jni.QLog.w(r6, r2, r4);	 Catch:{ all -> 0x013f }
        L_0x00fa:
            r3 = r3 + 1;
            goto L_0x00a7;
        L_0x00fd:
            r10 = r9.f;
            r10.unlock();
            r10 = r9.b;
            if (r10 == 0) goto L_0x0146;
        L_0x0106:
            r10 = com.tencent.rtmp.sharp.jni.TraeAudioManager.this;
            r10 = r10._deviceConfigManager;
            if (r10 == 0) goto L_0x0114;
        L_0x010c:
            r10 = com.tencent.rtmp.sharp.jni.TraeAudioManager.this;
            r10 = r10._deviceConfigManager;
            r1 = r10.d();
        L_0x0114:
            r10 = android.text.TextUtils.isEmpty(r1);
            if (r10 == 0) goto L_0x0122;
        L_0x011a:
            r10 = r9.b;
            r0 = "DEVICE_BLUETOOTHHEADSET";
            r10.a(r0, r11);
            goto L_0x0146;
        L_0x0122:
            r10 = r9.b();
            if (r10 == 0) goto L_0x0137;
        L_0x0128:
            r10 = r9.b;
            r11 = "DEVICE_BLUETOOTHHEADSET";
            r10.a(r11, r0);
            r10 = com.tencent.rtmp.sharp.jni.TraeAudioManager.this;
            r11 = "DEVICE_BLUETOOTHHEADSET";
            r10.checkDevicePlug(r11, r0);
            goto L_0x0146;
        L_0x0137:
            r10 = r9.b;
            r0 = "DEVICE_BLUETOOTHHEADSET";
            r10.a(r0, r11);
            goto L_0x0146;
        L_0x013f:
            r10 = move-exception;
            r11 = r9.f;
            r11.unlock();
            throw r10;
        L_0x0146:
            com.tencent.rtmp.sharp.jni.AudioDeviceInterface.LogTraceExit();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$a.onServiceConnected(int, android.bluetooth.BluetoothProfile):void");
        }

        @TargetApi(11)
        public void onServiceDisconnected(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_profile:");
            stringBuilder.append(this.d);
            stringBuilder.append(" profile:");
            stringBuilder.append(i);
            AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
            if (i == 1) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "TRAEBluetoohProxy: HEADSET Disconnected");
                }
                if (b()) {
                    TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
                this.f.lock();
                try {
                    if (this.d != null) {
                        this.c.closeProfileProxy(1, this.d);
                        this.d = null;
                    }
                    this.f.unlock();
                } catch (Throwable th) {
                    this.f.unlock();
                }
            }
            AudioDeviceInterface.LogTraceExit();
        }

        /* Access modifiers changed, original: 0000 */
        public void a(IntentFilter intentFilter) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" ");
                stringBuilder.append(c());
                stringBuilder.append(" _addAction");
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Context context, Intent intent) {
            int intExtra;
            BluetoothDevice bluetoothDevice;
            StringBuilder stringBuilder;
            if ("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                StringBuilder stringBuilder2;
                intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", -1);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("BT ACTION_CONNECTION_STATE_CHANGED|   EXTRA_CONNECTION_STATE ");
                    stringBuilder2.append(c(intExtra));
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("    EXTRA_PREVIOUS_CONNECTION_STATE ");
                    stringBuilder2.append(c(intExtra2));
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (QLog.isColorLevel()) {
                    String str = "TRAE";
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("    EXTRA_DEVICE ");
                    stringBuilder3.append(bluetoothDevice);
                    stringBuilder3.append(" ");
                    stringBuilder3.append(bluetoothDevice != null ? bluetoothDevice.getName() : " ");
                    QLog.w(str, 2, stringBuilder3.toString());
                }
                if (intExtra == 2) {
                    if (QLog.isColorLevel()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("   dev:");
                        stringBuilder.append(bluetoothDevice.getName());
                        stringBuilder.append(" connected,start sco...");
                        QLog.w("TRAE", 2, stringBuilder.toString());
                    }
                    this.b.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                    this.b.b(bluetoothDevice != null ? bluetoothDevice.getName() : "unkown");
                } else if (intExtra == 0) {
                    this.b.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            } else if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                int intExtra3 = intent.getIntExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", -1);
                bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("BT ACTION_SCO_AUDIO_STATE_UPDATED|   EXTRA_CONNECTION_STATE  dev:");
                    stringBuilder4.append(bluetoothDevice);
                    QLog.w("TRAE", 2, stringBuilder4.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("   EXTRA_SCO_AUDIO_STATE ");
                    stringBuilder.append(b(intExtra));
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("   EXTRA_SCO_AUDIO_PREVIOUS_STATE ");
                    stringBuilder5.append(b(intExtra3));
                    QLog.w("TRAE", 2, stringBuilder5.toString());
                }
            } else if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                int profileConnectionState = defaultAdapter.getProfileConnectionState(2);
                if (profileConnectionState == 0) {
                    QLog.w("TRAE", 2, "BluetoothA2dp STATE_DISCONNECTED");
                    TraeAudioManager.this.IsBluetoothA2dpExisted = false;
                } else if (profileConnectionState != 2) {
                    StringBuilder stringBuilder6 = new StringBuilder();
                    stringBuilder6.append("BluetoothA2dp");
                    stringBuilder6.append(defaultAdapter.getProfileConnectionState(2));
                    QLog.w("TRAE", 2, stringBuilder6.toString());
                } else {
                    QLog.w("TRAE", 2, "BluetoothA2dp STATE_CONNECTED");
                    TraeAudioManager.this.IsBluetoothA2dpExisted = true;
                }
            }
        }
    }

    class b extends d {
        public void a() {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Context context, Intent intent) {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(IntentFilter intentFilter) {
        }

        public boolean a(Context context, e eVar) {
            return true;
        }

        public boolean b() {
            return false;
        }

        public String c() {
            return "BluetoohHeadsetCheckFake";
        }

        b() {
            super();
        }
    }

    class c extends d {
        Class<?> a = null;
        Class<?> b = null;
        Object c = null;
        Method d = null;
        Context e = null;
        e f = null;

        public String c() {
            return "BluetoohHeadsetCheckFor2x";
        }

        c() {
            super();
        }

        public boolean a(Context context, e eVar) {
            AudioDeviceInterface.LogTraceEntry("");
            this.e = context;
            this.f = eVar;
            if (this.e == null || this.f == null) {
                return false;
            }
            try {
                this.a = Class.forName("android.bluetooth.BluetoothHeadset");
            } catch (Exception unused) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset class not found");
                }
            }
            if (this.a == null) {
                return false;
            }
            try {
                this.b = Class.forName("android.bluetooth.BluetoothHeadset$ServiceListener");
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("BTLooperThread BluetoothHeadset.ServiceListener class not found:");
                    stringBuilder.append(e);
                    QLog.e("TRAE", 2, stringBuilder.toString());
                }
            }
            Class cls = this.b;
            try {
                this.d = this.a.getDeclaredMethod("getCurrentHeadset", new Class[0]);
            } catch (NoSuchMethodException unused2) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset method getCurrentHeadset NoSuchMethodException");
                }
            }
            if (this.d == null) {
                return false;
            }
            try {
                this.c = this.a.getConstructor(new Class[]{Context.class, this.b}).newInstance(new Object[]{context, null});
            } catch (IllegalArgumentException unused3) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset getConstructor IllegalArgumentException");
                }
            } catch (InstantiationException unused4) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset getConstructor InstantiationException");
                }
            } catch (IllegalAccessException unused5) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset getConstructor IllegalAccessException");
                }
            } catch (InvocationTargetException unused6) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset getConstructor InvocationTargetException");
                }
            } catch (NoSuchMethodException unused7) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "BTLooperThread BluetoothHeadset getConstructor NoSuchMethodException");
                }
            }
            if (this.c == null) {
                return false;
            }
            this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, b());
            if (b()) {
                this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                TraeAudioManager.this.checkDevicePlug(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
            } else {
                this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
            }
            AudioDeviceInterface.LogTraceExit();
            return true;
        }

        public void a() {
            AudioDeviceInterface.LogTraceEntry("");
            if (this.c != null) {
                Method declaredMethod;
                try {
                    declaredMethod = this.a.getDeclaredMethod("close", new Class[0]);
                } catch (NoSuchMethodException unused) {
                    if (QLog.isColorLevel()) {
                        QLog.e("TRAE", 2, "BTLooperThread _uninitHeadsetfor2x method close NoSuchMethodException");
                    }
                    declaredMethod = null;
                }
                if (declaredMethod != null) {
                    try {
                        declaredMethod.invoke(this.c, new Object[0]);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
                    }
                    this.a = null;
                    this.b = null;
                    this.c = null;
                    this.d = null;
                    AudioDeviceInterface.LogTraceExit();
                }
            }
        }

        /* JADX WARNING: Missing block: B:19:0x003f, code skipped:
            r2 = null;
     */
        public boolean b() {
            /*
            r6 = this;
            r0 = r6.d;
            r1 = 0;
            if (r0 == 0) goto L_0x0067;
        L_0x0005:
            r0 = r6.d;
            if (r0 != 0) goto L_0x000a;
        L_0x0009:
            goto L_0x0067;
        L_0x000a:
            r0 = 2;
            r2 = r6.d;	 Catch:{ IllegalArgumentException -> 0x0032, IllegalAccessException -> 0x0024, InvocationTargetException -> 0x0016 }
            r3 = r6.c;	 Catch:{ IllegalArgumentException -> 0x0032, IllegalAccessException -> 0x0024, InvocationTargetException -> 0x0016 }
            r4 = new java.lang.Object[r1];	 Catch:{ IllegalArgumentException -> 0x0032, IllegalAccessException -> 0x0024, InvocationTargetException -> 0x0016 }
            r2 = r2.invoke(r3, r4);	 Catch:{ IllegalArgumentException -> 0x0032, IllegalAccessException -> 0x0024, InvocationTargetException -> 0x0016 }
            goto L_0x0040;
        L_0x0016:
            r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r2 == 0) goto L_0x003f;
        L_0x001c:
            r2 = "TRAE";
            r3 = "BTLooperThread BluetoothHeadset method getCurrentHeadset InvocationTargetException";
            com.tencent.rtmp.sharp.jni.QLog.w(r2, r0, r3);
            goto L_0x003f;
        L_0x0024:
            r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r2 == 0) goto L_0x003f;
        L_0x002a:
            r2 = "TRAE";
            r3 = "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalAccessException";
            com.tencent.rtmp.sharp.jni.QLog.w(r2, r0, r3);
            goto L_0x003f;
        L_0x0032:
            r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r2 == 0) goto L_0x003f;
        L_0x0038:
            r2 = "TRAE";
            r3 = "BTLooperThread BluetoothHeadset method getCurrentHeadset IllegalArgumentException";
            com.tencent.rtmp.sharp.jni.QLog.w(r2, r0, r3);
        L_0x003f:
            r2 = 0;
        L_0x0040:
            r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
            if (r3 == 0) goto L_0x0063;
        L_0x0046:
            r3 = "TRAE";
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "BTLooperThread BluetoothHeadset method getCurrentHeadset res:";
            r4.append(r5);
            if (r2 == 0) goto L_0x0057;
        L_0x0054:
            r5 = " Y";
            goto L_0x0059;
        L_0x0057:
            r5 = "N";
        L_0x0059:
            r4.append(r5);
            r4 = r4.toString();
            com.tencent.rtmp.sharp.jni.QLog.w(r3, r0, r4);
        L_0x0063:
            if (r2 == 0) goto L_0x0066;
        L_0x0065:
            r1 = 1;
        L_0x0066:
            return r1;
        L_0x0067:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager$c.b():boolean");
        }

        /* Access modifiers changed, original: 0000 */
        public void a(IntentFilter intentFilter) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" ");
                stringBuilder.append(c());
                stringBuilder.append(" _addAction");
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            intentFilter.addAction("android.bluetooth.headset.action.AUDIO_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.action.STATE_CHANGED");
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Context context, Intent intent) {
            int intExtra;
            int intExtra2;
            int intExtra3;
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if ("android.bluetooth.headset.action.AUDIO_STATE_CHANGED".equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                intExtra2 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                intExtra3 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("++ AUDIO_STATE_CHANGED|  STATE ");
                    stringBuilder.append(intExtra);
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("       PREVIOUS_STATE ");
                    stringBuilder2.append(intExtra2);
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("       AUDIO_STATE ");
                    stringBuilder2.append(intExtra3);
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (intExtra3 == 2) {
                    this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                } else if (intExtra3 == 0) {
                    this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            } else if ("android.bluetooth.headset.action.STATE_CHANGED".equals(intent.getAction())) {
                intExtra = intent.getIntExtra("android.bluetooth.headset.extra.STATE", -2);
                intExtra2 = intent.getIntExtra("android.bluetooth.headset.extra.PREVIOUS_STATE", -2);
                intExtra3 = intent.getIntExtra("android.bluetooth.headset.extra.AUDIO_STATE", -2);
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("++ STATE_CHANGED|  STATE ");
                    stringBuilder.append(intExtra);
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("       PREVIOUS_STATE ");
                    stringBuilder2.append(intExtra2);
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (QLog.isColorLevel()) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("       AUDIO_STATE ");
                    stringBuilder2.append(intExtra3);
                    QLog.w("TRAE", 2, stringBuilder2.toString());
                }
                if (intExtra3 == 2) {
                    this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, true);
                } else if (intExtra3 == 0) {
                    this.f.a(TraeAudioManager.DEVICE_BLUETOOTHHEADSET, false);
                }
            }
        }
    }

    class g extends k {
        public String b() {
            return TraeAudioManager.DEVICE_BLUETOOTHHEADSET;
        }

        g() {
            super();
        }

        public void a() {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "connect bluetoothHeadset: do nothing");
            }
            e();
        }

        @TargetApi(8)
        public void c() {
            if (TraeAudioManager.this._am != null) {
                d();
            }
        }

        /* Access modifiers changed, original: 0000 */
        @TargetApi(8)
        public void d() {
            if (VERSION.SDK_INT > 8) {
                TraeAudioManager.this._am.stopBluetoothSco();
            }
            TraeAudioManager.this._am.setBluetoothScoOn(false);
        }
    }

    class h extends k {
        public String b() {
            return TraeAudioManager.DEVICE_EARPHONE;
        }

        public void c() {
        }

        h() {
            super();
        }

        public void a() {
            if (TraeAudioManager.IsUpdateSceneFlag) {
                e();
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "connect earphone: do nothing");
            }
        }
    }

    class i extends k {
        public String b() {
            return TraeAudioManager.DEVICE_WIREDHEADSET;
        }

        public void c() {
        }

        i() {
            super();
        }

        public void a() {
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                TraeAudioManager.this._am.setWiredHeadsetOn(true);
            }
            e();
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "connect headset: do nothing");
            }
        }
    }

    class j extends k {
        public String b() {
            return TraeAudioManager.DEVICE_SPEAKERPHONE;
        }

        public void c() {
        }

        j() {
            super();
        }

        public void a() {
            if (!TraeAudioManager.IsMusicScene && TraeAudioManager.IsUpdateSceneFlag) {
                e();
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "connect speakerPhone: do nothing");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSessionEarAction(HashMap<String, Object> hashMap) {
        return 0;
    }

    public static boolean checkDevName(String str) {
        if (str == null) {
            return false;
        }
        if (DEVICE_SPEAKERPHONE.equals(str) || DEVICE_EARPHONE.equals(str) || DEVICE_WIREDHEADSET.equals(str) || DEVICE_BLUETOOTHHEADSET.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isHandfree(String str) {
        if (checkDevName(str) && DEVICE_SPEAKERPHONE.equals(str)) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void printDevices() {
        StringBuilder stringBuilder;
        AudioDeviceInterface.LogTraceEntry("");
        int e = this._deviceConfigManager.e();
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("   ConnectedDevice:");
            stringBuilder.append(this._deviceConfigManager.h());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("   ConnectingDevice:");
            stringBuilder.append(this._deviceConfigManager.g());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("   prevConnectedDevice:");
            stringBuilder.append(this._deviceConfigManager.i());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("   AHPDevice:");
            stringBuilder.append(this._deviceConfigManager.f());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("   deviceNamber:");
            stringBuilder.append(e);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        int i = 0;
        for (int i2 = 0; i2 < e; i2++) {
            String a = this._deviceConfigManager.a(i2);
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("      ");
                stringBuilder2.append(i2);
                stringBuilder2.append(" devName:");
                stringBuilder2.append(a);
                stringBuilder2.append(" Visible:");
                stringBuilder2.append(this._deviceConfigManager.c(a));
                stringBuilder2.append(" Priority:");
                stringBuilder2.append(this._deviceConfigManager.d(a));
                QLog.w("TRAE", 2, stringBuilder2.toString());
            }
        }
        String[] strArr = (String[]) this._deviceConfigManager.k().toArray(new String[0]);
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("   AvailableNamber:");
            stringBuilder3.append(strArr.length);
            QLog.w("TRAE", 2, stringBuilder3.toString());
        }
        while (i < strArr.length) {
            String str = strArr[i];
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("      ");
                stringBuilder4.append(i);
                stringBuilder4.append(" devName:");
                stringBuilder4.append(str);
                stringBuilder4.append(" Visible:");
                stringBuilder4.append(this._deviceConfigManager.c(str));
                stringBuilder4.append(" Priority:");
                stringBuilder4.append(this._deviceConfigManager.d(str));
                QLog.w("TRAE", 2, stringBuilder4.toString());
            }
            i++;
        }
        AudioDeviceInterface.LogTraceExit();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051 A:{RETURN} */
    static boolean isCloseSystemAPM(int r3) {
        /*
        r0 = 0;
        r1 = -1;
        if (r3 == r1) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r3 = android.os.Build.MANUFACTURER;
        r1 = "Xiaomi";
        r3 = r3.equals(r1);
        r1 = 1;
        if (r3 == 0) goto L_0x003c;
    L_0x0010:
        r3 = android.os.Build.MODEL;
        r2 = "MI 2";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x001b;
    L_0x001a:
        return r1;
    L_0x001b:
        r3 = android.os.Build.MODEL;
        r2 = "MI 2A";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x0026;
    L_0x0025:
        return r1;
    L_0x0026:
        r3 = android.os.Build.MODEL;
        r2 = "MI 2S";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x0031;
    L_0x0030:
        return r1;
    L_0x0031:
        r3 = android.os.Build.MODEL;
        r2 = "MI 2SC";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x0051;
    L_0x003b:
        return r1;
    L_0x003c:
        r3 = android.os.Build.MANUFACTURER;
        r2 = "samsung";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x0051;
    L_0x0046:
        r3 = android.os.Build.MODEL;
        r2 = "SCH-I959";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x0051;
    L_0x0050:
        return r1;
    L_0x0051:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager.isCloseSystemAPM(int):boolean");
    }

    public static boolean IsEabiLowVersionByAbi(String str) {
        if (str == null) {
            return true;
        }
        if (str.contains("x86") || str.contains("mips")) {
            return false;
        }
        return str.equalsIgnoreCase("armeabi") || !str.equalsIgnoreCase("armeabi-v7a");
    }

    static boolean IsEabiLowVersion() {
        String str = Build.CPU_ABI;
        String str2 = IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        if (VERSION.SDK_INT >= 8) {
            try {
                str2 = (String) Build.class.getDeclaredField("CPU_ABI2").get(null);
            } catch (Exception unused) {
                return IsEabiLowVersionByAbi(str);
            }
        }
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("IsEabiVersion CPU_ABI:");
            stringBuilder.append(str);
            stringBuilder.append(" CPU_ABI2:");
            stringBuilder.append(str2);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return IsEabiLowVersionByAbi(str) && IsEabiLowVersionByAbi(str2);
    }

    static int getAudioSource(int i) {
        int i2 = 0;
        if (IsMusicScene) {
            return 0;
        }
        StringBuilder stringBuilder;
        if (IsEabiLowVersion()) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("[Config] armeabi low Version, getAudioSource _audioSourcePolicy:");
                stringBuilder.append(i);
                stringBuilder.append(" source:");
                stringBuilder.append(0);
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            return 0;
        }
        int i3 = VERSION.SDK_INT;
        if (i >= 0) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("[Config] getAudioSource _audioSourcePolicy:");
                stringBuilder2.append(i);
                stringBuilder2.append(" source:");
                stringBuilder2.append(i);
                QLog.w("TRAE", 2, stringBuilder2.toString());
            }
            return i;
        }
        if (i3 >= 11) {
            i2 = 7;
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("[Config] getAudioSource _audioSourcePolicy:");
            stringBuilder.append(i);
            stringBuilder.append(" source:");
            stringBuilder.append(i2);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return i2;
    }

    static int getAudioStreamType(int i) {
        if (IsMusicScene) {
            return 3;
        }
        StringBuilder stringBuilder;
        if (IsEabiLowVersion()) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("[Config] armeabi low Version, getAudioStreamType audioStreamTypePolicy:");
                stringBuilder.append(i);
                stringBuilder.append(" streamType:");
                stringBuilder.append(3);
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            return 3;
        }
        int i2 = i >= 0 ? i : VERSION.SDK_INT >= 9 ? 0 : 3;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("[Config] getAudioStreamType audioStreamTypePolicy:");
            stringBuilder.append(i);
            stringBuilder.append(" streamType:");
            stringBuilder.append(i2);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return i2;
    }

    static int getCallAudioMode(int i) {
        int i2 = 0;
        if (IsMusicScene) {
            return 0;
        }
        StringBuilder stringBuilder;
        if (IsEabiLowVersion()) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("[Config] armeabi low Version, getCallAudioMode modePolicy:");
                stringBuilder.append(i);
                stringBuilder.append(" mode:");
                stringBuilder.append(0);
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            return 0;
        }
        int i3 = VERSION.SDK_INT;
        if (i >= 0) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("[Config] getCallAudioMode modePolicy:");
                stringBuilder2.append(i);
                stringBuilder2.append(" mode:");
                stringBuilder2.append(i);
                QLog.w("TRAE", 2, stringBuilder2.toString());
            }
            return i;
        }
        if (i3 >= 11) {
            i2 = 3;
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("[Config] getCallAudioMode _modePolicy:");
            stringBuilder.append(i);
            stringBuilder.append(" mode:");
            stringBuilder.append(i2);
            stringBuilder.append("facturer:");
            stringBuilder.append(Build.MANUFACTURER);
            stringBuilder.append(" model:");
            stringBuilder.append(Build.MODEL);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return i2;
    }

    /* Access modifiers changed, original: 0000 */
    public void updateDeviceStatus() {
        int e = this._deviceConfigManager.e();
        for (int i = 0; i < e; i++) {
            boolean a;
            StringBuilder stringBuilder;
            String a2 = this._deviceConfigManager.a(i);
            if (a2 != null) {
                if (a2.equals(DEVICE_BLUETOOTHHEADSET)) {
                    if (this._bluetoothCheck == null) {
                        a = this._deviceConfigManager.a(a2, false);
                    } else {
                        a = this._deviceConfigManager.a(a2, this._bluetoothCheck.b());
                    }
                } else if (a2.equals(DEVICE_WIREDHEADSET)) {
                    a = this._deviceConfigManager.a(a2, this._am.isWiredHeadsetOn());
                } else if (a2.equals(DEVICE_SPEAKERPHONE)) {
                    this._deviceConfigManager.a(a2, true);
                }
                if (a && QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("pollUpdateDevice dev:");
                    stringBuilder.append(a2);
                    stringBuilder.append(" Visible:");
                    stringBuilder.append(this._deviceConfigManager.c(a2));
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
            }
            a = false;
            stringBuilder = new StringBuilder();
            stringBuilder.append("pollUpdateDevice dev:");
            stringBuilder.append(a2);
            stringBuilder.append(" Visible:");
            stringBuilder.append(this._deviceConfigManager.c(a2));
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        checkAutoDeviceListUpdate();
    }

    /* Access modifiers changed, original: 0000 */
    public void _updateEarphoneVisable() {
        if (this._deviceConfigManager.c(DEVICE_WIREDHEADSET)) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, " detected headset plugin,so disable earphone");
            }
            this._deviceConfigManager.a(DEVICE_EARPHONE, false);
            return;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 2, " detected headset plugout,so enable earphone");
        }
        this._deviceConfigManager.a(DEVICE_EARPHONE, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void checkAutoDeviceListUpdate() {
        if (this._deviceConfigManager.b()) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "checkAutoDeviceListUpdate got update!");
            }
            _updateEarphoneVisable();
            this._deviceConfigManager.c();
            internalSendMessage(32785, new HashMap());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void checkDevicePlug(String str, boolean z) {
        if (this._deviceConfigManager.b()) {
            StringBuilder stringBuilder;
            if (QLog.isColorLevel()) {
                String str2 = "TRAE";
                stringBuilder = new StringBuilder();
                stringBuilder.append("checkDevicePlug got update dev:");
                stringBuilder.append(str);
                stringBuilder.append(z ? " piugin" : " plugout");
                stringBuilder.append(" connectedDev:");
                stringBuilder.append(this._deviceConfigManager.h());
                QLog.w(str2, 2, stringBuilder.toString());
            }
            _updateEarphoneVisable();
            this._deviceConfigManager.c();
            HashMap hashMap;
            if (z) {
                hashMap = new HashMap();
                hashMap.put(PARAM_DEVICE, str);
                internalSendMessage(32786, hashMap);
                return;
            }
            String h = this._deviceConfigManager.h();
            if (h.equals(str) || h.equals(DEVICE_NONE)) {
                hashMap = new HashMap();
                hashMap.put(PARAM_DEVICE, str);
                internalSendMessage(32787, hashMap);
                return;
            }
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(" ---No switch,plugout:");
                stringBuilder.append(str);
                stringBuilder.append(" connectedDev:");
                stringBuilder.append(h);
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            internalSendMessage(32785, new HashMap());
        }
    }

    public static int SetSpeakerForTest(Context context, boolean z) {
        int InternalSetSpeaker;
        _glock.lock();
        if (_ginstance != null) {
            InternalSetSpeaker = _ginstance.InternalSetSpeaker(context, z);
        } else {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "TraeAudioManager|static SetSpeakerForTest|null == _ginstance");
            }
            InternalSetSpeaker = -1;
        }
        _glock.unlock();
        return InternalSetSpeaker;
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSetSpeaker(Context context, boolean z) {
        int i = -1;
        if (context == null) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "Could not InternalSetSpeaker - no context");
            }
            return -1;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "Could not InternalSetSpeaker - no audio manager");
            }
            return -1;
        }
        StringBuilder stringBuilder;
        if (QLog.isColorLevel()) {
            String str = "TRAE";
            stringBuilder = new StringBuilder();
            stringBuilder.append("InternalSetSpeaker entry:speaker:");
            stringBuilder.append(audioManager.isSpeakerphoneOn() ? "Y" : "N");
            stringBuilder.append("-->:");
            stringBuilder.append(z ? "Y" : "N");
            QLog.w(str, 2, stringBuilder.toString());
        }
        if (isCloseSystemAPM(this._modePolicy) && this._activeMode != 2) {
            return InternalSetSpeakerSpe(audioManager, z);
        }
        if (audioManager.isSpeakerphoneOn() != z) {
            audioManager.setSpeakerphoneOn(z);
        }
        if (audioManager.isSpeakerphoneOn() == z) {
            i = 0;
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InternalSetSpeaker exit:");
            stringBuilder.append(z);
            stringBuilder.append(" res:");
            stringBuilder.append(i);
            stringBuilder.append(" mode:");
            stringBuilder.append(audioManager.getMode());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return i;
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSetSpeakerSpe(AudioManager audioManager, boolean z) {
        StringBuilder stringBuilder;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InternalSetSpeakerSpe fac:");
            stringBuilder.append(Build.MANUFACTURER);
            stringBuilder.append(" model:");
            stringBuilder.append(Build.MODEL);
            stringBuilder.append(" st:");
            stringBuilder.append(this._streamType);
            stringBuilder.append(" media_force_use:");
            stringBuilder.append(getForceUse(1));
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        int i = 0;
        if (z) {
            InternalSetMode(0);
            audioManager.setSpeakerphoneOn(true);
            setForceUse(1, 1);
        } else {
            InternalSetMode(3);
            audioManager.setSpeakerphoneOn(false);
            setForceUse(1, 0);
        }
        if (audioManager.isSpeakerphoneOn() != z) {
            i = -1;
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InternalSetSpeakerSpe exit:");
            stringBuilder.append(z);
            stringBuilder.append(" res:");
            stringBuilder.append(i);
            stringBuilder.append(" mode:");
            stringBuilder.append(audioManager.getMode());
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return i;
    }

    /* Access modifiers changed, original: 0000 */
    public void InternalSetMode(int i) {
        StringBuilder stringBuilder;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("SetMode entry:");
            stringBuilder.append(i);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (this._am == null) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("setMode:");
                stringBuilder.append(i);
                stringBuilder.append(" fail am=null");
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            return;
        }
        this._am.setMode(i);
        if (QLog.isColorLevel()) {
            String str = "TRAE";
            stringBuilder = new StringBuilder();
            stringBuilder.append("setMode:");
            stringBuilder.append(i);
            stringBuilder.append(this._am.getMode() != i ? "fail" : "success");
            QLog.w(str, 2, stringBuilder.toString());
        }
    }

    public static int registerAudioSession(boolean z, long j, Context context) {
        int i;
        _glock.lock();
        if (_ginstance != null) {
            if (z) {
                _ginstance._audioSessionHost.add(j, context);
            } else {
                _ginstance._audioSessionHost.remove(j);
            }
            i = 0;
        } else {
            i = -1;
        }
        _glock.unlock();
        return i;
    }

    public static int sendMessage(int i, HashMap<String, Object> hashMap) {
        _glock.lock();
        i = _ginstance != null ? _ginstance.internalSendMessage(i, hashMap) : -1;
        _glock.unlock();
        return i;
    }

    public static int init(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TraeAudioManager init _ginstance:");
        stringBuilder.append(_ginstance);
        TXCLog.w("TRAE", stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" _ginstance:");
        stringBuilder2.append(_ginstance);
        AudioDeviceInterface.LogTraceEntry(stringBuilder2.toString());
        _glock.lock();
        if (_ginstance == null) {
            _ginstance = new TraeAudioManager(context);
        }
        _glock.unlock();
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    public static void uninit() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TraeAudioManager uninit _ginstance:");
        stringBuilder.append(_ginstance);
        TXCLog.w("TRAE", stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" _ginstance:");
        stringBuilder2.append(_ginstance);
        AudioDeviceInterface.LogTraceEntry(stringBuilder2.toString());
        _glock.lock();
        if (_ginstance != null) {
            _ginstance.release();
            _ginstance = null;
        }
        _glock.unlock();
        AudioDeviceInterface.LogTraceExit();
    }

    TraeAudioManager(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" context:");
        stringBuilder.append(context);
        AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
        if (context != null) {
            this._context = context;
            this.mTraeAudioManagerLooper = new f(this);
            f fVar = this.mTraeAudioManagerLooper;
            AudioDeviceInterface.LogTraceExit();
        }
    }

    public void release() {
        AudioDeviceInterface.LogTraceEntry("");
        if (this.mTraeAudioManagerLooper != null) {
            this.mTraeAudioManagerLooper.a();
            this.mTraeAudioManagerLooper = null;
        }
        AudioDeviceInterface.LogTraceExit();
    }

    public void onReceive(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        if (intent2 == null || context2 == null) {
            if (QLog.isColorLevel()) {
                QLog.d("TRAE", 2, "onReceive intent or context is null!");
            }
            return;
        }
        try {
            String action = intent.getAction();
            String stringExtra = intent2.getStringExtra(PARAM_OPERATION);
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("TraeAudioManager|onReceive::Action:");
                stringBuilder.append(intent.getAction());
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            if (this._deviceConfigManager == null) {
                if (QLog.isColorLevel()) {
                    QLog.d("TRAE", 2, "_deviceConfigManager null!");
                }
                return;
            }
            boolean c = this._deviceConfigManager.c(DEVICE_WIREDHEADSET);
            boolean c2 = this._deviceConfigManager.c(DEVICE_BLUETOOTHHEADSET);
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                onHeadsetPlug(context, intent);
                if (!c && this._deviceConfigManager.c(DEVICE_WIREDHEADSET)) {
                    checkDevicePlug(DEVICE_WIREDHEADSET, true);
                }
                if (c && !this._deviceConfigManager.c(DEVICE_WIREDHEADSET)) {
                    checkDevicePlug(DEVICE_WIREDHEADSET, false);
                }
            } else if (!"android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                if (ACTION_TRAEAUDIOMANAGER_REQUEST.equals(action)) {
                    if (QLog.isColorLevel()) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("   OPERATION:");
                        stringBuilder2.append(stringExtra);
                        QLog.w("TRAE", 2, stringBuilder2.toString());
                    }
                    if (OPERATION_REGISTERAUDIOSESSION.equals(stringExtra)) {
                        registerAudioSession(intent2.getBooleanExtra(REGISTERAUDIOSESSION_ISREGISTER, false), intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), context2);
                    } else if (OPERATION_STARTSERVICE.equals(stringExtra)) {
                        startService(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent2.getStringExtra(EXTRA_DATA_DEVICECONFIG));
                    } else if (OPERATION_STOPSERVICE.equals(stringExtra)) {
                        stopService(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_GETDEVICELIST.equals(stringExtra)) {
                        getDeviceList(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_GETSTREAMTYPE.equals(stringExtra)) {
                        getStreamType(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_CONNECTDEVICE.equals(stringExtra)) {
                        connectDevice(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent2.getStringExtra(CONNECTDEVICE_DEVICENAME));
                    } else if (OPERATION_CONNECT_HIGHEST_PRIORITY_DEVICE.equals(stringExtra)) {
                        connectHighestPriorityDevice(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_EARACTION.equals(stringExtra)) {
                        earAction(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent2.getIntExtra(EXTRA_EARACTION, -1));
                    } else if (OPERATION_ISDEVICECHANGABLED.equals(stringExtra)) {
                        isDeviceChangabled(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_GETCONNECTEDDEVICE.equals(stringExtra)) {
                        getConnectedDevice(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_GETCONNECTINGDEVICE.equals(stringExtra)) {
                        getConnectingDevice(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_VOICECALL_PREPROCESS.equals(stringExtra)) {
                        voicecallPreprocess(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent2.getIntExtra(PARAM_MODEPOLICY, -1), intent2.getIntExtra(PARAM_STREAMTYPE, -1));
                    } else if (OPERATION_VOICECALL_POSTPROCESS.equals(stringExtra)) {
                        voicecallPostprocess(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    } else if (OPERATION_VOICECALL_AUDIOPARAM_CHANGED.equals(stringExtra)) {
                        voiceCallAudioParamChanged(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intent2.getIntExtra(PARAM_MODEPOLICY, -1), intent2.getIntExtra(PARAM_STREAMTYPE, -1));
                    } else if (OPERATION_STARTRING.equals(stringExtra)) {
                        int intExtra = intent2.getIntExtra(PARAM_RING_DATASOURCE, -1);
                        int intExtra2 = intent2.getIntExtra(PARAM_RING_RSID, -1);
                        Uri uri = (Uri) intent2.getParcelableExtra(PARAM_RING_URI);
                        String stringExtra2 = intent2.getStringExtra(PARAM_RING_FILEPATH);
                        boolean booleanExtra = intent2.getBooleanExtra(PARAM_RING_LOOP, false);
                        String stringExtra3 = intent2.getStringExtra(PARAM_RING_USERDATA_STRING);
                        startRing(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false, intExtra, intExtra2, uri, stringExtra2, booleanExtra, intent2.getIntExtra(PARAM_RING_LOOPCOUNT, 1), stringExtra3, intent2.getBooleanExtra(PARAM_RING_MODE, false));
                    } else if (OPERATION_STOPRING.equals(stringExtra)) {
                        stopRing(stringExtra, intent2.getLongExtra(PARAM_SESSIONID, Long.MIN_VALUE), false);
                    }
                } else if (this._deviceConfigManager != null) {
                    if (this._bluetoothCheck != null) {
                        this._bluetoothCheck.a(context2, intent2, this._deviceConfigManager);
                    }
                    if (!c2 && this._deviceConfigManager.c(DEVICE_BLUETOOTHHEADSET)) {
                        checkDevicePlug(DEVICE_BLUETOOTHHEADSET, true);
                    }
                    if (c2 && !this._deviceConfigManager.c(DEVICE_BLUETOOTHHEADSET)) {
                        checkDevicePlug(DEVICE_BLUETOOTHHEADSET, false);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onHeadsetPlug(Context context, Intent intent) {
        StringBuilder stringBuilder;
        String str = "";
        String stringExtra = intent.getStringExtra("name");
        if (stringExtra == null) {
            stringExtra = "unkonw";
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(" [");
        stringBuilder2.append(stringExtra);
        stringBuilder2.append("] ");
        str = stringBuilder2.toString();
        int intExtra = intent.getIntExtra("state", -1);
        if (intExtra != -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(intExtra == 0 ? "unplugged" : "plugged");
            str = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" mic:");
        str = stringBuilder.toString();
        int intExtra2 = intent.getIntExtra("microphone", -1);
        boolean z = true;
        if (intExtra2 != -1) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(intExtra2 == 1 ? "Y" : "unkown");
            str = stringBuilder2.toString();
        }
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("onHeadsetPlug:: ");
            stringBuilder3.append(str);
            QLog.w("TRAE", 2, stringBuilder3.toString());
        }
        e eVar = this._deviceConfigManager;
        String str2 = DEVICE_WIREDHEADSET;
        if (1 != intExtra) {
            z = false;
        }
        eVar.a(str2, z);
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 2, "onHeadsetPlug exit");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int internalSendMessage(int i, HashMap<String, Object> hashMap) {
        return this.mTraeAudioManagerLooper != null ? this.mTraeAudioManagerLooper.a(i, hashMap) : -1;
    }

    static int getDeviceList(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32774, hashMap);
    }

    static int getStreamType(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32784, hashMap);
    }

    static int startService(String str, long j, boolean z, String str2) {
        if (str2.length() <= 0) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_DATA_DEVICECONFIG, str2);
        return sendMessage(32772, hashMap);
    }

    static int stopService(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32773, hashMap);
    }

    static int connectDevice(String str, long j, boolean z, String str2) {
        if (str2 == null) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(CONNECTDEVICE_DEVICENAME, str2);
        hashMap.put(PARAM_DEVICE, str2);
        return sendMessage(32775, hashMap);
    }

    static int connectHighestPriorityDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32789, hashMap);
    }

    static int earAction(String str, long j, boolean z, int i) {
        if (i != 0 && i != 1) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(EXTRA_EARACTION, Integer.valueOf(i));
        return sendMessage(32776, hashMap);
    }

    static int isDeviceChangabled(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32777, hashMap);
    }

    static int getConnectedDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32778, hashMap);
    }

    static int getConnectingDevice(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32779, hashMap);
    }

    static int voicecallPreprocess(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(32780, hashMap);
    }

    static int voicecallPostprocess(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32781, hashMap);
    }

    static int voiceCallAudioParamChanged(String str, long j, boolean z, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_MODEPOLICY, Integer.valueOf(i));
        hashMap.put(PARAM_STREAMTYPE, Integer.valueOf(i2));
        return sendMessage(32788, hashMap);
    }

    static int startRing(String str, long j, boolean z, int i, int i2, Uri uri, String str2, boolean z2, int i3, String str3, boolean z3) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        hashMap.put(PARAM_RING_DATASOURCE, Integer.valueOf(i));
        hashMap.put(PARAM_RING_RSID, Integer.valueOf(i2));
        hashMap.put(PARAM_RING_URI, uri);
        hashMap.put(PARAM_RING_FILEPATH, str2);
        hashMap.put(PARAM_RING_LOOP, Boolean.valueOf(z2));
        hashMap.put(PARAM_RING_LOOPCOUNT, Integer.valueOf(i3));
        hashMap.put(PARAM_RING_MODE, Boolean.valueOf(z3));
        hashMap.put(PARAM_RING_USERDATA_STRING, str3);
        return sendMessage(32782, hashMap);
    }

    static int stopRing(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32783, hashMap);
    }

    static int requestReleaseAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32790, hashMap);
    }

    static int recoverAudioFocus(String str, long j, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(PARAM_SESSIONID, Long.valueOf(j));
        hashMap.put(PARAM_OPERATION, str);
        hashMap.put(PARAM_ISHOSTSIDE, Boolean.valueOf(z));
        return sendMessage(32791, hashMap);
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSessionConnectDevice(HashMap<String, Object> hashMap) {
        AudioDeviceInterface.LogTraceEntry("");
        if (hashMap == null || this._context == null) {
            return -1;
        }
        if (IsMusicScene) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "MusicScene: InternalSessionConnectDevice failed");
            }
            return -1;
        }
        String str = (String) hashMap.get(PARAM_DEVICE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ConnectDevice: ");
        stringBuilder.append(str);
        TXCLog.w("TRAE", stringBuilder.toString());
        boolean InternalIsDeviceChangeable = InternalIsDeviceChangeable();
        int i = !checkDevName(str) ? 7 : !this._deviceConfigManager.c(str) ? 8 : !InternalIsDeviceChangeable ? 9 : 0;
        if (QLog.isColorLevel()) {
            String str2 = "TRAE";
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("sessonID:");
            stringBuilder2.append((Long) hashMap.get(PARAM_SESSIONID));
            stringBuilder2.append(" devName:");
            stringBuilder2.append(str);
            stringBuilder2.append(" bChangabled:");
            stringBuilder2.append(InternalIsDeviceChangeable ? "Y" : "N");
            stringBuilder2.append(" err:");
            stringBuilder2.append(i);
            QLog.w(str2, 2, stringBuilder2.toString());
        }
        if (i != 0) {
            Intent intent = new Intent();
            intent.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent, hashMap, i);
            return -1;
        } else if (str.equals(this._deviceConfigManager.h())) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, " --has connected!");
            }
            Intent intent2 = new Intent();
            intent2.putExtra(CONNECTDEVICE_RESULT_DEVICENAME, (String) hashMap.get(PARAM_DEVICE));
            sendResBroadcast(intent2, hashMap, i);
            return 0;
        } else {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, " --connecting...");
            }
            InternalConnectDevice(str, hashMap, false);
            AudioDeviceInterface.LogTraceExit();
            return 0;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalConnectDevice(String str, HashMap<String, Object> hashMap, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" devName:");
        stringBuilder.append(str);
        AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
        if (str == null) {
            return -1;
        }
        if (IsMusicScene && str.equals(DEVICE_EARPHONE)) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("MusicScene, Connect device:");
                stringBuilder2.append(str);
                stringBuilder2.append(" failed");
                QLog.e("TRAE", 2, stringBuilder2.toString());
            }
            return -1;
        } else if (!z && !this._deviceConfigManager.h().equals(DEVICE_NONE) && str.equals(this._deviceConfigManager.h())) {
            return 0;
        } else {
            if (!checkDevName(str) || !this._deviceConfigManager.c(str)) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " checkDevName fail");
                }
                return -1;
            } else if (!InternalIsDeviceChangeable()) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, " InternalIsDeviceChangeable fail");
                }
                return -1;
            } else {
                if (this._switchThread != null) {
                    if (QLog.isColorLevel()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("_switchThread:");
                        stringBuilder.append(this._switchThread.b());
                        QLog.w("TRAE", 2, stringBuilder.toString());
                    }
                    this._switchThread.f();
                    this._switchThread = null;
                }
                if (str.equals(DEVICE_EARPHONE)) {
                    this._switchThread = new h();
                } else if (str.equals(DEVICE_SPEAKERPHONE)) {
                    this._switchThread = new j();
                } else if (str.equals(DEVICE_WIREDHEADSET)) {
                    this._switchThread = new i();
                } else if (str.equals(DEVICE_BLUETOOTHHEADSET)) {
                    this._switchThread = new g();
                }
                if (this._switchThread != null) {
                    this._switchThread.a((HashMap) hashMap);
                    this._switchThread.start();
                }
                AudioDeviceInterface.LogTraceExit();
                return 0;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSessionIsDeviceChangabled(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(ISDEVICECHANGABLED_RESULT_ISCHANGABLED, InternalIsDeviceChangeable());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean InternalIsDeviceChangeable() {
        String g = this._deviceConfigManager.g();
        return g == null || g.equals(DEVICE_NONE) || g.equals("");
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSessionGetConnectedDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTEDDEVICE_RESULT_LIST, this._deviceConfigManager.h());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalSessionGetConnectingDevice(HashMap<String, Object> hashMap) {
        Intent intent = new Intent();
        intent.putExtra(GETCONNECTINGDEVICE_RESULT_LIST, this._deviceConfigManager.g());
        sendResBroadcast(intent, hashMap, 0);
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int sendResBroadcast(Intent intent, HashMap<String, Object> hashMap, int i) {
        if (this._context == null) {
            return -1;
        }
        Long l = (Long) hashMap.get(PARAM_SESSIONID);
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" sessonID:");
            stringBuilder.append(l);
            stringBuilder.append(" ");
            stringBuilder.append((String) hashMap.get(PARAM_OPERATION));
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (l == null || l.longValue() == Long.MIN_VALUE) {
            InternalNotifyDeviceListUpdate();
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "sendResBroadcast sid null,don't send res");
            }
            return -1;
        }
        final Long l2 = (Long) hashMap.get(PARAM_SESSIONID);
        final String str = (String) hashMap.get(PARAM_OPERATION);
        final Intent intent2 = intent;
        final int i2 = i;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                intent2.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_RES);
                intent2.putExtra(TraeAudioManager.PARAM_SESSIONID, l2);
                intent2.putExtra(TraeAudioManager.PARAM_OPERATION, str);
                intent2.putExtra(TraeAudioManager.PARAM_RES_ERRCODE, i2);
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent2);
                }
            }
        });
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalNotifyDeviceListUpdate() {
        AudioDeviceInterface.LogTraceEntry("");
        if (this._context == null) {
            return -1;
        }
        HashMap j = this._deviceConfigManager.j();
        final ArrayList arrayList = (ArrayList) j.get(EXTRA_DATA_AVAILABLEDEVICE_LIST);
        final String str = (String) j.get(EXTRA_DATA_CONNECTEDDEVICE);
        final String str2 = (String) j.get(EXTRA_DATA_PREV_CONNECTEDDEVICE);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_DEVICELIST_UPDATE);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_AVAILABLEDEVICE_LIST, (String[]) arrayList.toArray(new String[0]));
                intent.putExtra(TraeAudioManager.EXTRA_DATA_CONNECTEDDEVICE, str);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_PREV_CONNECTEDDEVICE, str2);
                intent.putExtra(TraeAudioManager.EXTRA_DATA_IF_HAS_BLUETOOTH_THIS_IS_NAME, TraeAudioManager.this._deviceConfigManager.d());
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent);
                }
            }
        });
        AudioDeviceInterface.LogTraceExit();
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int InternalNotifyDeviceChangableUpdate() {
        if (this._context == null) {
            return -1;
        }
        final boolean InternalIsDeviceChangeable = InternalIsDeviceChangeable();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setAction(TraeAudioManager.ACTION_TRAEAUDIOMANAGER_NOTIFY);
                intent.putExtra(TraeAudioManager.PARAM_OPERATION, TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE);
                intent.putExtra(TraeAudioManager.NOTIFY_DEVICECHANGABLE_UPDATE_DATE, InternalIsDeviceChangeable);
                if (TraeAudioManager.this._context != null) {
                    TraeAudioManager.this._context.sendBroadcast(intent);
                }
            }
        });
        return 0;
    }

    public d CreateBluetoothCheck(Context context, e eVar) {
        d aVar;
        if (VERSION.SDK_INT >= 11) {
            aVar = new a();
        } else if (VERSION.SDK_INT != 18) {
            aVar = new c();
        } else {
            aVar = new b();
        }
        if (!aVar.a(context, eVar)) {
            aVar = new b();
        }
        if (QLog.isColorLevel()) {
            String str = "TRAE";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CreateBluetoothCheck:");
            stringBuilder.append(aVar.c());
            stringBuilder.append(" skip android4.3:");
            stringBuilder.append(VERSION.SDK_INT == 18 ? "Y" : "N");
            QLog.w(str, 2, stringBuilder.toString());
        }
        return aVar;
    }

    static String getForceConfigName(int i) {
        return (i < 0 || i >= forceName.length) ? "unknow" : forceName[i];
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class[] clsArr) {
        try {
            return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("invokeMethod Exception:");
                stringBuilder.append(e.getMessage());
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return null;
     */
    public static java.lang.Object invokeStaticMethod(java.lang.String r3, java.lang.String r4, java.lang.Object[] r5, java.lang.Class[] r6) {
        /*
        r0 = 0;
        r1 = 2;
        r2 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x00a8, NoSuchMethodException -> 0x008b, IllegalArgumentException -> 0x006e, IllegalAccessException -> 0x0051, InvocationTargetException -> 0x0033, Exception -> 0x0010 }
        r6 = r2.getMethod(r4, r6);	 Catch:{ ClassNotFoundException -> 0x00a8, NoSuchMethodException -> 0x008b, IllegalArgumentException -> 0x006e, IllegalAccessException -> 0x0051, InvocationTargetException -> 0x0033, Exception -> 0x0010 }
        r5 = r6.invoke(r0, r5);	 Catch:{ ClassNotFoundException -> 0x00a8, NoSuchMethodException -> 0x008b, IllegalArgumentException -> 0x006e, IllegalAccessException -> 0x0051, InvocationTargetException -> 0x0033, Exception -> 0x0010 }
        goto L_0x00c5;
    L_0x0010:
        r3 = move-exception;
        r4 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r4 == 0) goto L_0x00c4;
    L_0x0017:
        r4 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "invokeStaticMethod Exception:";
        r5.append(r6);
        r3 = r3.getMessage();
        r5.append(r3);
        r3 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r4, r1, r3);
        goto L_0x00c4;
    L_0x0033:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x00c4;
    L_0x0039:
        r3 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "InvocationTarget:";
        r5.append(r6);
        r5.append(r4);
        r4 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r1, r4);
        goto L_0x00c4;
    L_0x0051:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x00c4;
    L_0x0057:
        r3 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "IllegalAccess:";
        r5.append(r6);
        r5.append(r4);
        r4 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r1, r4);
        goto L_0x00c4;
    L_0x006e:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x00c4;
    L_0x0074:
        r3 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "IllegalArgument:";
        r5.append(r6);
        r5.append(r4);
        r4 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r1, r4);
        goto L_0x00c4;
    L_0x008b:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x00c4;
    L_0x0091:
        r3 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "NoSuchMethod:";
        r5.append(r6);
        r5.append(r4);
        r4 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r1, r4);
        goto L_0x00c4;
    L_0x00a8:
        r4 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r4 == 0) goto L_0x00c4;
    L_0x00ae:
        r4 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "ClassNotFound:";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r4, r1, r3);
    L_0x00c4:
        r5 = r0;
    L_0x00c5:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeAudioManager.invokeStaticMethod(java.lang.String, java.lang.String, java.lang.Object[], java.lang.Class[]):java.lang.Object");
    }

    static void setParameters(String str) {
        Object[] objArr = new Object[]{str};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = String.class;
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setParameters  :");
            stringBuilder.append(str);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        invokeStaticMethod("android.media.AudioSystem", "setParameters", objArr, clsArr);
    }

    static void setPhoneState(int i) {
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        invokeStaticMethod("android.media.AudioSystem", "setPhoneState", objArr, clsArr);
    }

    static void setForceUse(int i, int i2) {
        Object[] objArr = new Object[]{Integer.valueOf(i), Integer.valueOf(i2)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        clsArr[1] = Integer.TYPE;
        Object invokeStaticMethod = invokeStaticMethod("android.media.AudioSystem", "setForceUse", objArr, clsArr);
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setForceUse  usage:");
            stringBuilder.append(i);
            stringBuilder.append(" config:");
            stringBuilder.append(i2);
            stringBuilder.append(" ->");
            stringBuilder.append(getForceConfigName(i2));
            stringBuilder.append(" res:");
            stringBuilder.append(invokeStaticMethod);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
    }

    static int getForceUse(int i) {
        Object valueOf = Integer.valueOf(0);
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        Object invokeStaticMethod = invokeStaticMethod("android.media.AudioSystem", "getForceUse", objArr, clsArr);
        if (invokeStaticMethod != null) {
            valueOf = (Integer) invokeStaticMethod;
        }
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getForceUse  usage:");
            stringBuilder.append(i);
            stringBuilder.append(" config:");
            stringBuilder.append(valueOf);
            stringBuilder.append(" ->");
            stringBuilder.append(getForceConfigName(valueOf.intValue()));
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return valueOf.intValue();
    }

    static void forceVolumeControlStream(AudioManager audioManager, int i) {
        Object[] objArr = new Object[]{Integer.valueOf(i)};
        Class[] clsArr = new Class[objArr.length];
        clsArr[0] = Integer.TYPE;
        Object invokeMethod = invokeMethod(audioManager, "forceVolumeControlStream", objArr, clsArr);
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("forceVolumeControlStream  streamType:");
            stringBuilder.append(i);
            stringBuilder.append(" res:");
            stringBuilder.append(invokeMethod);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
    }
}
