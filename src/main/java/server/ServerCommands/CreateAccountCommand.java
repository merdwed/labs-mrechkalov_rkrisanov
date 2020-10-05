package server.ServerCommands;

import DataClasses.Account;
import server.DataBase.DataBaseCommand;
import server.ServerMediator;
import server.ServerNet.Answer;
import server.ServerNet.Request;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountCommand extends Command {
    public static void execute(Request request, Answer answer) throws IOException {
        Account account = (Account)request.getArg();
        if (account.getPassword()==null)
            account = new Account(account.getLogin(),"");
        try {
            DataBaseCommand.CreateNewAccount(account.getLogin(),account.getPassword());
            answer.setToCurrans("Account created successful");
        } catch (SQLException e) {
            answer.setToCurrans(e.toString());
        }

    }

}
