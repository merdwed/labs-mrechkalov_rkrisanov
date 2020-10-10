package client.ClientNet;

import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;
import DataClasses.Account;
import DataClasses.Ticket;
import DataClasses.TicketType;
import client.ShellUtils.NoSourceException;
import client.ShellUtils.ShellIO;
import client.ShellUtils.ShellParser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandTypeParameterDistributor {
    /**
     * method looks which parameters are needed for the command and fill in by using
     * pushParameter
     *
     * @see ParameterizedCommand#pushParameter(Object)
     * @see ParameterizedCommand#parameterClassIterator()
     * @param commandName just put your Command here
     * @param vararg  input string after name of command in console
     * @throws IOException
     * @throws NoSourceException
     */
    public static ArrayList<Serializable> readArgArrayList(CommandType commandType, String vararg) throws NoSourceException {
        if(CommandTypeInformation.NeededParametersOfCommndType(commandType)==null)
                    return null;
        ArrayList<Serializable> ar=new ArrayList<Serializable>();
        Iterator<String> stringIterator=null;
        Iterator<Class> parameterIterator=CommandTypeInformation.NeededParametersOfCommndType(commandType).iterator();
                if(vararg != null && vararg.isEmpty()==false)
                    stringIterator = Arrays.asList(vararg.split(" ")).iterator();
                Class currentParameterType;
                String currentString;
                while (parameterIterator.hasNext()) {
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
    public static void fillIn(List<Serializable> arrayArg, PackageOut packageOut)throws IOException {
        if(arrayArg==null)return;
        for(Object t:arrayArg)
            packageOut.getObjectOutputStream().writeObject(t);

    }
}