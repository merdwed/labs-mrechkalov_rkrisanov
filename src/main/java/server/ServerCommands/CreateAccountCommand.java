package server.ServerCommands;

import DataClasses.Account;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountCommand extends Command {
    public static void execute() throws IOException {
        Account account = Request.getInstance().getAccount();
        if (account.getPassword()==null)
            account = new Account(account.getLogin(),"");
        try {
            DataBaseCommand.CreateNewAccount(account.getLogin(),account.getPassword());
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject("Account created successful");
        } catch (SQLException e) {
            server.ServerNet.PackageOut.getInstance().getObjectOutputStream().writeObject(e.toString());
        }

    }

}
