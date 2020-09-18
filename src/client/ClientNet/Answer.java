package client.ClientNet;

import java.io.IOException;

public class Answer {
    public static void send() throws IOException {
        Connection.getInstance().getClient().send(PackageOut.getInstance().getBufferOut(),Connection.getInstance().getServerAddress());
    }
}
