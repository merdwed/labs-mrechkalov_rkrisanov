package server.Commands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class UpdateCommand extends Command{
    public static void execute(){
        Long id;
        Ticket ticket;
        try {
            id = PackageIn.getInstance().getObjectInputStream().readLong();
            ServerMediator.getInstance().remove(id);
            ticket = (Ticket)PackageIn.getInstance().getObjectInputStream().readObject();
            ServerMediator.getInstance().add(ticket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Твои классы говно");
            e.printStackTrace();
        }
    }
}
