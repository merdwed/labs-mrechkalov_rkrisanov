package server.Commands;

import DataClasses.Comparators.IdComparator;
import DataClasses.Ticket;
import server.ServerMediator;

public class ClearCommand extends Command {
    public static void execute(){
        ServerMediator.getInstance().getArrayListCollection().forEach(element -> ServerMediator.getInstance().remove(element.getId()));
    }
}
