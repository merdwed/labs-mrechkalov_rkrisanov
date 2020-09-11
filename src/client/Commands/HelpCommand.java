package client.Commands;
/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class HelpCommand extends Command {
    /**
     * command prints all existing commands and their documentation
     * @see Command#execute()
     * @see Command#getDocumentation()
     */
    @Override
    public void execute(){
       for(String str:CommandFactory.getArrayExistingCommands()){
           System.out.println(CommandFactory.createNewCommand(str).getDocumentation());//неоптимальынй костыль, но работает
       }
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command shows all existing command";
    }
    @Override
    public String toString() {
        return "help";
    }
}
