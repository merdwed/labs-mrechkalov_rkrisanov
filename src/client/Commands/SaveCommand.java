package client.Commands;

import server.JsonFile;
import java.io.IOException;
//писал Drukharion, а javadoc коммент писал merdwed
/**
 * @author Drukharion
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 */
public class SaveCommand extends Command{
    /**
     * command save current collection to file
     * @see Command#execute()
     * @see JsonFile#writeJSON()
     */
    public void execute(){
        try {
            JsonFile.getJsonFile().writeJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command save collection to file";
    }
    @Override
    public String toString(){
        return "save";
    }
}
