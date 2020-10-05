package server.ServerCommands;

import DataClasses.Exception.IdLessZeroException;
import DataClasses.Exception.TicketIsNotExistException;
import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCommand extends Command{
    public static void execute(Request request, Answer answer) throws IOException{
        Long id;
        Ticket ticket;
        try {
            id = (Long) request.getArg();
            if (id<=0)
                throw new IdLessZeroException();
            if (!ServerMediator.getInstance().exist(id))
                throw new TicketIsNotExistException();
            ticket = (Ticket)request.getArg();
            if (DataBaseCommand.TicketIsExist(id)) {
                if (DataBaseCommand.AccessToTicket(id, request.getAccount().getLogin())) {
                    DataBaseCommand.UpdateTicket(id,ticket,request.getAccount());
                    ServerMediator.getInstance().remove(id);
                    ServerMediator.getInstance().add(ticket);
                    answer.setToCurrans("Ticket updated successful");
                }
                else
                    answer.setToCurrans("Wrong user");

            }
            else
                answer.setToCurrans("Ticket not found");
            ServerMediator.getInstance().add(ticket);
        } catch (IdLessZeroException |TicketIsNotExistException e) {
            answer.setToCurrans(e.toString());
        } catch (SQLException e) {
            answer.setToCurrans(e.toString());
        }
    }
    
}
