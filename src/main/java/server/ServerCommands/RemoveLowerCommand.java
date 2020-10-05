package server.ServerCommands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;

public class RemoveLowerCommand extends Command{
    public static void execute(Request request, Answer answer) throws IOException {
        Ticket ticket = (Ticket) request.getArg();
        answer.setToCurrans("Every ticket from the collection less the ticket is removed");
        ServerMediator.getInstance().getArrayListCollection().stream().filter
                (x -> x.getPrice().compareTo(ticket.getPrice()) < 0).forEach(x -> ServerMediator.getInstance().remove(x.getId()));
    }
}
