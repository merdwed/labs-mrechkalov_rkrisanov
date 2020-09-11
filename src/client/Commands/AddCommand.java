package client.Commands;
import server.Dataset;

/**
 * Ticket parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see TicketParametrizedCommand
 */
public class AddCommand extends TicketParametrizedCommand {
    /**
     * command adds new input element to current collection
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
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
                "    Command adds Ticket to collection";
    }
    @Override
    public String toString(){
        return "add";
    }
}
