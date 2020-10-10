import DataClasses.*;
import DataClasses.CommandTypeUtils.CommandType;
import server.DataBase.DataBase;
import server.DataBase.DataBaseCommand;
import server.DataBase.DataBaseConnection;
import server.Dataset;
import server.ServerMediator;
import server.ServerNet.*;
import server.Threading.LoggerForkJoinTask;
import server.Threading.ProssesRunnableTask;
import server.Threading.ThreadPools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void serverSetUp() throws IOException {
        System.out.println("server ready to start");
        System.out.print("enter a listening host:");
        String host = reader.readLine();
        System.out.print("enter a listening port:");
        Integer port = Integer.valueOf(reader.readLine());
        Connection.getInstance().setiAdd(new InetSocketAddress(host,port));
        Connection.getInstance().rebind();
        LoggerForkJoinTask.connectionLogger.info("server started, host:\n " + Connection.getInstance().getiAdd().getHostName() + ":" + Connection.getInstance().getiAdd().getPort());
        System.out.println("server started, host:\n " + Connection.getInstance().getiAdd().getHostName() + ":" + Connection.getInstance().getiAdd().getPort());
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String ss;
        System.out.print("enter a DataBase host:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setHostname("127.0.0.1");
            System.out.println("installed default host:127.0.0.1");
            LoggerForkJoinTask.connectionLogger.info("installed default host:127.0.0.1");
        }
        else {
            dataBaseConnection.setHostname(ss);
            System.out.println("installed entered host:"+ss);
            LoggerForkJoinTask.connectionLogger.info("installed entered host:"+ss);
        }
        System.out.print("enter a DataBase port:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setDataBasePORT(5432);
            System.out.println("installed default port:5432");
            LoggerForkJoinTask.connectionLogger.info("installed default port:5432");
        }
        else {
            dataBaseConnection.setDataBasePORT(Integer.valueOf(ss));
            System.out.println("installed entered port:"+ss);
            LoggerForkJoinTask.connectionLogger.info("installed entered port:"+ss);
        }
        System.out.print("enter a DataBase name:");
        ss = reader.readLine();
        if (ss.equals("")) {
            dataBaseConnection.setDataBaseName("mydb");
            System.out.println("installed default name:mydb");
            LoggerForkJoinTask.connectionLogger.info("installed default name:mydb");
        }
        else {
            dataBaseConnection.setDataBaseName(ss);
            System.out.println("installed entered name:"+ss);
            LoggerForkJoinTask.connectionLogger.info("installed entered name:"+ss);
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
            LoggerForkJoinTask.connectionLogger.info("installed user name:"+name);
        }
        else{
            System.out.println("installed user name:postgres");
            LoggerForkJoinTask.connectionLogger.info("installed user name:postgres");
        }
    }

    public static void sqlSetUp() {
        DataBase.getInstance().Connect();
        DataBaseCommand.setStatement(DataBase.getInstance().getStatement());
        try {
            DataBaseCommand.getTicketCollection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        serverSetUp();
        sqlSetUp();

        Thread consoleCommandThread=new Thread(()-> consoleMethod());
        consoleCommandThread.start();
        while(true) {
            Request request = new Request();
            if (request.receive()) {
                ActiveUsers.getInstance().add(Connection.getInstance().getRemoteAdd());
                ThreadPools.prossesService.submit(new ProssesRunnableTask(request));
            }
        }
    }
    public static void consoleMethod()  {
        System.out.println("console reader start");

        try {
            while (true) {
                String command = reader.readLine();
                if (command.equals("exit")){
                    ThreadPools.prossesService.shutdown();
                    ThreadPools.executeService.shutdown();
                    ThreadPools.answerServise.shutdown();
                    System.exit(0);
                    break;
                }
                switch (command) {
                    case "get_host_name": {
                            System.out.println(InetAddress.getLocalHost().getHostName());
                        
                        break;
                    }
                    case "get_collection": {
                        for (Ticket ticket : Dataset.getCurrentInstance().getArrayListCollection()) {
                            System.out.print(ticket);
                        }
                        break;
                    }
                }
                System.out.print(">");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}