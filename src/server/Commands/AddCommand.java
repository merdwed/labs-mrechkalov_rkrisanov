package server.Commands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class AddCommand extends Command {

    public static void execute(){
        Ticket ticket;
        try {
            ticket = (Ticket) PackageIn.getInstance().getObjectInputStream().readObject();
            ServerMediator.getInstance().add(ticket);
            //ServerMediator.getInstance().add((Ticket) PackageIn.getInstance().getObjectInputStream().readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
