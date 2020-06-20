package Commands;
/**
 * String parametrized command
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see StringParametrizedCommand
 */
public class ExecuteScriptCommand extends StringParametrizedCommand{

    /**
     * command pushes new file source of command to ShellIO class
     * @see Command#execute()
     * @see ShellUtils.ShellIO
     */
    @Override
    public void execute(){
        ShellUtils.ShellIO.initAndPushSource(parameter);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + " <file_name>" + "\n" +
                "    <file_name> - argument with scripts path" +"\n" +
                "    Command opens script file and execute command like from console";
    }
    @Override
    public String toString(){
        return "execute_script";
    }
}
