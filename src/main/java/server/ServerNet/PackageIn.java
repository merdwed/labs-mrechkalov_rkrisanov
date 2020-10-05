package server.ServerNet;

import java.io.*;
import java.nio.ByteBuffer;

public class PackageIn {
    PackageIn(){
    }
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
