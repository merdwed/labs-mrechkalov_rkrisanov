package server.ServerNet;

import DataClasses.CommandType;
import java.io.IOException;

import server.Commands.*;

public class ExecuteCommand {
    public static void executeCommand(CommandType commandType) throws IOException {
        switch (commandType){
            case ADD:{AddCommand.execute();break;}
            case ADD_IF_MAX:{AddIfMaxCommand.execute();break;}
            case ADD_IF_MIN:{AddIfMinCommand.execute();break;}
            case SHOW:{ShowCommand.execute();break;}
            case INFO:{InfoCommand.execute();break;}
            case HELP:{HelpCommand.execute();break;}
            case CLEAR:{ClearCommand.execute();break;}
            case REMOVE_BY_ID:{RemoveByIdCommand.execute();break;}
            case REMOVE_LOWER:{RemoveLowerCommand.execute();break;}
            case UPDATE:{UpdateCommand.execute();break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
}
