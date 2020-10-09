package server.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.ActiveUsers;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveByIdCommand extends Command {
    public void execute(Request request,Answer answer) {
        Long id;
        try {
            id = (Long) request.getArg();
            if (id<=0)
                throw new IdLessZeroException();
            if (!ServerMediator.getInstance().exist(id))
                throw new TicketIsNotExistException();
            if (DataBaseCommand.TicketIsExist(id)) {
                if (DataBaseCommand.AccessToTicket(id, request.getAccount().getLogin())) {
                    DataBaseCommand.RemoveTicket(id);
                    ServerMediator.getInstance().remove(id);
                    answer.setToCurrans("Ticket deleted successful");
                    ActiveUsers.getInstance().CollectionChanged();
                }
                else
                    answer.setToCurrans("Wrong user");

            }
            else
                answer.setToCurrans("Ticket not found");
        } catch (IdLessZeroException |TicketIsNotExistException e) {
            answer.setToCurrans(e.toString());
        } catch (SQLException e) {
            answer.setToCurrans(e.toString());
        }
    }

   
}
