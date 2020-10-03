package DataClasses.ServerCommands;

import DataClasses.Ticket;
import server.ServerMediator;

import java.io.IOException;

public class AddCommand extends Command {

    public static void execute() throws IOException {
        try {
            Ticket ticket = (Ticket) server.ServerNet.PackageIn.getInstance().getObjectInputStream().readObject();
            ServerMediator.getInstance().add(ticket);
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket added successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
        }

    }

}
