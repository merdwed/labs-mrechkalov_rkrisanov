package server.ServerCommands;

import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageOut;
import server.ServerNet.Request;

import java.io.IOException;

public class InfoCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        answer.setToCurrans(ServerMediator.getInstance().getInfo());
    }
  
}
