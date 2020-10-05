import DataClasses.*;
import DataClasses.CommandTypeUtils.CommandType;
import server.DataBase.DataBase;
import server.DataBase.DataBaseCommand;
import server.DataBase.DataBaseConnection;
import server.Dataset;
import server.ServerMediator;
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
        connectionLogger.info("server started, host:\n " + Connection.getInstance().getiAdd().getHostName() + ":" + Connection.getInstance().getiAdd().getPort());
        System.out.println("server started, host:\n " + Connection.getInstance().getiAdd().getHostName() + ":" + Connection.getInstance().getiAdd().getPort());

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String ss;
        System.out.print("enter a DataBase host:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setDataBaseName("127.0.0.1");
            System.out.println("installed default host:127.0.0.1");
            connectionLogger.info("installed default host:127.0.0.1");
        }
        else {
            dataBaseConnection.setDataBaseName(ss);
            System.out.println("installed entered host:"+ss);
            connectionLogger.info("installed entered host:"+ss);
        }
        System.out.print("enter a DataBase port:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setDataBasePORT(5432);
            System.out.println("installed default port:5432");
            connectionLogger.info("installed default port:5432");
        }
        else {
            dataBaseConnection.setDataBasePORT(Integer.valueOf(ss));
            System.out.println("installed entered port:"+ss);
            connectionLogger.info("installed entered port:"+ss);
        }
        System.out.print("enter a DataBase name:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setDataBaseName("mydb");
            System.out.println("installed default name:mydb");
            connectionLogger.info("installed default name:mydb");
        }
        else {
            dataBaseConnection.setDataBaseName(ss);
            System.out.println("installed entered name:"+ss);
            connectionLogger.info("installed entered name:"+ss);
        }
        dataBaseConnection.update();
        System.out.println(dataBaseConnection.getDbUrl());
        DataBase.getInstance().setDbUrl(dataBaseConnection.getDbUrl());
        String name,password;
        System.out.println("set the new user?(y/n)");
        ss = reader.readLine();
        if (ss.equals("y")) {
            System.out.println("enter name");
            name = reader.readLine();
            System.out.println("enter password");
            password = reader.readLine();
            DataBase.getInstance().setSuperAccount(name,password);
            System.out.println("installed user name:"+name);
            connectionLogger.info("installed user name:"+name);
        }
        else{
            System.out.println("installed user name:postgres");
            connectionLogger.info("installed user name:postgres");
        }


        DataBase.getInstance().Connect();
        DataBaseCommand.setStatement(DataBase.getInstance().getStatement());
        try {
            DataBaseCommand.getTicketCollection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true) {
            Request request = new Request();
            if (request.receive()) {
                connectionLogger.info("Request received from " + Connection.getInstance().getRemoteAdd());
                request.prossesAccount();
                connectionLogger.info("Request received from user: "+request.getAccount().getLogin());
                request.prossesCommand();
                connectionLogger.info("Request contains command " + request.getCommandType());
                request.prossesArg();
                Answer answer = new Answer();
                try {
                    if (DataBase.getInstance().checkAccount(request.getAccount())||request.getCommandType().equals(CommandType.CREATE_ACCOUNT)) {
                        connectionLogger.info("Request contains command " + request.getCommandType());

                        ServerCommandFactory.executeCommand(request,answer);
                        connectionLogger.info("Command " + request.getCommandType() + " executed");
                        answer.prepare(request.getCommandType());
                        answer.send();
                        connectionLogger.info("Answer sent to " + Connection.getInstance().getRemoteAdd());
                    }
                    else
                    {
                        answer.setToCurrans("Wrong account");
                        answer.prepare(request.getCommandType());
                        answer.send();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
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
                }
                System.out.print(">");
            }
        }
    }
}