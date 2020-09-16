package client.ClientCommands;
import DataClasses.TicketType;
import DataClasses.Ticket;
import client.ShellUtils.ShellParser;
import client.ShellUtils.ShellIO;
import client.ShellUtils.NoSourceException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * full static class for filling in the Command parameters
 * @author merdwed
 */
public class CommandParameterDistributor {
    /**
     * method looks which parameters are needed for the command and fill in by using pushParameter
     * @see ParameterizedCommand#pushParameter(Object)
     * @see ParameterizedCommand#parameterClassIterator()
     * @param command just put your Command here
     * @param vararg input string after name of command in console
     */
    public static void fillIn(Command command, String vararg)throws NoSourceException{
        if(command instanceof ParameterizedCommand) {
            Iterator<String> stringIterator;
            if (vararg != null)
                stringIterator = Arrays.asList(vararg.split(" ")).iterator();
            else
                stringIterator = null;
            Class currentParameterType;
            String currentString;
            while (((ParameterizedCommand) command).parameterClassStack.size()!=0) {//да, тут можно бы было использовать итератор, но со стеком итератор не дружит, когда внезапно на верхушку может положиться новый элемент
                try {
                    if (stringIterator != null && stringIterator.hasNext())
                        currentString = stringIterator.next();
                    else
                        currentString = null;
                    currentParameterType = ((ParameterizedCommand) command).parameterClassStack.pop();


                    if (currentParameterType == String.class) {
                        if (currentString != null && ShellParser.parseString(currentString) != null) {
                            ((ParameterizedCommand) command).pushParameter(ShellParser.parseString(currentString));
                        } else {
                            ((ParameterizedCommand) command).pushParameter(ShellIO.readString("enter the string:"));
                        }
                        continue;
                    }
                    if (currentParameterType == Long.class) {
                        if (currentString != null && ShellParser.parseLong(currentString) != null) {
                            ((ParameterizedCommand) command).pushParameter(ShellParser.parseLong(currentString));
                        } else {
                            ((ParameterizedCommand) command).pushParameter(ShellIO.readLong("enter the number:"));
                        }
                        continue;
                    }
                    if (currentParameterType == Double.class) {
                        if (currentString != null && ShellParser.parseDouble(currentString) != null) {
                            ((ParameterizedCommand) command).pushParameter(ShellParser.parseDouble(currentString));
                        } else {
                            ((ParameterizedCommand) command).pushParameter(ShellIO.readDouble("enter the number:"));
                        }
                        continue;
                    }
                    if (currentParameterType == Ticket.class) {
                        ((ParameterizedCommand) command).pushParameter(ShellIO.readTicket());
                        continue;
                    }
                    if (currentParameterType == TicketType.class) {
                        if (currentString != null && ShellParser.parseTicketType(currentString.toUpperCase()) != null) {
                            ShellParser.parseTicketType(currentString.toUpperCase());
                        } else {
                            ((ParameterizedCommand) command).pushParameter(ShellIO.readTicketType("enter the type:"));
                        }
                        continue;
                    }


                }
                catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
