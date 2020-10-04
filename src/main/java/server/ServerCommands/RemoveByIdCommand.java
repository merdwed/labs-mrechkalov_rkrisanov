package server.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveByIdCommand extends Command {
    public static void execute() throws IOException{
        Long id;
        try {
            id = (Long) PackageIn.getInstance().getObjectInputStream().readObject();
            if (id<=0)
                throw new IdLessZeroException();
            if (!ServerMediator.getInstance().exist(id))
                throw new TicketIsNotExistException();
            if (DataBaseCommand.TicketIsExist(id)) {
                if (DataBaseCommand.AccessToTicket(id, Request.getInstance().getAccount().getLogin())) {
                    DataBaseCommand.RemoveTicket(id);
                    ServerMediator.getInstance().remove(id);
                    server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket deleted successful");
                }
                else
                    server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Wrong user");

            }
            else
                server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket not found");
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
