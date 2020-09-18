package DataClasses.ServerCommands;

import DataClasses.Comparators.NameComparator;
import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class ShowCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject("Collection:");
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getSortedArrayList(new NameComparator()));
    }

    public static void process() throws IOException {
        try {
            String message = (String)client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(message);
            java.util.ArrayList arr = (java.util.ArrayList) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(arr.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
