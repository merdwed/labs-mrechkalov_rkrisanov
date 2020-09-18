package DataClasses.CommandTypeUtils;

import DataClasses.Ticket;
import DataClasses.TicketType;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandTypeInformation {
    private static java.util.Map<String, CommandType> existingCommands=new java.util.HashMap<String,  CommandType>();
    private static java.util.Map<CommandType, ArrayList<Class> > commandTypeParametersArray=new java.util.HashMap<CommandType, ArrayList<Class> >();
    private static java.util.Map<String,String> CommandTypeDocumentation=new java.util.HashMap<String,String>();
    static{
        existingCommands.put("add",CommandType.ADD);
        commandTypeParametersArray.put(CommandType.ADD, new ArrayList<Class>(Arrays.asList(Ticket.class)));//да, это уродливая конструкция с рефлексией, оно выглядит страшно но работать будет
        CommandTypeDocumentation.put("add",
        "add\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection");
        
        
        existingCommands.put("add_if_max",CommandType.ADD_IF_MAX );
        commandTypeParametersArray.put(CommandType.ADD_IF_MAX, new ArrayList<Class>(Arrays.asList(Ticket.class)));   
        CommandTypeDocumentation.put("add_if_max",
        "add_if_max" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection if there is no cheaper then entered");


        existingCommands.put("add_if_min",CommandType.ADD_IF_MIN );
        commandTypeParametersArray.put(CommandType.ADD_IF_MIN, new ArrayList<Class>(Arrays.asList(Ticket.class)));  
        CommandTypeDocumentation.put("add_if_min",
        "add_if_min" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command adds Ticket to collection if there is no more expensive then entered");


        existingCommands.put("clear",CommandType.CLEAR );
        commandTypeParametersArray.put(CommandType.CLEAR, null);
        CommandTypeDocumentation.put("clear",
        "clear" +
        "    Command removes all ticket from collection");


//        existingCommands.put("exit",CommandType.EXIT );
//        commandTypeParametersArray.put(CommandType.EXIT, null);
//        CommandTypeDocumentation.put("exit",
//        "exit" +
//        "    Command closes the program without saving");
        

        existingCommands.put("filter_by_price",CommandType.FILTER_BY_PRICE );
        commandTypeParametersArray.put(CommandType.FILTER_BY_PRICE, new ArrayList<Class>(Arrays.asList(Double.class)));
        CommandTypeDocumentation.put("filter_by_price",
        "filter_by_price" + " <price>" + "\n" +
        "    <price> - price of ticket, real number" +"\n" +
        "    Command shows all tickets in collection with entered price");


        existingCommands.put("filter_less_than_type",CommandType.FILTER_LESS_THAN_TYPE);
        commandTypeParametersArray.put(CommandType.FILTER_LESS_THAN_TYPE, new ArrayList<Class>(Arrays.asList(TicketType.class)));
        String tempString = "filter_less_than_type" + " <type>" + "\n" +
                "    <type> - type of ticket ticket. Can be: ";
        for (TicketType tp : TicketType.values())
            tempString = tempString + tp.name() + "  ";
        tempString=tempString + "\n" +
                "    Command shows all tickets in collection with type less then entered";
        CommandTypeDocumentation.put("filter_less_than_type", tempString);


        existingCommands.put("info",CommandType.INFO);
        commandTypeParametersArray.put(CommandType.INFO, null);
        CommandTypeDocumentation.put("info", 
        "info" + "\n" +
        "    Command shows information about current collection");


        existingCommands.put("print_ascending",CommandType.PRINT_ASCENDING);
        commandTypeParametersArray.put(CommandType.PRINT_ASCENDING, null);
        CommandTypeDocumentation.put( "print_ascending", 
        "print_ascending" + "\n" +
        "    Command sorts by price and shows all elements in collection ");

        
        existingCommands.put("remove_by_id",CommandType.REMOVE_BY_ID);
        commandTypeParametersArray.put(CommandType.REMOVE_BY_ID, new ArrayList<Class>(Arrays.asList(Long.class)));
        CommandTypeDocumentation.put("remove_by_id", 
        "remove_by_id" + " <id>" + "\n" +
        "    <id> - Ticket id, integer number" +"\n" +
        "    Command removes ticket from collection by id");


        existingCommands.put("remove_lower",CommandType.REMOVE_LOWER);
        commandTypeParametersArray.put(CommandType.REMOVE_LOWER, new ArrayList<Class>(Arrays.asList(Ticket.class)));
        CommandTypeDocumentation.put("remove_lower", 
        "remove_lower" + "\n"+
        "  {element}" + "\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command remove all tickets from collection cheaper then entered");  

        existingCommands.put("show",CommandType.SHOW);
        commandTypeParametersArray.put(CommandType.SHOW, null);
        CommandTypeDocumentation.put("show", 
        "show" + "\n" +
        "    Command shows all elements in collection");

        existingCommands.put("update",CommandType.UPDATE);// на стороне сервера надо добавить проверку на дурака чтобы id был больше 0, раньше это проверялось при заполнении команды, но теперь клиент может отправить обновление -1 элемента
        commandTypeParametersArray.put(CommandType.UPDATE, new ArrayList<Class>(Arrays.asList(Long.class, Ticket.class)));
        CommandTypeDocumentation.put(  "update",
        "update" + " <id> {element}" + "\n" +
        "    <id> - Ticket id, integer number" +"\n" +
        "    {element} - fields of element line by lie" +"\n" +
        "    Command replace ticket in collection by id");
    }
    public static CommandType commandTypeByStringName(String str){
        return existingCommands.get(str);
    }
    public static ArrayList<Class> NeededParametersOfCommndType(CommandType commandType){
        return commandTypeParametersArray.get(commandType);
    }
    public static ArrayList<String> getArrayOfDocumentation(){
        return new ArrayList<String>(CommandTypeDocumentation.values());
    }
}
