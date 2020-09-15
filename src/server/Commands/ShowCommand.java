package server.Commands;

import server.ServerMediator;
import server.ServerNet.PackageOut;
import server.ServerNet.SendAnswer;
import DataClasses.Ticket;
import java.io.IOException;

public class ShowCommand extends Command {
    public static void execute() throws IOException {
        PackageOut.getInstance().remake();
        PackageOut.getInstance().getObjectOutputStream().writeInt(ServerMediator.getInstance().getArrayListCollection().size());
        for (DataClasses.Ticket i:
                ServerMediator.getInstance().getArrayListCollection()) {
            PackageOut.getInstance().getObjectOutputStream().writeObject(i);
        }
        PackageOut.getInstance().ToClose();
        SendAnswer.send();
    }
}
