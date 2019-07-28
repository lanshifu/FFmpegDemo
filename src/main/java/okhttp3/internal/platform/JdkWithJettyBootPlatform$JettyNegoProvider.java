package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import okhttp3.internal.Util;

class JdkWithJettyBootPlatform$JettyNegoProvider implements InvocationHandler {
    private final List<String> protocols;
    String selected;
    boolean unsupported;

    JdkWithJettyBootPlatform$JettyNegoProvider(List<String> list) {
        this.protocols = list;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        Class returnType = method.getReturnType();
        if (objArr == null) {
            objArr = Util.EMPTY_STRING_ARRAY;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return Boolean.valueOf(true);
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.unsupported = true;
            return null;
        } else if (name.equals("protocols") && objArr.length == 0) {
            return this.protocols;
        } else {
            if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                List list = (List) objArr[0];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (this.protocols.contains(list.get(i))) {
                        name = (String) list.get(i);
                        this.selected = name;
                        return name;
                    }
                }
                name = (String) this.protocols.get(0);
                this.selected = name;
                return name;
            } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                return method.invoke(this, objArr);
            } else {
                this.selected = (String) objArr[0];
                return null;
            }
        }
    }
}
