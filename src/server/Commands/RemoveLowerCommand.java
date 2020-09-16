package server.Commands;

import DataClasses.Comparators.PriceComparator;
import DataClasses.Ticket;
import server.ServerMediator;
import server.ServerNet.PackageIn;
import server.ServerNet.PackageOut;
import server.ServerNet.Answer;

import java.io.IOException;

public class RemoveLowerCommand extends Command{
    public static void execute() throws IOException {
        Double price = PackageIn.getInstance().getObjectInputStream().readDouble();
        ServerMediator.getInstance().getArrayListCollection().stream().filter
                (x -> x.getPrice().compareTo(price) < 0).forEach(x -> ServerMediator.getInstance().remove(x.getId()));
    }
}
