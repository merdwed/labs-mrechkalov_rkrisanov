package server.ServerNet;

import DataClasses.CommandType;
import java.io.IOException;

import server.Commands.*;

public class ExecuteCommand {
    public static void executeCommand(CommandType commandType) throws IOException {
        switch (commandType){
            case ADD:{AddCommand.execute();break;}
            case ADD_IF_MAX:{break;}
            case ADD_IF_MIN:{break;}
            case SHOW:{ShowCommand.execute();break;}
            case INFO:{InfoCommand.execute();break;}
            case HELP:{break;}
            case CLEAR:{break;}
            case REMOVE_BY_ID:{break;}
            case REMOVE_LOWER:{break;}
            case UPDATE:{break;}
            case EXECUTE_SCRIPT:{break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
}
