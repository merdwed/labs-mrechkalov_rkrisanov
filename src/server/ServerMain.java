package server;
import server.ServerNet.Answer;
import server.ServerNet.Connection;
import server.ServerNet.ExecuteCommand;
import server.ServerNet.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class ServerMain {
    private final static int PORT = 8989;
    private final static String HOST_NAME = "localhost";
    public static void main(String[] args) throws IOException {
//        String fileName="NewCollection";
        String fileName="dataset example/dataset1";
        JsonFile.getJsonFile().setPathName(fileName);
        JsonFile.getJsonFile().readJSON();
//        if(args.length>0) {
//            fileName=args[0];
//            JsonFile.getJsonFile().setPathName(fileName);
//            JsonFile.getJsonFile().readJSON();
//        }
//        else{
//            JsonFile.getJsonFile().setPathName(fileName);
//        }
        //Я ещё подумаю, куда это убрать
        Connection.getInstance().setPORT(PORT);
        Connection.getInstance().setHostname(HOST_NAME);
        Connection.getInstance().setiAdd(new InetSocketAddress(HOST_NAME,PORT));
        Connection.getInstance().rebind();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            Request request = new Request();
            if(Connection.getInstance().getRemoteAdd()!=null) {
                ExecuteCommand.executeCommand(request.getCommandType());
                Answer.send();
            }

            if (reader.ready()){
                if (reader.readLine().equals("exit"))
                    break;
            }
        }
        //JsonFile.getJsonFile().writeJSON();

    }
}