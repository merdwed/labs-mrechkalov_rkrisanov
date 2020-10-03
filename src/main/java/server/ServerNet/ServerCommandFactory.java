package server.ServerNet;

import java.io.IOException;

import DataClasses.CommandTypeUtils.CommandType;
import server.ServerCommands.*;

public class ServerCommandFactory  {
    public static void executeCommand(CommandType commandType) throws IOException {
        server.ServerNet.PackageOut.getInstance().remake();
        switch (commandType){
            case ADD:{AddCommand.execute();break;}
            case ADD_IF_MAX:{AddIfMaxCommand.execute();break;}
            case ADD_IF_MIN:{AddIfMinCommand.execute();break;}
            case SHOW:{ShowCommand.execute();break;}
            case INFO:{InfoCommand.execute();break;}
            case CLEAR:{ClearCommand.execute();break;}
            case REMOVE_BY_ID:{RemoveByIdCommand.execute();break;}
            case REMOVE_LOWER:{RemoveLowerCommand.execute();break;}
            case FILTER_BY_PRICE:{FilterByPriceCommand.execute();break;}
            case FILTER_LESS_THAN_TYPE:{FilterLessThanTypeCommand.execute();break;}
            case PRINT_ASCENDING:{PrintAscendingCommand.execute();break;}
            case UPDATE:{UpdateCommand.execute();break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
}
