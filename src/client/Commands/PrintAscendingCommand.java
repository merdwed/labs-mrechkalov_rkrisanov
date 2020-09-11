package client.Commands;

import DataClasses.Comparators.PriceComparator;
import client.ClientMediator;

/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class PrintAscendingCommand extends Command {
    /**
     * command prints sorted by price elements in current collection
     * @see Command#execute()
     * @see Dataset
     * @see Dataset#priceComparator
     */
    @Override
    public void execute(){
        for(DataClasses.Ticket element:ClientMediator.getInstance().getSortedArrayList(new PriceComparator()))
            System.out.println(element.toString());
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command sorts by price and shows all elements in collection ";
    }
    @Override
    public String toString() {
        return "print_ascending";
    }
}
