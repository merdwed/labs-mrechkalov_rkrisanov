package DataClasses.ServerCommands;

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

    public static void process() throws IOException {
        try {
            String message = (String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            if (!message.equals(new WrongTicketTypeException().toString())) {
                System.out.println(message);
                java.util.ArrayList arr = (java.util.ArrayList) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                System.out.println(arr.toString());
            }
            else{
                System.out.println(message);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
