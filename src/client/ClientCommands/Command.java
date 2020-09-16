package client.ClientCommands;
/**
 * the main abstract parent of all commands
 * @author merdwed
 */
public abstract class Command {

    /**
     * the main logic body of all command
     */
    public abstract void execute();

    /**
     * @return user friendly manual how to call a command
     */
    public abstract String getDocumentation();
}
