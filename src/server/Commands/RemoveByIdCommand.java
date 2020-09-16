package server.Commands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class RemoveByIdCommand extends Command {
    public static void execute(){
        try {
            ServerMediator.getInstance().remove(PackageIn.getInstance().getObjectInputStream().readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
