package client.ClientCommands;
import DataClasses.Account;
/**
 * the main abstract parent of all Account parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 */
public abstract class AccountParametrizedCommand extends ParameterizedCommand{

    protected Account parameter=null;
    /**
     * constructor push one Account.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public AccountParametrizedCommand(){
        parameterClassStack.push(Account.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof Account || obj==null)
            parameter=(Account)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need String, find "+obj.getClass().toString());
    }
}
