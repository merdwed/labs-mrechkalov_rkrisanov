package server.ServerNet;

import DataClasses.CommandTypeUtils.CommandType;

import java.io.*;
import java.nio.ByteBuffer;

public class Request {
    public Request() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.clear();
        Connection.getInstance().setRemoteAdd(Connection.getInstance().getServer().receive(buffer));
        if (Connection.getInstance().getRemoteAdd()!=null) {
            PackageIn.getInstance().setBufferIn(buffer);
            try {
                commandType = (CommandType) PackageIn.getInstance().getObjectInputStream().readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("Не удалось опознать команду");
                e.printStackTrace();
            }
        }
    }

    private CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }
}

