package client.ClientNet;
import DataClasses.CommandTypeUtils.CommandTypeInformation;//вот это должно быть в jar файле
import DataClasses.CommandTypeUtils.CommandType;//и это тоже
import DataClasses.ServerCommands.*;//Ну и это, раз такое дело пошло

import java.io.IOException;

public class CommandTypeFactory {
    public static CommandType getCommandType(String commandName){
        return CommandTypeInformation.commandTypeByStringName(commandName);
    }
    public static void prepareCommand(CommandType commandType){
        switch (commandType){
            case ADD:{;break;}
            case ADD_IF_MAX:{;break;}
            case ADD_IF_MIN:{;break;}
            case SHOW:{;break;}
            case INFO:{;break;}
            case CLEAR:{;break;}
            case REMOVE_BY_ID:{;break;}
            case REMOVE_LOWER:{;break;}
            case FILTER_BY_PRICE:{;break;}
            case FILTER_LESS_THAN_TYPE:{;break;}
            case PRINT_ASCENDING:{;break;}
            case UPDATE:{;break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
    public static void processCommand(CommandType commandType) throws IOException {
        switch (commandType){
            case ADD:{AddCommand.process();break;}
            case ADD_IF_MAX:{AddIfMaxCommand.process();break;}
            case ADD_IF_MIN:{AddIfMinCommand.process();break;}
            case SHOW:{ShowCommand.process();break;}
            case INFO:{InfoCommand.process();break;}
            case CLEAR:{ClearCommand.process();break;}
            case REMOVE_BY_ID:{RemoveByIdCommand.process();break;}
            case REMOVE_LOWER:{RemoveLowerCommand.process();break;}
            case FILTER_BY_PRICE:{FilterByPriceCommand.process();break;}
            case FILTER_LESS_THAN_TYPE:{FilterLessThanTypeCommand.process();break;}
            case PRINT_ASCENDING:{ PrintAscendingCommand.process();break;}
            case UPDATE:{UpdateCommand.process();break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
    
}
