package Commands;

/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
import DataClasses.Dataset;

public class InfoCommand extends Command {
    /**
     * command prints information about current collection
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        System.out.println(Dataset.getCurrentInstance().getInfo());
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command shows information about current collection";
    }
    @Override
    public String toString() {
        return "info";
    }
}
