package server.ServerNet;

import java.io.IOException;

public class SendAnswer {
    public static void send() throws IOException {
        Connection.getInstance().getServer().send(PackageOut.getInstance().getBufferOut(),Connection.getInstance().getRemoteAdd());
        System.out.println(PackageOut.getInstance().getBufferOut().limit());
    }
}
