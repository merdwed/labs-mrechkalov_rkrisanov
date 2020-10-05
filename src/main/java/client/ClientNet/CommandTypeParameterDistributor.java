package client.ClientNet;

import DataClasses.CommandTypeUtils.CommandTypeInformation;
import DataClasses.Account;
import DataClasses.Ticket;
import DataClasses.TicketType;
import client.ShellUtils.NoSourceException;
import client.ShellUtils.ShellIO;
import client.ShellUtils.ShellParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

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
    public static void fillIn(String commandName, String vararg) throws IOException, NoSourceException {

                Iterator<String> stringIterator;
                if(CommandTypeInformation.NeededParametersOfCommndType(commandName)==null)
                    return;
                Iterator<Class> parameterIterator=CommandTypeInformation.NeededParametersOfCommndType(commandName).iterator();
                if(vararg != null && vararg.isEmpty()==false)
                    stringIterator = Arrays.asList(vararg.split(" ")).iterator();
                else
                    stringIterator = null;
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
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellParser.parseString(currentString));
                            } else {
                               PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readString("enter the string:"));
                            }
                            continue;
                        }
                        if (currentParameterType == Long.class) {
                            if (currentString != null && ShellParser.parseLong(currentString) != null) {
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellParser.parseLong(currentString));
                            } else {
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readLong("enter the number:"));
                            }
                            continue;
                        }
                        if (currentParameterType == Double.class) {
                            if (currentString != null && ShellParser.parseDouble(currentString) != null) {
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellParser.parseDouble(currentString));
                            } else {
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readDouble("enter the number:"));
                            }
                            continue;
                        }
                        if (currentParameterType == Ticket.class) {
                            PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readTicket());
                            continue;
                        }
                        if (currentParameterType == TicketType.class) {
                            if (currentString != null && ShellParser.parseTicketType(currentString.toUpperCase()) != null) {
                                ShellParser.parseTicketType(currentString.toUpperCase());
                            } else {
                                PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readTicketType("enter the type:"));
                            }
                            continue;
                        }
                        if (currentParameterType == Account.class) {
                            PackageOut.getInstance().getObjectOutputStream().writeObject(ShellIO.readAccount());
                            continue;
                        }
    
    
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
            
    
        }
    }
    