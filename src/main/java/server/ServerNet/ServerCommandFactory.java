package server.ServerNet;

import java.io.IOException;

import DataClasses.CommandTypeUtils.CommandType;
import server.ServerCommands.*;

public class ServerCommandFactory  {
    public static void executeCommand(Request request,Answer answer) throws IOException {
        switch (request.getCommandType()){
            case ADD:{AddCommand.execute(request,answer);break;}
            case ADD_IF_MAX:{AddIfMaxCommand.execute(request,answer);break;}
            case ADD_IF_MIN:{AddIfMinCommand.execute(request,answer);break;}
            case SHOW:{ShowCommand.execute(request,answer);break;}
            case INFO:{InfoCommand.execute(request,answer);break;}
            case CLEAR:{ClearCommand.execute(request,answer);break;}
            case REMOVE_BY_ID:{RemoveByIdCommand.execute(request,answer);break;}
            case REMOVE_LOWER:{RemoveLowerCommand.execute(request,answer);break;}
            case FILTER_BY_PRICE:{FilterByPriceCommand.execute(request,answer);break;}
            case FILTER_LESS_THAN_TYPE:{FilterLessThanTypeCommand.execute(request,answer);break;}
            case PRINT_ASCENDING:{PrintAscendingCommand.execute(request,answer);break;}
            case UPDATE:{UpdateCommand.execute(request,answer);break;}
            case CREATE_ACCOUNT:{CreateAccountCommand.execute(request,answer);break;}
            case DELETE_ACCOUNT:{DeleteAccountCommand.execute(request,answer);break;}
            default:
                System.out.println("Команда не найдена");
        }
    }
}
