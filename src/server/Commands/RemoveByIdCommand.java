package server.Commands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class RemoveByIdCommand extends Command {
    public static void execute(){
        Long id;
        try {
            id = PackageIn.getInstance().getObjectInputStream().readLong();
            ServerMediator.getInstance().remove(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
