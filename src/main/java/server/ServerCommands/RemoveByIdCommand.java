package server.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import server.ServerMediator;
import server.ServerNet.PackageIn;

import java.io.IOException;

public class RemoveByIdCommand extends Command {
    public static void execute() throws IOException{
        Long id;
        try {
            id = (Long) PackageIn.getInstance().getObjectInputStream().readObject();
            if (id<=0)
                throw new IdLessZeroException();
            if (!ServerMediator.getInstance().exist(id))
                throw new TicketIsNotExistException();
            ServerMediator.getInstance().remove(id);
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ticket deleted successful");
        } catch (ClassNotFoundException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Ошибка при попытке десериализовать билет");
            e.printStackTrace();
        } catch (IdLessZeroException |TicketIsNotExistException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(e.toString());
        }
    }

   
}
