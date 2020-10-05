package server.ServerCommands;

import DataClasses.Exception.WrongTicketTypeException;
import DataClasses.TicketType;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageOut;
import server.ServerNet.Request;

import java.io.IOException;
import java.util.stream.Collectors;

public class FilterLessThanTypeCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        TicketType ticketType;
            ticketType = (TicketType) request.getArg();
            answer.setToCurrans("Collection sorted less by ticketType"+ticketType.toString());
            answer.setToCurrans(
                     ServerMediator.getInstance().getArrayListCollection().stream().
                            filter(x -> x.getType().compareTo(ticketType) < 0).collect(Collectors.toList()));
    }

   
}
