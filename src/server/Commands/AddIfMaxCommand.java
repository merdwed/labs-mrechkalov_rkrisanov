package server.Commands;

import DataClasses.Comparators.PriceComparator;
import DataClasses.Ticket;
import server.Dataset;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class AddIfMaxCommand {
    public static void execute(){
        Ticket ticket;
        try {
            ticket = (Ticket) PackageIn.getInstance().getObjectInputStream().readObject();
            if(Dataset.getCurrentInstance().getSortedArrayList(new PriceComparator()).get(0).getPrice().compareTo(ticket.getPrice()) < 0)
                ServerMediator.getInstance().add(ticket);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}
