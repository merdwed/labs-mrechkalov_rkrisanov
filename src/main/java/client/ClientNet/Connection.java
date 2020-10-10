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
    private String hostname = "127.0.0.1";//"localhost";
    private DatagramChannel client=null;
    private SocketAddress serverAddress = new InetSocketAddress(hostname, PORT);
    {init();}
    private void init()
    {
        try {
            client = DatagramChannel.open();
            //client.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reinit(){
        if (client!=null)
            try {
                client.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        init();
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

    public void setServerAddress(InetSocketAddress serverAddress) {
        this.serverAddress = serverAddress;
        try {
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        init();
        rebind();
    }
}
