package server.ServerCommands;

import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;
import java.util.stream.Collectors;

public class FilterByPriceCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
            Double price = (Double)request.getArg();
            answer.setToCurrans("Collection sorted by price");
            answer.setToCurrans(
                    ServerMediator.getInstance().getArrayListCollection().stream().filter(x -> x.getPrice().equals(price)).collect(Collectors.toList()));
    }
}
