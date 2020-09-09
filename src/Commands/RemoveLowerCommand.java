package Commands;

import DataClasses.Dataset;
import DataClasses.Ticket;
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
        for(Ticket element:Dataset.getCurrentInstance().getSortedArrayList(Dataset.priceComparator))
            if(element.getPrice().compareTo(parameter.getPrice())<0)
            Dataset.getCurrentInstance().remove(element.getId());
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
