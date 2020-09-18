package DataClasses.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class UpdateCommand extends Command{
    public static void execute() throws IOException{
        Long id;
        Ticket ticket;
        try {
            id = (Long) PackageIn.getInstance().getObjectInputStream().readObject();
            if (id<=0)
                throw new IdLessZeroException();
            if (!ServerMediator.getInstance().exist(id))
                throw new TicketIsNotExistException();
            ServerMediator.getInstance().remove(id);
            ticket = (Ticket)PackageIn.getInstance().getObjectInputStream().readObject();
            ServerMediator.getInstance().add(ticket);
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket updated successful");
        } catch (ClassNotFoundException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
            e.printStackTrace();
        } catch (IdLessZeroException |TicketIsNotExistException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(e.toString());
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
