package server.Commands;

import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.PackageIn;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class FilterByPriceCommand {
    public static void execute() throws IOException {
        Double price = PackageIn.getInstance().getObjectInputStream().readDouble();
        PackageOut.getInstance().remake();
        PackageOut.getInstance().getObjectOutputStream().writeObject(
                ServerMediator.getInstance().getArrayListCollection().stream().filter(x -> x.getPrice().equals(price)));
        Answer.send();
    }
}
