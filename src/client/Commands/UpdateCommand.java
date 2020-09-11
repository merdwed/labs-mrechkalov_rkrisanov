package client.Commands;

import DataClasses.Ticket;
import client.ClientMediator;
/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketParametrizedCommand
 */
public class UpdateCommand extends ParameterizedCommand{
    private int parameterCount;
    private Long idParameter=null;
    private Ticket ticketParameter=null;
    /**
     * constructor push one Long.class value and one Ticket.class value to parameterClassStack
     * @see ParameterizedCommand#parameterClassStack
     */
    public UpdateCommand(){
        parameterCount=0;
        parameterClassStack.push(Ticket.class);
        parameterClassStack.push(Long.class);
    }
    /**
     * @see ParameterizedCommand#pushParameter(Object)
     */
    @Override
    public void pushParameter(Object obj ){
        if(parameterCount==0)
        {
            if(obj instanceof Long ) {
                if((Long)obj<=0) {
                    parameterClassStack.push(Long.class);//может это и костыль, но работает это очень красиво
                    throw new IllegalArgumentException("'" + ((Long) obj).toString() + "' - wrong value id has to be more than 0");
                }
                idParameter=(Long)obj;
                parameterCount++;
            }
            else
                if(obj==null){
                    parameterClassStack.pop();//if first arg is null, don't need another,
                }
                else
                    throw new IllegalArgumentException("Programmer wrote bad code, need Long, find "+obj.getClass().toString());
        }
        else{
            if(obj instanceof Ticket  || obj==null){
                ticketParameter=(Ticket)obj;
            }
            else
                throw new IllegalArgumentException("Programmer wrote bad code, need Ticket, find "+obj.getClass().toString());
        }
    }
    /**
     * command replace element in current collection by id with input element
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        ClientMediator.getInstance().remove(idParameter);
        if(ticketParameter!=null)
        ticketParameter.setId(idParameter);
        ClientMediator.getInstance().add(ticketParameter);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + " <id> {element}" + "\n" +
                "    <id> - Ticket id, integer number" +"\n" +
                "    {element} - fields of element line by lie" +"\n" +
                "    Command replace ticket in collection by id";
    }
    @Override
    public String toString(){
        return "update";
    }

}
