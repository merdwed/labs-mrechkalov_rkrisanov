package Commands;
/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class ExitCommand extends Command{
    @Override
    /**
     * command closes program
     * @see Command#execute()
     */
    public void execute(){
        System.exit(0);
    }

    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command closes the program without saving";
    }
    @Override
    public String toString() {
        return "exit";
    }
}
