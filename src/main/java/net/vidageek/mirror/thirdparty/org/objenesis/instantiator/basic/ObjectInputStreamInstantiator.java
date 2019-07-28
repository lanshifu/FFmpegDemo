package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class ObjectInputStreamInstantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$io$Serializable;
    private ObjectInputStream inputStream;

    private static class MockStream extends InputStream {
        private static byte[] HEADER;
        private static final int[] NEXT = new int[]{1, 2, 2};
        private static byte[] REPEATING_DATA;
        private final byte[] FIRST_DATA;
        private byte[][] buffers;
        private byte[] data = HEADER;
        private int pointer = 0;
        private int sequence = 0;

        public int available() throws IOException {
            return Filter.MAX;
        }

        static {
            initialize();
        }

        private static void initialize() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(-21267);
                dataOutputStream.writeShort(5);
                HEADER = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeByte(115);
                dataOutputStream.writeByte(113);
                dataOutputStream.writeInt(8257536);
                REPEATING_DATA = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("IOException: ");
                stringBuffer.append(e.getMessage());
                throw new Error(stringBuffer.toString());
            }
        }

        public MockStream(Class cls) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeByte(115);
                dataOutputStream.writeByte(114);
                dataOutputStream.writeUTF(cls.getName());
                dataOutputStream.writeLong(ObjectStreamClass.lookup(cls).getSerialVersionUID());
                dataOutputStream.writeByte(2);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeByte(120);
                dataOutputStream.writeByte(112);
                this.FIRST_DATA = byteArrayOutputStream.toByteArray();
                this.buffers = new byte[][]{HEADER, this.FIRST_DATA, REPEATING_DATA};
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("IOException: ");
                stringBuffer.append(e.getMessage());
                throw new Error(stringBuffer.toString());
            }
        }

        private void advanceBuffer() {
            this.pointer = 0;
            this.sequence = NEXT[this.sequence];
            this.data = this.buffers[this.sequence];
        }

        public int read() throws IOException {
            byte[] bArr = this.data;
            int i = this.pointer;
            this.pointer = i + 1;
            byte b = bArr[i];
            if (this.pointer >= this.data.length) {
                advanceBuffer();
            }
            return b;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int length = this.data.length - this.pointer;
            int i3 = i;
            i = i2;
            while (length <= i) {
                System.arraycopy(this.data, this.pointer, bArr, i3, length);
                i3 += length;
                i -= length;
                advanceBuffer();
                length = this.data.length - this.pointer;
            }
            if (i > 0) {
                System.arraycopy(this.data, this.pointer, bArr, i3, i);
                this.pointer += i;
            }
            return i2;
        }
    }

    public ObjectInputStreamInstantiator(Class cls) {
        Class class$;
        if (class$java$io$Serializable == null) {
            class$ = class$("java.io.Serializable");
            class$java$io$Serializable = class$;
        } else {
            class$ = class$java$io$Serializable;
        }
        if (class$.isAssignableFrom(cls)) {
            try {
                this.inputStream = new ObjectInputStream(new MockStream(cls));
                return;
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("IOException: ");
                stringBuffer.append(e.getMessage());
                throw new Error(stringBuffer.toString());
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(cls);
        stringBuffer2.append(" not serializable");
        throw new ObjenesisException(new NotSerializableException(stringBuffer2.toString()));
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object newInstance() {
        try {
            return this.inputStream.readObject();
        } catch (ClassNotFoundException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("ClassNotFoundException: ");
            stringBuffer.append(e.getMessage());
            throw new Error(stringBuffer.toString());
        } catch (Exception e2) {
            throw new ObjenesisException(e2);
        }
    }
}
