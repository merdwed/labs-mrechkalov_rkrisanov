package server.ServerCommands;

import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;

import java.io.IOException;
import java.sql.SQLException;

public class AddCommand extends Command {

    public static void execute() throws IOException {
        try {
            Ticket ticket = (Ticket) server.ServerNet.PackageIn.getInstance().getObjectInputStream().readObject();
            DataBaseCommand.AddTicket(ticket);
            ticket.setId(DataBaseCommand.getCurrId());
            ServerMediator.getInstance().add(ticket);
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket added successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
        } catch (SQLException e) {
            e.printStackTrace();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket didn't added");
        }

    }

}
