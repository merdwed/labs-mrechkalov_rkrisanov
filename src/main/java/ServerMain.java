import DataClasses.*;
import DataClasses.CommandTypeUtils.CommandType;
import server.DataBase.DataBase;
import server.DataBase.DataBaseCommand;
import server.Dataset;
import server.ServerNet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Logger connectionLogger = LogManager.getLogger();
        System.out.println("server ready to start");
        System.out.print("enter a listening host:");
        String host = reader.readLine();
        System.out.print("enter a listening port:");
        Integer port = Integer.valueOf(reader.readLine());

        Connection.getInstance().setiAdd(new InetSocketAddress(host,port));
        Connection.getInstance().rebind();
        connectionLogger.info("server started, host:\n " + Connection.getInstance().getHostname() + ":" + Connection.getInstance().getPORT());
        System.out.println("server started, host:\n " + Connection.getInstance().getHostname() + ":" + Connection.getInstance().getPORT());
        DataBase.getInstance().Connect();
        DataBaseCommand.setStatement(DataBase.getStatement());
        try {
            DataBaseCommand.getTicketCollection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true) {
            if (Request.getInstance().receive()) {
                connectionLogger.info("Request received from " + Connection.getInstance().getRemoteAdd());
                Request.getInstance().prossesAccount();
                connectionLogger.info("Request received from user: "+Request.getInstance().getAccount().getLogin());
                Request.getInstance().prossesCommand();
                connectionLogger.info("Request contains command " + Request.getInstance().getCommandType());
                if (DataBase.getInstance().checkAccount(Request.getInstance().getAccount())||Request.getInstance().getCommandType().equals(CommandType.CREATE_ACCOUNT)) {
                    connectionLogger.info("Request contains command " + Request.getInstance().getCommandType());
                    ServerCommandFactory.executeCommand(Request.getInstance().getCommandType());
                    connectionLogger.info("Command " + Request.getInstance().getCommandType() + " executed");
                    Answer.send();
                    connectionLogger.info("Answer sent to " + Connection.getInstance().getRemoteAdd());
                }
                else
                {
                    PackageOut.getInstance().remake();//надо убрать куда-нить
                    PackageOut.getInstance().getObjectOutputStream().writeObject("Account not found, use create_account");
                    Answer.send();
                }
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
                    case "get_collection":{
                        for (Ticket ticket :
                                Dataset.getCurrentInstance().getArrayListCollection()) {
                            System.out.print(ticket);
                        }
                        break;
                    }
                    case "delete_user":{
                        System.out.println();
                        break;
                    }
                }
                System.out.print(">");
            }
        }
    }
}