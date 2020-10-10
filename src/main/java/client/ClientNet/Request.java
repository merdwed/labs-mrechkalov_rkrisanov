package client.ClientNet;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Request {
    public Request(){
    }
    //private static Request request = new Request();
    
    private ByteBuffer buffer = ByteBuffer.allocate(4096);


    public boolean receive() throws IOException {
        buffer.clear();
        SocketAddress socketAddress;
        socketAddress=Connection.getInstance().getClient().receive(buffer);
        return socketAddress != null;
    }
    public PackageIn prossessing() throws IOException {
        PackageIn temp =new PackageIn();
        temp.setBufferIn(buffer);
        return temp;
    }
}

