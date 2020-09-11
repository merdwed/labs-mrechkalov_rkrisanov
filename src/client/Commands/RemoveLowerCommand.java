package client.Commands;

import DataClasses.Ticket;
import DataClasses.Comparators.PriceComparator;
import client.ClientMediator;
/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketParametrizedCommand
 */
public class RemoveLowerCommand extends TicketParametrizedCommand {
    /**
     * command remove element in current collection with price less then input element's price
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        for(Ticket element:ClientMediator.getInstance().getSortedArrayList(new PriceComparator()))
            if(element.getPrice().compareTo(parameter.getPrice())<0)
            ClientMediator.getInstance().remove(element.getId());
    }
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n"+
                "  {element}" + "\n" +
                "    {element} - fields of element line by lie" +"\n" +
                "    Command remove all tickets from collection cheaper then entered";
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String toString(){
        return "remove_lower";
    }
}
