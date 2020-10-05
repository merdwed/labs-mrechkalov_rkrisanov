package server.ServerNet;

import DataClasses.Account;
import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

public class Request {
    public Request() {
        packageIn = new PackageIn();
        currans = new ArrayList<Object>();
    }
    private PackageIn packageIn;
    private ByteBuffer buffer = ByteBuffer.allocate(4096);
    private Account account=null;
    private CommandType commandType;
    private ArrayList<Object> currans;
    private Iterator<Object> iterator;

    public boolean receive() throws IOException {
        buffer.clear();
        Connection.getInstance().setRemoteAdd(Connection.getInstance().getServer().receive(buffer));
        return (Connection.getInstance().getRemoteAdd()!=null);
    }
    public void prossesCommand()throws IOException {
        try {
            commandType = (CommandType) packageIn.getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось опознать команду");
            e.printStackTrace();
        }
    }
    public void prossesAccount()throws IOException {
        packageIn.setBufferIn(buffer);
        try {
            account = (Account) packageIn.getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось опознать команду");
            e.printStackTrace();
        }
    }
    public void prossesArg(){
        ArrayList<Class> requestAnsForm =  CommandTypeInformation.NeededParametersOfCommndType(commandType);
        if (requestAnsForm!=null) {
            for (Object obj :
                    requestAnsForm) {
                try {
                    currans.add(packageIn.getObjectInputStream().readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            iterator = currans.iterator();
        }
    }
    public boolean hasArg(){
        return iterator.hasNext();
    }
    public Object getArg(){
        return iterator.next();
    }


    public CommandType getCommandType() {
        return commandType;
    }
    public Account getAccount(){return account;}
}

