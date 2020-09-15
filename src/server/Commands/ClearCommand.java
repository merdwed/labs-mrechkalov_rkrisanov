package server.Commands;

import DataClasses.Comparators.IdComparator;
import DataClasses.Ticket;
import server.ServerMediator;

public class ClearCommand extends Command {
    public static void execute(){
        for(Ticket element:ServerMediator.getInstance().getSortedArrayList(new IdComparator()))
            ServerMediator.getInstance().remove(element.getId());
    }
}
