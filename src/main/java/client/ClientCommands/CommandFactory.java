package client.ClientCommands;

import java.util.ArrayList;
import java.util.Collections;
/**
* @author merdwed
*/
public class CommandFactory {
    /**
     * full static class for creation Command
     * @value map collection. keys is string names of command, values is command Class
     */
    private static java.util.Map<String, Class<? extends Command>> existingCommands=new java.util.HashMap<String, Class<? extends Command>>();
    static{
        existingCommands.put("execute_script", ExecuteScriptCommand.class);
        existingCommands.put("exit",ExitCommand.class);
        existingCommands.put("print_ascending",PrintAscendingCommand.class);
        existingCommands.put("help",HelpCommand.class);
    }

    /**
     * @return sorted ArrayList of String names of existing commands
     */
    public static ArrayList<String> getArrayExistingCommands(){
        ArrayList<String> temp=new ArrayList<String>(existingCommands.keySet());
        Collections.sort(temp);
        return temp;
    }

    /**
     * generate Command objects, uses string name of command
     * @param commandName String name of command
     * @return new Command object
     */
    public static Command createNewCommand(String commandName){
        Class commandClass=existingCommands.get(commandName);
        if(commandClass==null) {
            return createNotFoundCommand(commandName);
        }

        Command command=null;
        try{command = (Command)commandClass.newInstance();}
        catch(Exception e)
        {System.out.println("programmer wrote some really bad code"); System.out.println(e.getMessage());}


        return command;
    }

    /**
     * creation special system command NotFoundCommand when th program can not find some string element
     * @param whatYouCanNotFind string name of element, will be printed with message
     * @return new Command object
     * @see NotFoundCommand
     */
    public static Command createNotFoundCommand(String whatYouCanNotFind){
        return new NotFoundCommand(whatYouCanNotFind);
    }



}
