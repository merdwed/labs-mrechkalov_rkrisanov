package client.ClientCommands;
/**
 * the main abstract parent of all Long parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 */
public abstract class LongParametrizedCommand extends ParameterizedCommand {
    protected Long parameter=null;
    /**
     * constructor push one Long.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public LongParametrizedCommand(){
        parameterClassStack.push(Long.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof Long  || obj==null)
            parameter=(Long)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need Long, find "+obj.getClass().toString());
    }
}
