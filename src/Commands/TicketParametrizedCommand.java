package Commands;
import DataClasses.Ticket;
/**
 * the main abstract parent of all Ticket parametrized commands
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see ParameterizedCommand
 * @see Ticket
 */
public abstract class TicketParametrizedCommand extends ParameterizedCommand {
    protected Ticket parameter=null;
    /**
     * constructor push one Ticket.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public TicketParametrizedCommand(){
        parameterClassStack.push(Ticket.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj){
        if(obj instanceof Ticket || obj==null)
            parameter=(Ticket)obj;
        else
            throw new IllegalArgumentException("Programmer wrote bad code, need Ticket, find "+obj.getClass().toString());
    }
}
