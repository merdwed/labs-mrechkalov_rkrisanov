package server.ServerCommands;

import DataClasses.Ticket;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class AddCommand extends Command {

    public void execute(Request request,Answer answer) {
        try {
            Ticket ticket = (Ticket) request.getArg();
            DataBaseCommand.AddTicket(ticket,request.getAccount());
            ticket.setId(DataBaseCommand.getCurrId());
            ServerMediator.getInstance().add(ticket);
            answer.setToCurrans("Ticket added successful");
        } catch (SQLException e) {
            e.printStackTrace();
            answer.setToCurrans("Ticket didn't added");
        }

    }

}
