package server.ServerCommands;

import DataClasses.Comparators.NameComparator;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageOut;
import server.ServerNet.Request;

import java.io.IOException;

public class PrintAscendingCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        answer.setToCurrans("Collection sorted by name:");
        answer.setToCurrans(ServerMediator.getInstance().getSortedArrayList(new NameComparator()));
    }
   
}
