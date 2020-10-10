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
public class SignInCommand extends AccountParametrizedCommand {

    /**
     * 
     * @see Command#execute()
    */
    @Override
    public void execute(){
        ClientNetMediator.setCurrentAccount(parameter);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "  {account}" + "\n" +
                "    {account} - fields of account line by line" +"\n" +
                "    Command allows you to sgin in your account";
    }
    @Override
    public String toString(){
        return "sign_in";
    }
}
