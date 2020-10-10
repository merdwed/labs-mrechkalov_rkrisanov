package client.ClientCommands;

import client.ClientNet.ClientNetMediator;
import client.ShellUtils.ShellInterpretator;

/**
 * Account parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see AccountParametrizedCommand
 */
public class SignOutCommand extends Command {

    /**
     * 
     * @see Command#execute()
    */
    @Override
    public void execute(){
        ClientNetMediator.setCurrentAccount(null);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command allows you to sign out from your account";
    }
    @Override
    public String toString(){
        return "sign_out";
    }
}
