package server;
import DataClasses.CommandType;
import server.ServerNet.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ServerMain {
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
        while(true) {

            Request request = new Request();
            if (request.getCommandType()==CommandType.EXIT)
                break;
            ExecuteCommand.executeCommand(request.getCommandType());
        }
    }
}