package client.Commands;
/**
 * the main abstract parent of all String parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 */
public abstract class StringParametrizedCommand extends ParameterizedCommand{

    protected String parameter=null;
    /**
     * constructor push one String.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public StringParametrizedCommand(){
        parameterClassStack.push(String.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof String || obj==null)
            parameter=(String)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need String, find "+obj.getClass().toString());
    }
}
