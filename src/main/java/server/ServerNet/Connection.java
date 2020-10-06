package server.ServerNet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class Connection {
    private Connection() {
    }

    private static Connection connection = new Connection();
    public static Connection getInstance(){ return connection;}

    private int PORT = 8989;
    private String hostname = "127.0.0.1";
    private DatagramChannel server;
    private InetSocketAddress iAdd = new InetSocketAddress(hostname, PORT);

    private SocketAddress remoteAdd;

    {
        try {
            server = DatagramChannel.open();
            //server.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bind inet with current hostname and port
    public void rebind(){
        try {
            server.bind(iAdd);
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

    public DatagramChannel getServer() {
        return server;
    }

    public InetSocketAddress getiAdd() {
        return iAdd;
    }

    public SocketAddress getRemoteAdd() {
        return remoteAdd;
    }

    protected void setRemoteAdd(SocketAddress remoteAdd) {
        this.remoteAdd = remoteAdd;
    }

    public void setServer(DatagramChannel server) {
        this.server = server;
    }

    public void setiAdd(InetSocketAddress iAdd) {
        this.iAdd = iAdd;
    }
}
