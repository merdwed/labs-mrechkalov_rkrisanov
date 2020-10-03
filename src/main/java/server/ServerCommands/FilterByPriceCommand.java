package server.ServerCommands;

import server.ServerMediator;

import java.io.IOException;
import java.util.stream.Collectors;

public class FilterByPriceCommand extends Command {
    public static void execute() throws IOException {
        try {
            Double price = (Double)server.ServerNet.PackageIn.getInstance().getObjectInputStream().readObject();
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Collection sorted by price");
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(
                    ServerMediator.getInstance().getArrayListCollection().stream().filter(x -> x.getPrice().equals(price)).collect(Collectors.toList()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

   
}
