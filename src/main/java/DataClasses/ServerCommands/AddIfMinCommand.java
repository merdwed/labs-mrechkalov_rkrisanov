package DataClasses.ServerCommands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class AddIfMinCommand extends Command {
    public static void execute() throws IOException {
        Ticket ticket;
        try {
            ticket = (Ticket) PackageIn.getInstance().getObjectInputStream().readObject();
            if (ServerMediator.getInstance().getArrayListCollection().stream().allMatch(x -> x.getPrice().compareTo(ticket.getPrice()) >= 0)) {
                ServerMediator.getInstance().add(ticket);
                server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket added successful");
            } else {
                server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket wasn't added");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
        }

    }

    public static void process() throws IOException {
        try {
            String message = (String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
