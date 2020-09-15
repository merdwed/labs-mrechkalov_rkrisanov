package client.ClientNet;

import client.ClientNet.Connection;
import client.ClientNet.PackageOut;

import java.io.IOException;

public class Answer {
    public static void send() throws IOException {
        Connection.getInstance().getClient().send(PackageOut.getInstance().getBufferOut(),Connection.getInstance().getServerAddress());
    }
}
