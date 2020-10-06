package server.ServerNet;

import java.io.IOException;

import DataClasses.CommandTypeUtils.CommandType;
import server.ServerCommands.*;

public class ServerCommandFactory  {
    public static Command createCommand(CommandType ct) throws IOException {
        switch (ct){
            case ADD:{return new AddCommand();}
            case ADD_IF_MAX:{return new AddIfMaxCommand();}
            case ADD_IF_MIN:{return new AddIfMinCommand();}
            case SHOW:{return new ShowCommand();}
            case INFO:{return new InfoCommand();}
            case CLEAR:{return new ClearCommand();}
            case REMOVE_BY_ID:{return new RemoveByIdCommand();}
            case REMOVE_LOWER:{return new RemoveLowerCommand();}
            case FILTER_BY_PRICE:{return new FilterByPriceCommand();}
            case FILTER_LESS_THAN_TYPE:{return new FilterLessThanTypeCommand();}
            case PRINT_ASCENDING:{return new PrintAscendingCommand();}
            case UPDATE:{return new UpdateCommand();}
            case CREATE_ACCOUNT:{return new CreateAccountCommand();}
            case DELETE_ACCOUNT:{return new DeleteAccountCommand();}
            default:
                System.out.println("Команда не найдена");
                return null;
        }
    }
}
