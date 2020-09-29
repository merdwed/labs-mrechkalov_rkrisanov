package client.ClientCommands;

import DataClasses.CommandTypeUtils.CommandTypeInformation;

public class HelpCommand extends Command{
    @Override
    /**
     * command prints all existing commands and their documentation
     * @see Command#execute()
     */
    public void execute(){
        for(String str:CommandFactory.getArrayExistingCommands()){
            System.out.println(CommandFactory.createNewCommand(str).getDocumentation());//неоптимальынй костыль, но работает
        }
        for(String str:CommandTypeInformation.getArrayOfDocumentation()){
            System.out.println(str);
        }
    }

    /**
     * @see Command#getDocumentation()
     */
    @Override
    public String getDocumentation(){
        return  this.toString() + "\n" +
                "    Command show documentation of all existing command";
    }
    @Override
    public String toString() {
        return "help";
    }
    
}
