package server.ServerCommands;

import DataClasses.Comparators.NameComparator;
import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class ShowCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject("Collection:");
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getSortedArrayList(new NameComparator()));
    }


}
