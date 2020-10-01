import client.ClientNet.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class ClientMain {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("client ready to start");
        System.out.print("enter a server host:");
        String host = reader.readLine();
        System.out.print("enter a server port:");
        Integer port = Integer.valueOf(reader.readLine());
        Connection.getInstance().setServerAddress(new InetSocketAddress(host,port));
        reader=null;


        client.ShellUtils.ShellIO.initAndPushSource(null);//null is special value for console. usualy arg is path to file
        client.ShellUtils.ShellInterpretator.run();
    }
}