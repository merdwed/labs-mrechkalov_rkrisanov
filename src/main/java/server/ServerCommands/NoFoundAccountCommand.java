package server.ServerCommands;

import server.ServerMediator;
import server.ServerNet.PackageOut;

import java.io.IOException;

public class NoFoundAccountCommand {
    public static void execute() throws IOException {
        PackageOut.getInstance().getObjectOutputStream().writeObject("Not Found current Account, use create_account");
    }

}
