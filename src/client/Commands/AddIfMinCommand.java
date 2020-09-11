package client.Commands;
import server.Dataset;
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
        if(Dataset.getCurrentInstance().getSortedArrayList(Dataset.priceComparator).get(0).getPrice().compareTo(parameter.getPrice())>0)
            Dataset.getCurrentInstance().add(parameter);
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
