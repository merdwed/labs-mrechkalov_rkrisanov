package server.ServerCommands;

import DataClasses.Account;
import server.DataBase.DataBaseCommand;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccountCommand extends Command {
    public void execute(Request request, Answer answer) {
        Account account = request.getAccount();
        if (account.getPassword()==null)
            account = new Account(account.getLogin(),"");
        try {
            DataBaseCommand.DeleteAccount(account.getLogin(),account.getPassword());
            answer.setToCurrans("Account deleted successful");
        } catch (SQLException e) {
            answer.setToCurrans(e.toString());
        }

    }
}
