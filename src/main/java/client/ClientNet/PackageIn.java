package client.ClientNet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class PackageIn {
    private static PackageIn packageIn = new PackageIn();
    public static PackageIn getInstance() { return packageIn; }
    private ObjectInputStream objectInputStream;
    private ByteArrayInputStream byteArrayInputStream;

    public void setBufferIn(ByteBuffer bufferIn) throws IOException {
        byteArrayInputStream=new ByteArrayInputStream(bufferIn.array());
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }
}
