package server.ServerCommands;

import server.ServerMediator;

import java.io.IOException;

public class ClearCommand extends Command {
    public static void execute() throws IOException {
        ServerMediator.getInstance().getArrayListCollection().forEach(element -> ServerMediator.getInstance().remove(element.getId()));
        server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Collection is cleaned up");
    }
}
