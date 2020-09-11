package client.Commands;

import client.ClientMediator;

/**
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */

public class InfoCommand extends Command {
    /**
     * command prints information about current collection
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        System.out.println(ClientMediator.getInstance().getInfo());
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
