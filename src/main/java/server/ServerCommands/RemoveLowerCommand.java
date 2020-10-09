package server.ServerCommands;

import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.ActiveUsers;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveLowerCommand extends Command{
    public void execute(Request request, Answer answer) {
        Ticket ticket = (Ticket) request.getArg();
        answer.setToCurrans("Every ticket from the collection less the ticket is removed");
        ActiveUsers.getInstance().CollectionChanged();
        ServerMediator.getInstance().getArrayListCollection().stream().filter
                (x -> x.getPrice().compareTo(ticket.getPrice()) < 0).forEach(x -> {
            try {
                if (DataBaseCommand.TicketIsExist(x.getId())) {
                    if (DataBaseCommand.AccessToTicket(x.getId(), request.getAccount().getLogin())) {
                        DataBaseCommand.RemoveTicket(x.getId());
                        ServerMediator.getInstance().remove(x.getId());
                        ServerMediator.getInstance().remove(x.getId());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
