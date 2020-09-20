package DataClasses.ServerCommands;

import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class InfoCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getInfo());
    }
    public static void process() throws IOException {
        try {
            String message = (String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
