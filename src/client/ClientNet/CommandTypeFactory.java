package client.ClientNet;
import DataClasses.CommandTypeUtils.CommandTypeInformation;//вот это должно быть в jar файле
import DataClasses.CommandTypeUtils.CommandType;//и это тоже
public class CommandTypeFactory {
    public static CommandType getCommandType(String commandName){
        return CommandTypeInformation.commandTypeByStringName(commandName);
    }
    
}
