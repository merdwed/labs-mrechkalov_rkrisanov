package DataClasses.ServerCommands;

import server.ServerMediator;

import java.io.IOException;

public class ClearCommand extends Command {
    public static void execute() throws IOException {
        ServerMediator.getInstance().getArrayListCollection().forEach(element -> ServerMediator.getInstance().remove(element.getId()));
        server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Collection is cleaned up");
}
    public static void process() throws IOException {
        try {
            String message =(String) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка при попытке десериализовать сообщение");
        }
    }
}
