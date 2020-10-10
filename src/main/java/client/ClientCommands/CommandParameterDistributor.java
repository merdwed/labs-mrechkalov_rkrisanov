package client.ClientCommands;

import DataClasses.TicketType;
import DataClasses.Ticket;
import DataClasses.Account;
import client.ShellUtils.ShellParser;
import client.ShellUtils.ShellIO;
import client.ShellUtils.NoSourceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * full static class for filling in the Command parameters
 * @author merdwed
 */
public class CommandParameterDistributor {
    /**
     * method looks which parameters are needed for the command 
     * 
     * @see ParameterizedCommand#pushParameter(Object)
     * @see ParameterizedCommand#parameterClassIterator()
     * @param command just put your Command here
     * @param vararg  input string after name of command in console
     * @throws NoSourceException
     */
    public static ArrayList<Object> readArgArrayList(Command command, String vararg) throws NoSourceException {
        if((command instanceof ParameterizedCommand)==false)
            return null; 
        ArrayList<Object> ar=new ArrayList<Object>();
        Iterator<String> stringIterator=null;
        if (vararg != null && vararg.isEmpty()==false)
            stringIterator = Arrays.asList(vararg.split(" ")).iterator();
        Iterator<Class> parameterIterator=((ParameterizedCommand) command).parameterClassStack.iterator();
        
        
        Class currentParameterType;
        String currentString;
        while (parameterIterator.hasNext()){
            try {
                if (stringIterator != null && stringIterator.hasNext())
                    currentString = stringIterator.next();
                else
                    currentString = null;
                currentParameterType = parameterIterator.next();
             
                if (currentParameterType == String.class) {
                    if (currentString != null && ShellParser.parseString(currentString) != null) {
                        ar.add(ShellParser.parseString(currentString));
                    } else {
                        ar.add(ShellIO.readString("enter the string:"));
                    }
                    continue;
                }
                if (currentParameterType == Long.class) {
                    if (currentString != null && ShellParser.parseLong(currentString) != null) {
                        ar.add(ShellParser.parseLong(currentString));
                    } else {
                        ar.add(ShellIO.readLong("enter the number:"));
                    }
                    continue;
                }
                if (currentParameterType == Double.class) {
                    if (currentString != null && ShellParser.parseDouble(currentString) != null) {
                        ar.add(ShellParser.parseDouble(currentString));
                    } else {
                        ar.add(ShellIO.readDouble("enter the number:"));
                    }
                    continue;
                }
                if (currentParameterType == Ticket.class) {
                    ar.add(ShellIO.readTicket());
                    continue;
                }
                if (currentParameterType == TicketType.class) {
                    if (currentString != null && ShellParser.parseTicketType(currentString.toUpperCase()) != null) {
                        ar.add(ShellParser.parseTicketType(currentString.toUpperCase()));
                    } else {
                        ar.add(ShellIO.readTicketType("enter the type:"));
                    }
                    continue;
                }
                if (currentParameterType == Account.class) {
                    ar.add(ShellIO.readAccount());
                    continue;
                }
         }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        return ar;

    }
    public static void fillIn(Command command, List<Object> arrayArg){
        if(arrayArg==null)return;
        if(command instanceof ParameterizedCommand)
        arrayArg.stream().forEach(((ParameterizedCommand)command)::pushParameter);
    }
}
