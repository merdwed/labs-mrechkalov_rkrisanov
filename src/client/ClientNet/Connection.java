package client.ClientNet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class Connection {
    private Connection() {
        rebind();
    }

    private static Connection connection = new Connection();
    public static Connection getInstance(){ return connection;}

    private int PORT = 8989;
    private String hostname = "localhost";
    private DatagramChannel client;
    private InetSocketAddress serverAddress = new InetSocketAddress(hostname, PORT);

    {
        try {
            client = DatagramChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bind inet with current hostname and port
    private void rebind(){
        try {
            client.bind(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //setters and getters
    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public DatagramChannel getClient() {
        return client;
    }

    public SocketAddress getServerAddress() {
        return serverAddress;
    }

    private void setServerAddress(SocketAddress remoteAdd) {
        this.serverAddress = serverAddress;
    }
}
