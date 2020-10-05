package server.ServerCommands;

import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;

public class AddIfMinCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        Ticket ticket;
        ticket = (Ticket) request.getArg();
        if (ServerMediator.getInstance().getArrayListCollection().stream().allMatch(x -> x.getPrice().compareTo(ticket.getPrice()) >= 0)) {
            ServerMediator.getInstance().add(ticket);
            answer.setToCurrans("Ticket added successful");
        } else {
            answer.setToCurrans("Ticket wasn't added");
        }
    }
}
