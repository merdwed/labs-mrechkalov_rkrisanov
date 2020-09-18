package DataClasses.ServerCommands;

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

    public static void process() throws IOException {
        try {
            String message = (String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            if (message.equals("Collection sorted by price")) {
                System.out.println(message);
                java.util.ArrayList arr = (java.util.ArrayList) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                System.out.println(arr.toString());
            }
            else{
                System.out.println(message);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
