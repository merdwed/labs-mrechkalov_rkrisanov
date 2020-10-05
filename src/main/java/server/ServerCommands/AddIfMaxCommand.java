package server.ServerCommands;

import DataClasses.Comparators.PriceComparator;
import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.Request;

import java.io.IOException;

public class AddIfMaxCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        Ticket ticket;
            ticket = (Ticket) request.getArg();

            if (ServerMediator.getInstance().getSortedArrayList(new PriceComparator()).get(0).getPrice().compareTo(ticket.getPrice()) < 0) {
                ServerMediator.getInstance().add(ticket);
                answer.setToCurrans("Ticket added successful");
            } else {
                answer.setToCurrans("Ticket wasn't added");
            }
    }
}
