package Commands;
/**
 * special String parametrized command
 * Can be created when program can't find some things. Cannot be called by user
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see StringParametrizedCommand
 */
public class NotFoundCommand extends Command {
    /**
     * constructor combine String input to final message
     */
    NotFoundCommand(String whatYouCanNotFind){
        element=whatYouCanNotFind;
    }
    private String element;
    /**
     * print message with information what program can not find
     * @see Command#execute()
     * @see CommandFactory#createNotFoundCommand(String)
     */
    @Override
    public void execute(){
        System.out.println("'" + element + "' not found. Try to use 'help'");
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return "Default command for program logic. Can be created when program can't find some things. Cannot be called by user";
    }
}
