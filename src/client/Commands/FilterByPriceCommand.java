package client.Commands;

import server.Dataset;
import DataClasses.Ticket;
/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see DoubleParametrizedCommand
 */
public class FilterByPriceCommand extends DoubleParametrizedCommand {
    /**
     * command prints elements in collection with price equal to argument
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        for(Ticket element: Dataset.getCurrentInstance().getSortedArrayList(Dataset.priceComparator))
            if(element.getPrice().equals(parameter))
                System.out.println(element.toString());
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + " <price>" + "\n" +
                "    <price> - price of ticket, real number" +"\n" +
                "    Command shows all tickets in collection with entered price";
    }
    @Override
    public String toString() {
        return "filter_by_price";
    }

}
