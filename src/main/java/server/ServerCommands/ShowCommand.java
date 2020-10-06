package server.ServerCommands;

import DataClasses.Comparators.NameComparator;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

public class ShowCommand extends Command {
    public void execute(Request request, Answer answer)  {
        answer.setToCurrans("Collection:");
        answer.setToCurrans(ServerMediator.getInstance().getSortedArrayList(new NameComparator()));
    }


}
