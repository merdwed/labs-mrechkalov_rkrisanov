package client.ShellUtils;

import DataClasses.Account;
import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;
import client.ClientCommands.Command;
import client.ClientCommands.CommandFactory;
import client.ClientCommands.CommandParameterDistributor;
import client.ClientNet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author merdwed full static class, main loop for read and execute command.
 *         it's calls other basic classes
 * @see CommandFactory
 * @see CommandParameterDistributor
 * @see Command
 * @see ShellParser
 * @see ShellIO
 */
public class ShellInterpretator {


    /**
     * main loop. method calls parseStringCommand on every command line and executes
     * received Command
     * 
     * @see ShellInterpretator#parseStringCommand(String)
     * @see Command
     */
   
    public static void run() {

        String stringCommand;
        String commandName;
        String vararg;
        while (true) {
            try {
                stringCommand = ShellIO.readString(">");
                if (stringCommand == null || stringCommand.isEmpty())
                    continue;
                commandName=stringCommand.split(" ",2)[0];//первая часть - сама команда
                vararg =Arrays.stream( stringCommand.split(" ",2)).skip(1).reduce("", String::concat);// в stream отбрасываеся первая часть
                
                //Я вынужден поместить его здесь, перед parseStringServeCommand
                CommandType commandType=parseStringServerCommand(commandName);
                if(commandType==null){//если клиентская команда
                    Command command = parseStringCommand(commandName);//пробовать найти клиентскую
                    formClientCommandArg(command,vararg);// в stream отбрасываеся первая часть
                    command.execute();
                }
                else{//если это серверная команда
                    if(commandName.equals(CommandTypeInformation.commandNameByCommandType(CommandType.CREATE_ACCOUNT)))
                        ClientNetMediator.setCurrentAccount(new Account("default","default"));//КОСТЫЛЬ чтобы не отсылать NULL при регистриации
                    if(ClientNetMediator.getCurrentAccount()==null){
                        System.out.println("you can't use server commands without signing in account. use "+CommandTypeInformation.commandNameByCommandType(CommandType.CREATE_ACCOUNT) +" or sign_in");
                        continue;
                    }
                    ArrayList<Serializable> arrayArg=CommandTypeParameterDistributor.readArgArrayList(commandType, vararg);
                    PackageOut packageOut=ClientNetMediator.formThePackageOut(commandType,arrayArg);
                    if(commandName.equals(CommandTypeInformation.commandNameByCommandType(CommandType.CREATE_ACCOUNT)))
                        ClientNetMediator.setCurrentAccount(null);
                   
                    ClientNetMediator.sendAndRecieveFromServer(commandType,packageOut);
                    

                }
            } catch (NoSourceException  e) {
                System.out.println(e.getMessage());
                return;
            }
        }

    }

    
    private static Command parseStringCommand(String stringCommand){
        String[] cav = stringCommand.split(" ", 2);
        if (cav.length > 0)
            return  CommandFactory.createNewCommand(cav[0]);
        return null;

    }
    private static void formClientCommandArg(Command command, String vararg) throws NoSourceException {
        ArrayList<Object> arrayArg = CommandParameterDistributor.readArgArrayList(command, vararg);
        CommandParameterDistributor.fillIn(command,arrayArg);
    }
    private static CommandType parseStringServerCommand(String stringCommand) {
        String[] cav=stringCommand.split(" ",2);
        if(cav.length>0)
             return CommandTypeInformation.commandTypeByCommandName(cav[0]);        
        return null;
    }
   

}
