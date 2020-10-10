package client.ClientNet;

import java.io.IOException;

public class Answer {
    public static void send(PackageOut packageOut) throws IOException {
        Connection.getInstance().getClient().send(packageOut.getBufferOut(),Connection.getInstance().getServerAddress());
    }
}
