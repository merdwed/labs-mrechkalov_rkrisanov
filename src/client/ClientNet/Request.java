package client.ClientNet;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Request {
    public Request() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        Connection.getInstance().getClient().receive(buffer);
        PackageIn.getInstance().setBufferIn(buffer);
    }
}

