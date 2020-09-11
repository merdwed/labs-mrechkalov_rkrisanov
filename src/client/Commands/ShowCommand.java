package client.Commands;

import DataClasses.Ticket;
import DataClasses.Comparators.IdComparator;
import client.ClientMediator;
/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class ShowCommand extends Command {
    /**
     * command prints sorted by id elements in current collection
     * @see Command#execute()
     * @see Dataset
     * @see Dataset#idComparator
     */
    @Override
    public void execute(){
        for(Ticket element:ClientMediator.getInstance().getSortedArrayList(new IdComparator()))
        System.out.println(element.toString());
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command shows all elements in collection";
    }
    @Override
    public String toString() {
        return "show";
    }
}
