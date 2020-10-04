package server.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

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
            ticket = (Ticket)PackageIn.getInstance().getObjectInputStream().readObject();
            if (DataBaseCommand.TicketIsExist(id)) {
                if (DataBaseCommand.AccessToTicket(id, Request.getInstance().getAccount().getLogin())) {
                    DataBaseCommand.UpdateTicket(id,ticket);
                    ServerMediator.getInstance().remove(id);
                    ServerMediator.getInstance().add(ticket);
                    server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket updated successful");
                }
                else
                    server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Wrong user");

            }
            else
                server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket not found");
            ServerMediator.getInstance().add(ticket);
        } catch (ClassNotFoundException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
            e.printStackTrace();
        } catch (IdLessZeroException |TicketIsNotExistException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(e.toString());
        } catch (SQLException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(e.toString());
        }
    }
    
}
