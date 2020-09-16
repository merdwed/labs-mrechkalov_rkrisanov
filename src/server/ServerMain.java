package server;
import DataClasses.CommandTypeUtils.CommandType;
import server.ServerNet.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerMain {
    private final static int PORT = 8989;
    private final static String HOST_NAME = "localhost";
    public static void main(String[] args) throws IOException {
//        String fileName="NewCollection";
//        fileName="dataset example/dataset1";
//        JsonFile.getJsonFile().setPathName(fileName);
//        JsonFile.getJsonFile().readJSON();
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
        while(true) {
            Request request = new Request();
            if (request.getCommandType()==CommandType.EXIT)
                break;
            ExecuteCommand.executeCommand(request.getCommandType());
        }
    }
}