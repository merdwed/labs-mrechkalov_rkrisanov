package client.Commands;

import client.ClientMediator;

/**
 * Long parametrized command
 * 
 * @author merdwed
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see LongParametrizedCommand
 */
public class RemoveByIdCommand extends LongParametrizedCommand{
    /**
     * command remove element in current collection by id
     * @see Command#execute()
     * @see Dataset
     */
    @Override
    public void execute(){
        ClientMediator.getInstance().remove(parameter);
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + " <id>" + "\n" +
                "    <id> - Ticket id, integer number" +"\n" +
                "    Command removes ticket from collection by id";
    }
    @Override
    public String toString(){
        return "remove_by_id";
    }
}
