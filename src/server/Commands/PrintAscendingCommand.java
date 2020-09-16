package server.Commands;

import DataClasses.Comparators.PriceComparator;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class PrintAscendingCommand {
    public static void execute() throws IOException {
        PackageOut.getInstance().remake();
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getSortedArrayList(new PriceComparator()));
        Answer.send();
    }
}
