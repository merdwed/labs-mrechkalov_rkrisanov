package DataClasses.CommandTypeUtils;

import DataClasses.Ticket;
import DataClasses.TicketType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandTypeInformation {
    private static class CommandTypeInformationObject{
        CommandTypeInformationObject(String sName,String doc, ArrayList<Class> commandTypePC, ArrayList<Class> commandTypeRC){
            commandName=sName;
            documentationString=doc;
            commandTypeParametersClasses=commandTypePC;
            commandTypeResponsedClasses=commandTypeRC;
        }
        String commandName;
        String documentationString;
        ArrayList<Class> commandTypeParametersClasses;
        ArrayList<Class> commandTypeResponsedClasses;
    }
    private static java.util.Map<CommandType, CommandTypeInformationObject> existingCommands = new java.util.HashMap<CommandType,  CommandTypeInformationObject>();
    static{
        existingCommands.put(CommandType.ADD,
        new CommandTypeInformationObject(
            "add",
            "add\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));//да, это уродливая конструкция с рефлексией, оно выглядит страшно но работать будет
               
        
        existingCommands.put(CommandType.ADD_IF_MAX ,
        new CommandTypeInformationObject(
            "add_if_max",
            "add_if_max" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection if there is no cheaper then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));

        existingCommands.put(CommandType.ADD_IF_MIN ,
        new CommandTypeInformationObject(
            "add_if_min",
            "add_if_min" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command adds Ticket to collection if there is no more expensive then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));


        existingCommands.put(CommandType.CLEAR,
        new CommandTypeInformationObject(
            "clear",
            "clear" +
            "    Command removes all ticket from collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
                  
            
        existingCommands.put(CommandType.FILTER_BY_PRICE,
        new CommandTypeInformationObject(
            "filter_by_price",
            "filter_by_price" + " <price>" + "\n" +
            "    <price> - price of ticket, real number" +"\n" +
            "    Command shows all tickets in collection with entered price",
            new ArrayList<Class>(Arrays.asList(Double.class)),
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));


        existingCommands.put(CommandType.FILTER_LESS_THAN_TYPE,
        new CommandTypeInformationObject(
            "filter_less_than_type",
            "filter_less_than_type" + " <type>" + "\n" +
            "    <type> - type of ticket ticket. Can be: "+
            Stream.of( TicketType.values()).map(x -> x.name()).map(x -> x.concat(" ")).reduce("", String::concat)+"\n" +
            "    Command shows all tickets in collection with type less then entered",
            new ArrayList<Class>(Arrays.asList(TicketType.class)),
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));
            
        existingCommands.put(CommandType.INFO,
        new CommandTypeInformationObject(
            "info",
            "info" + "\n" +
            "    Command shows information about current collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        
        
        existingCommands.put(CommandType.PRINT_ASCENDING,
        new CommandTypeInformationObject(
            "print_ascending",
            "print_ascending" + "\n" +
            "    Command sorts by price and shows all elements in collection ",
            null,
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));

        
        existingCommands.put(CommandType.REMOVE_BY_ID,
        new CommandTypeInformationObject(
            "remove_by_id",
            "remove_by_id" + " <id>" + "\n" +
            "    <id> - Ticket id, integer number" +"\n" +
            "    Command removes ticket from collection by id",
            new ArrayList<Class>(Arrays.asList(Long.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        

        existingCommands.put(CommandType.REMOVE_LOWER,
        new CommandTypeInformationObject(
            "remove_lower",
            "remove_lower" + "\n"+
            "  {element}" + "\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command remove all tickets from collection cheaper then entered",
            new ArrayList<Class>(Arrays.asList(Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
        
        existingCommands.put(CommandType.SHOW,
        new CommandTypeInformationObject(
            "show",
            "show" + "\n" +
            "    Command shows all elements in collection",
            null,
            new ArrayList<Class>(Arrays.asList(String.class, java.util.ArrayList.class))
        ));

        existingCommands.put(CommandType.UPDATE,
        new CommandTypeInformationObject(
            "update",
            "update" + " <id> {element}" + "\n" +
            "    <id> - Ticket id, integer number" +"\n" +
            "    {element} - fields of element line by lie" +"\n" +
            "    Command replace ticket in collection by id",
            new ArrayList<Class>(Arrays.asList(Long.class, Ticket.class)),
            new ArrayList<Class>(Arrays.asList(String.class))
        ));
    }
    public static CommandType commandTypeByCommandName(String str){
        for(CommandType cType:existingCommands.keySet()){
            if(existingCommands.get(cType).commandName.equals(str))
                return cType;
        }
        return null;
    }
    public static String commandNameByCommandType(CommandType cType){
        return existingCommands.get(cType).commandName;
    }
    public static ArrayList<Class> NeededParametersOfCommndType(String str){
        return existingCommands.get(commandTypeByCommandName(str)).commandTypeParametersClasses;
    }
    public static ArrayList<Class> NeededParametersOfCommndType(CommandType cType){
        return existingCommands.get(cType).commandTypeParametersClasses;
    }
    public static ArrayList<Class> ResponsedParametersOfCommndType(String str){
        return existingCommands.get(commandTypeByCommandName(str)).commandTypeResponsedClasses;
    }
    public static ArrayList<Class> ResponsedParametersOfCommndType(CommandType cType){
        return existingCommands.get(cType).commandTypeResponsedClasses;
    }
    public static ArrayList<String> getArrayOfDocumentation(){
        return new ArrayList<String>(existingCommands.values().stream().map(x -> x.documentationString).collect(Collectors.toList()));
    }
}
