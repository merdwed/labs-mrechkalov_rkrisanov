package ShellUtils;

import Commands.Command;
import Commands.CommandFactory;
import Commands.CommandParameterDistributor;

/**
 * @author merdwed
 * full static class, main loop for read and execute command. it's calls other basic classes
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 * @see ShellParser
 * @see ShellIO
 */
public class ShellInterpretator {

    /**
     * main loop. method calls parseStringCommand on every command line and executes received Command
     * @see ShellInterpretator#parseStringCommand(String)
     * @see Command
     */
    public static void run(){

        String stringCommand;
        while(true){
            try {
                stringCommand = ShellIO.readString(">");
                if (stringCommand==null || stringCommand.isEmpty()) continue;
                Command command = parseStringCommand(stringCommand);
                command.execute();
            }
            catch(NoSourceException e){
                System.out.println(e.getMessage());
                return;
            }
        }


    }

    /**
     * method calls CommandFactory and CommandParameterDistributor
     * @param stringCommand full input string with name of command and arguments
     * @return Command object, which ready to execute.
     * @throws NoSourceException
     * @see CommandFactory
     * @see CommandParameterDistributor
     * @see Command
     */
    private static Command parseStringCommand(String stringCommand)throws NoSourceException{
        String[] cav=stringCommand.split(" ",2);
        Command tempCommand=null;
        if(cav.length>0)
             tempCommand= CommandFactory.createNewCommand(cav[0]);
        if(cav.length>1) {
            CommandParameterDistributor.fillIn(tempCommand, cav[1]);
        }
        else{
            CommandParameterDistributor.fillIn(tempCommand,null);
        }
        return tempCommand;

    }


}
