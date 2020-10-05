package server.ServerCommands;

import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;

public class ClearCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        ServerMediator.getInstance().getArrayListCollection().forEach(element -> ServerMediator.getInstance().remove(element.getId()));
        answer.setToCurrans("Collection is cleaned up");
    }
}
