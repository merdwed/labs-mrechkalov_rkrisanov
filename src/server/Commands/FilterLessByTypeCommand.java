package server.Commands;

import DataClasses.TicketType;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class FilterLessByTypeCommand {
    public static void execute() throws IOException {

        try {
            TicketType ticketType;
            ticketType = (TicketType) PackageIn.getInstance().getObjectInputStream().readObject();
            PackageOut.getInstance().remake();
            PackageOut.getInstance().getObjectOutputStream().writeObject(
                    ServerMediator.getInstance().getArrayListCollection().stream().filter(x -> x.getType().compareTo(ticketType)<0));
            Answer.send();
        } catch (ClassNotFoundException e) {
            System.out.println("Your Ticket Type is absolutely bullshit");
            e.printStackTrace();
        }
    }
}
