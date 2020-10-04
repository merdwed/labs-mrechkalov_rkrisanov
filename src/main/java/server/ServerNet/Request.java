package server.ServerNet;

import DataClasses.Account;
import DataClasses.CommandTypeUtils.CommandType;

import java.io.*;
import java.nio.ByteBuffer;

public class Request {
    private Request() {
    }
    private static Request request = new Request();
    public static Request getInstance() { return request; }

    private ByteBuffer buffer = ByteBuffer.allocate(4096);
    private Account account=null;
    private CommandType commandType;

    public boolean receive() throws IOException {
        buffer.clear();
        Connection.getInstance().setRemoteAdd(Connection.getInstance().getServer().receive(buffer));
        return (Connection.getInstance().getRemoteAdd()!=null);
    }
    public void prossesCommand()throws IOException {
        try {
            commandType = (CommandType) PackageIn.getInstance().getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось опознать команду");
            e.printStackTrace();
        }
    }
    public void prossesAccount()throws IOException {
        PackageIn.getInstance().setBufferIn(buffer);
        try {
            account = (Account) PackageIn.getInstance().getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось опознать команду");
            e.printStackTrace();
        }
    }


    public CommandType getCommandType() {
        return commandType;
    }
    public Account getAccount(){return account;}
}

