package DataClasses.CommandTypeUtils;

import DataClasses.Ticket;
import DataClasses.TicketType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandTypeInformation {
    private static class CommandTypeInformationObject{
        CommandTypeInformationObject(CommandType cType,String doc, ArrayList<Class> commandTypePC, ArrayList<Class> commandTypeRC){
            commandType=cType;
            documentationString=doc;
            commandTypeParametersClasses=commandTypePC;
            commandTypeResponsedClasses=commandTypeRC;
        }
        CommandType commandType;
        String documentationString;
        ArrayList<Class> commandTypeParametersClasses;
        ArrayList<Class> commandTypeResponsedClasses;
    }
    private static java.util.Map<String, CommandTypeInformationObject> existingCommands = new java.util.HashMap<String,  CommandTypeInformationObject>();
    static{
        existingCommands.put("add",
        new CommandTypeInformationObject(
            CommandType.ADD,
            "add\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));//да, это уродливая конструкция с рефлексией, оно выглядит страшно но работать будет
               
        
        existingCommands.put("add_if_max",
        new CommandTypeInformationObject(
            CommandType.ADD_IF_MAX ,
            "add_if_max" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection if there is no cheaper then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));

        existingCommands.put("add_if_min",
        new CommandTypeInformationObject(
            CommandType.ADD_IF_MIN ,
            "add_if_min" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection if there is no more expensive then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));


        existingCommands.put("clear",
        new CommandTypeInformationObject(
            CommandType.CLEAR,
            "clear" +
            "    Command removes all ticket from collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
                  
            
        existingCommands.put("filter_by_price",
        new CommandTypeInformationObject(
            CommandType.FILTER_BY_PRICE,
            "filter_by_price" + " <price>" + "\n" +
            "    <price> - price of ticket, real number" +"\n" +
            "    Command shows all tickets in collection with entered price",
            new ArrayList<Class>(Arrays.asList(Double.class)),
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));


        existingCommands.put("filter_less_than_type",
        new CommandTypeInformationObject(
            CommandType.FILTER_LESS_THAN_TYPE,
            "filter_less_than_type" + " <type>" + "\n" +
            "    <type> - type of ticket ticket. Can be: "+
            Stream.of( TicketType.values()).map(x -> x.name()).map(x -> x.concat(" ")).reduce("", String::concat)+"\n" +
            "    Command shows all tickets in collection with type less then entered",
            new ArrayList<Class>(Arrays.asList(TicketType.class)),
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));
            
        existingCommands.put("info",
        new CommandTypeInformationObject(
            CommandType.INFO,
            "info" + "\n" +
            "    Command shows information about current collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        
        
        existingCommands.put("print_ascending",
        new CommandTypeInformationObject(
            CommandType.PRINT_ASCENDING,
            "print_ascending" + "\n" +
            "    Command sorts by price and shows all elements in collection ",
            null,
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));

        
        existingCommands.put("remove_by_id",
        new CommandTypeInformationObject(
            CommandType.REMOVE_BY_ID,
            "remove_by_id" + " <id>" + "\n" +
            "    <id> - Ticket id, integer number" +"\n" +
            "    Command removes ticket from collection by id",
            new ArrayList<Class>(Arrays.asList(Long.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        

        existingCommands.put("remove_lower",
        new CommandTypeInformationObject(
            CommandType.REMOVE_LOWER,
            "remove_lower" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command remove all tickets from collection cheaper then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        
        existingCommands.put("show",
        new CommandTypeInformationObject(
            CommandType.SHOW,
            "show" + "\n" +
            "    Command shows all elements in collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));

        existingCommands.put("update",
        new CommandTypeInformationObject(
            CommandType.UPDATE,// на стороне сервера надо добавить проверку на дурака чтобы id был больше 0, раньше это проверялось при заполнении команды, но теперь клиент может отправить обновление -1 элемента
            "update" + " <id> {element}" + "\n" +
            "    <id> - Ticket id, integer number" +"\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command replace ticket in collection by id",
            new ArrayList<Class>(Arrays.asList(Long.class, Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
    }
    public static CommandType commandTypeByStringName(String str){
        return existingCommands.get(str).commandType;
    }
    public static ArrayList<Class> NeededParametersOfCommndType(String str){
        return existingCommands.get(str).commandTypeParametersClasses;
    }
    public static ArrayList<Class> ResponsedParametersOfCommndType(String str){
        return existingCommands.get(str).commandTypeResponsedClasses;
    }
    public static ArrayList<String> getArrayOfDocumentation(){
        return new ArrayList<String>(existingCommands.values().stream().map(x -> x.documentationString).collect(Collectors.toList()));
    }
}
