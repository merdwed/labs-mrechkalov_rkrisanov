package client.Commands;

import DataClasses.Comparators.PriceComparator;
import client.ClientMediator;
/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketParametrizedCommand
 */
public class AddIfMinCommand extends TicketParametrizedCommand {
    /**
     * command adds new input element to current collection if there isn't less for the price
     * @see Command#execute()
     * @see Dataset
     * */
    @Override
    public void execute(){
        if(ClientMediator.getInstance().getSortedArrayList(new PriceComparator()).get(0).getPrice().compareTo(parameter.getPrice())>0)
            ClientMediator.getInstance().add(parameter);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n"+
                "  {element}" + "\n" +
                "    {element} - fields of element line by lie" +"\n" +
                "    Command adds Ticket to collection if there is no more expensive then entered";
    }
    @Override
    public String toString(){
        return "add_if_min";
    }
}
