package DataClasses.CommandTypeUtils;

import DataClasses.TicketType;

public class CommandTypeFactory {
    private static java.util.Map<String, CommandType> existingCommands=new java.util.HashMap<String,  CommandType>();
    private static java.util.Map<String,String> CommandTypeDocumentation=new java.util.HashMap<String,String>();

    static{
        existingCommands.put("add",CommandType.ADD);
        CommandTypeDocumentation.put("add",
        "add\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection");
        
        
        existingCommands.put("add_if_max",CommandType.ADD_IF_MAX );
        CommandTypeDocumentation.put("add_if_max",
        "add_if_max" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection if there is no cheaper then entered");


        existingCommands.put("add_if_min",CommandType.ADD_IF_MIN );
        CommandTypeDocumentation.put("add_if_min",
        "add_if_min" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection if there is no more expensive then entered");


        existingCommands.put("clear",CommandType.CLEAR );
        CommandTypeDocumentation.put("clear",
        "clear" +
        "    Command removes all ticket from collection");


//        existingCommands.put("exit",CommandType.EXIT );
//        commandTypeParametersArray.put(CommandType.EXIT, null);
//        CommandTypeDocumentation.put("exit",
//        "exit" +
//        "    Command closes the program without saving");
        

        existingCommands.put("filter_by_price",CommandType.FILTER_BY_PRICE );
        CommandTypeDocumentation.put("filter_by_price",
        "filter_by_price" + " <price>" + "\n" +
        "    <price> - price of ticket, real number" +"\n" +
        "    Command shows all tickets in collection with entered price");


        existingCommands.put("filter_less_than_type",CommandType.FILTER_LESS_THAN_TYPE);
        String tempString = "filter_less_than_type" + " <type>" + "\n" +
                "    <type> - type of ticket ticket. Can be: ";
        for (TicketType tp : TicketType.values())
            tempString = tempString + tp.name() + "  ";
        tempString=tempString + "\n" +
                "    Command shows all tickets in collection with type less then entered";
        CommandTypeDocumentation.put("filter_less_than_type", tempString);


        existingCommands.put("info",CommandType.INFO);
        CommandTypeDocumentation.put("info", 
        "info" + "\n" +
        "    Command shows information about current collection");


        existingCommands.put("print_ascending",CommandType.PRINT_ASCENDING);
        CommandTypeDocumentation.put( "print_ascending", 
        "print_ascending" + "\n" +
        "    Command sorts by price and shows all elements in collection ");

        
        existingCommands.put("remove_by_id",CommandType.REMOVE_BY_ID);
        CommandTypeDocumentation.put("remove_by_id", 
        "remove_by_id" + " <id>" + "\n" +
        "    <id> - Ticket id, integer number" +"\n" +
        "    Command removes ticket from collection by id");


        existingCommands.put("remove_lower",CommandType.REMOVE_LOWER);
        CommandTypeDocumentation.put("remove_lower", 
        "remove_lower" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command remove all tickets from collection cheaper then entered");  

        existingCommands.put("show",CommandType.SHOW);
        CommandTypeDocumentation.put("show", 
        "show" + "\n" +
        "    Command shows all elements in collection");

        existingCommands.put("update",CommandType.UPDATE);// на стороне сервера надо добавить проверку на дурака чтобы id был больше 0, раньше это проверялось при заполнении команды, но теперь клиент может отправить обновление -1 элемента
        CommandTypeDocumentation.put(  "update",
        "update" + " <id> {element}" + "\n" +
        "    <id> - Ticket id, integer number" +"\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command replace ticket in collection by id");
    }
    public static CommandType getCommandType(String commandName){
        return existingCommands.get(commandName);
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
    /*public static void processCommand(CommandType commandType) throws IOException {
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
    }*/
    
}
