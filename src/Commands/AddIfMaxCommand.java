package Commands;
import DataClasses.Dataset;
/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketParametrizedCommand
 */
public class AddIfMaxCommand extends TicketParametrizedCommand {
    /**
     * command adds new input element to current collection if there isn't more for the price
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        if(Dataset.getCurrentInstance().getSortedArrayList(Dataset.priceComparator).get(0).getPrice().compareTo(parameter.getPrice()) < 0)
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
                "    Command adds Ticket to collection if there is no cheaper then entered";
    }
    @Override
    public String toString(){
        return "add_if_max";
    }
}
