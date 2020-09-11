package client.Commands;

import DataClasses.Ticket;
import DataClasses.TicketType;
import DataClasses.Comparators.TicketTypeComparator;
import client.ClientMediator;
/**
 * TicketType parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketTypeParametrizedCommand
 */
public class FilterLessThanTypeCommand extends TicketTypeParametrizedCommand {
    /**
     * command prints elements in collection with TicketType less then parameter
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        for(Ticket element: ClientMediator.getInstance().getSortedArrayList(new TicketTypeComparator()))
            if(element.getType().compareTo(parameter) <= 0)
                System.out.println(element.toString());
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation() {
        String tempString = this.toString() + " <type>" + "\n" +
                "    <type> - type of ticket ticket. Can be: ";
        for (TicketType tp : TicketType.values())
            tempString = tempString + tp.name() + "  ";

        tempString=tempString + "\n" +
                "    Command shows all tickets in collection with type less then entered";
        return tempString;
    }
    @Override
    public String toString() {
        return "filter_less_than_type";
    }
}
