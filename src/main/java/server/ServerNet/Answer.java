package server.ServerNet;

import java.io.IOException;

public class Answer {
    public static void send() throws IOException {
        PackageOut.getInstance().ToClose();
        Connection.getInstance().getServer().send(PackageOut.getInstance().getBufferOut(),Connection.getInstance().getRemoteAdd());
    }
}
