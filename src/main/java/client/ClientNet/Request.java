package client.ClientNet;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Request {
    public Request(){
    }
    private static Request request = new Request();
    public static Request getInstance() { return request; }
    private ByteBuffer buffer = ByteBuffer.allocate(4096);


    public boolean receive() throws IOException {
        buffer.clear();
        SocketAddress socketAddress;
        socketAddress=Connection.getInstance().getClient().receive(buffer);
        return socketAddress != null;
    }
    public void prossessing() throws IOException {
        PackageIn.getInstance().setBufferIn(buffer);
    }
}
