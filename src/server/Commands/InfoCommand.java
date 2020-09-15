package server.Commands;

import server.ServerMediator;
import server.ServerNet.PackageOut;
import server.ServerNet.SendAnswer;

import java.io.IOException;

public class InfoCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject(ServerMediator.getInstance().getInfo());
        SendAnswer.send();
    }
}
