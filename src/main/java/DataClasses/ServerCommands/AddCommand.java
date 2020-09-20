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
    public static void process() throws IOException {
        try {
            String message =(String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
