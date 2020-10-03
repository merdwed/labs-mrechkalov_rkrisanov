package server.ServerCommands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class RemoveLowerCommand extends Command{
    public static void execute() throws IOException {
        try {
            Ticket ticket = (Ticket)PackageIn.getInstance().getObjectInputStream().readObject();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Every ticket from the collection less the ticket is removed");
            ServerMediator.getInstance().getArrayListCollection().stream().filter
                    (x -> x.getPrice().compareTo(ticket.getPrice()) < 0).forEach(x -> ServerMediator.getInstance().remove(x.getId()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
}
