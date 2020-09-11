package client.Commands;

import DataClasses.TicketType;
/**
 * the main abstract parent of all TicketType parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 * @see TicketType
 */
public abstract class TicketTypeParametrizedCommand extends ParameterizedCommand{
    protected TicketType parameter=null;
    /**
     * constructor push one TicketType.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public TicketTypeParametrizedCommand(){
        parameterClassStack.push(TicketType.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof TicketType  || obj==null)
            parameter=(TicketType)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need TicketType, find "+obj.getClass().toString());
    }
}
