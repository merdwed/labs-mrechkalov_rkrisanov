package server.ServerCommands;

import DataClasses.Comparators.NameComparator;
import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class PrintAscendingCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject("Collection sorted by name:");
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getSortedArrayList(new NameComparator()));
    }
   
}
