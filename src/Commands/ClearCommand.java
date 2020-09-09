package Commands;

import DataClasses.Dataset;
import DataClasses.Ticket;

/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class ClearCommand extends Command {
    /**
     * command clears current collection
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        for(Ticket element:Dataset.getCurrentInstance().getSortedArrayList(Dataset.idComparator))
            Dataset.getCurrentInstance().remove(element.getId());
    }

    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() +
                "    Command removes all ticket from collection";
    }
    @Override
    public String toString(){
        return "clear";
    }
}
