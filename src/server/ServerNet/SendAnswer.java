package server.ServerNet;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SendAnswer {
    public static void send() throws IOException {
        Connection.getInstance().getServer().send(PackageOut.getInstance().getBufferOut(),Connection.getInstance().getRemoteAdd());
    }
}
