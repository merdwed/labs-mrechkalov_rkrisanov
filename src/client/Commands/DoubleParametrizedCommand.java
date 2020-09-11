package client.Commands;
/**
 * the main abstract parent of all Double parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 */
public abstract class DoubleParametrizedCommand extends ParameterizedCommand{
    protected Double parameter=null;

    /**
     * constructor push one Double.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public DoubleParametrizedCommand(){
        parameterClassStack.push(Double.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof Double  || obj==null)
            parameter=(Double)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need Double, find "+obj.getClass().toString());
    }
}
