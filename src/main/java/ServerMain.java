import server.JsonFile;
import server.ServerNet.Answer;
import server.ServerNet.Connection;
import server.ServerNet.ServerCommandFactory;
import server.ServerNet.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Logger connectionLogger = LogManager.getLogger();
        String fileName="Jsonfiles/dataset1";
        if(new File(fileName).exists()){
            JsonFile.getJsonFile().setPathName(fileName);
            JsonFile.getJsonFile().readJSON();
        }
        else
        {
            JsonFile.getJsonFile().setPathName(fileName);
        }
        Connection.getInstance().setiAdd(new InetSocketAddress(InetAddress.getLocalHost().getHostName(),8989));
        Connection.getInstance().rebind();
        connectionLogger.info("Server started, the host named "+Connection.getInstance().getiAdd().getHostName());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            if (Request.getInstance().receive()) {
                connectionLogger.info("Request received from " + Connection.getInstance().getRemoteAdd());
                Request.getInstance().prossessing();
                connectionLogger.info("Request contains command " + Request.getInstance().getCommandType());
                ServerCommandFactory.executeCommand(Request.getInstance().getCommandType());
                connectionLogger.info("Command " + Request.getInstance().getCommandType() + " executed");
                Answer.send();
                connectionLogger.info("Answer sent to " + Connection.getInstance().getRemoteAdd());
            }

            if (reader.ready()) {
                String command = reader.readLine();
                if (command.equals("exit"))
                    break;
                switch (command) {
                    case "get_host_name": {
                        System.out.println(InetAddress.getLocalHost().getHostName());
                        break;
                    }
                    case "get_free_port": {
                        System.out.println(InetAddress.getLocalHost());
                        break;
                    }
                }
            }
        }
        fileName="Jsonfiles/dataset3";
        if(new File(fileName).exists()) {
            JsonFile.getJsonFile().setPathName(fileName);
            JsonFile.getJsonFile().writeJSON();
        }
    }
}