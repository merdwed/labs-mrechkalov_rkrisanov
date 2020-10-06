package server.ServerCommands;

import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class ClearCommand extends Command {
    public void execute(Request request, Answer answer) {
        ServerMediator.getInstance().getArrayListCollection().forEach(element -> {
                    try {
                        if (DataBaseCommand.TicketIsExist(element.getId())) {
                            if (DataBaseCommand.AccessToTicket(element.getId(), request.getAccount().getLogin())) {
                                DataBaseCommand.RemoveTicket(element.getId());
                                ServerMediator.getInstance().remove(element.getId());
                                ServerMediator.getInstance().remove(element.getId());
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        }
        );
        answer.setToCurrans("User's tickets is cleaned up");
    }
}
