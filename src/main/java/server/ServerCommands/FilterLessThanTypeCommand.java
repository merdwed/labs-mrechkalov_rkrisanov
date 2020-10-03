package server.ServerCommands;

import DataClasses.Exception.WrongTicketTypeException;
import DataClasses.TicketType;
import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;
import java.util.stream.Collectors;

public class FilterLessThanTypeCommand extends Command {
    public static void execute() throws IOException {
        TicketType ticketType;
        try {
            ticketType = (TicketType) server.ServerNet.PackageIn.getInstance().getObjectInputStream().readObject();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Collection sorted less by ticketType"+ticketType.toString());
            PackageOut.getInstance().getObjectOutputStream().writeObject(
                     ServerMediator.getInstance().getArrayListCollection().stream().
                            filter(x -> x.getType().compareTo(ticketType) < 0).collect(Collectors.toList()));
        } catch (ClassNotFoundException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(new WrongTicketTypeException().toString());
        }
    }

   
}
