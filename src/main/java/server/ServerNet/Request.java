package server.ServerNet;

import DataClasses.CommandTypeUtils.CommandType;

import java.io.*;
import java.nio.ByteBuffer;

public class Request {
    private Request() {
    }
    private static Request request = new Request();
    public static Request getInstance() { return request; }

    private ByteBuffer buffer = ByteBuffer.allocate(4096);
    private CommandType commandType;

    public boolean receive() throws IOException {
        buffer.clear();
        Connection.getInstance().setRemoteAdd(Connection.getInstance().getServer().receive(buffer));
        return (Connection.getInstance().getRemoteAdd()!=null);
    }
    public void prossessing()throws IOException {
        PackageIn.getInstance().setBufferIn(buffer);
        try {
            commandType = (CommandType) PackageIn.getInstance().getObjectInputStream().readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось опознать команду");
            e.printStackTrace();
        }
    }
    public CommandType getCommandType() {
        return commandType;
    }
}

