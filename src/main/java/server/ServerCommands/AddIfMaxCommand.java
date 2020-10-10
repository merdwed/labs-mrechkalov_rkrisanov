package server.ServerCommands;

import DataClasses.Comparators.PriceComparator;
import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.ActiveUsers;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class AddIfMaxCommand extends Command {
    public void execute(Request request, Answer answer) {
        Ticket ticket;
            ticket = (Ticket) request.getArg();
        try {
            if (ServerMediator.getInstance().getSortedArrayList(new PriceComparator()).get(0).getPrice().compareTo(ticket.getPrice()) < 0) {
                DataBaseCommand.AddTicket(ticket,request.getAccount());
                ticket.setId(DataBaseCommand.getCurrId());
                ticket.setCreator(request.getAccount().getLogin());
                answer.setToCurrans("Ticket added successful");
                ActiveUsers.getInstance().CollectionChanged();
            } else {
                answer.setToCurrans("Ticket didn't add");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
