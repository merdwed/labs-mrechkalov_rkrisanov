package client.ClientNet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class PackageOut {
    private static PackageOut packageOut = new PackageOut();
    public static PackageOut getInstance() { return packageOut; }

    private ByteArrayOutputStream byteArrayOutputStream;//=new ByteArrayOutputStream();
    private ObjectOutputStream objectOutputStream;

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ByteBuffer getBufferOut() {
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }
    public void ToClose() throws IOException {
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.flush();
        objectOutputStream.close();
    }
    public void remake(){
        byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
