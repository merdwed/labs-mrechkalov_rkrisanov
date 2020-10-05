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
import java.util.ArrayList;
import java.util.Arrays;

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
    private static Account currentAccount=null;
    public static void setCurrentAccount(Account acc){
        currentAccount=acc;
    }
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
                
                PackageOut.getInstance().remake();//Я вынужден поместить его здесь, перед parseStringServeCommand
                CommandType commandType=parseStringServerCommand(commandName);
                if(commandType==null){//если клиентская команда
                    Command command = parseStringCommand(commandName);//пробовать найти клиентскую
                    formClientCommandArg(command,vararg);// в stream отбрасываеся первая часть
                    command.execute();
                }
                else{//если это серверная команда
                    if(currentAccount==null){
                        System.out.println("you can't use server commands without signing in account. use sign_in");
                        continue;
                    }
                    formThePackageOut(commandType,commandName,vararg);// в stream отбрасываеся первая часть
                    long timeout;
                    boolean received;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    do {
                        Answer.send();
                        timeout = System.currentTimeMillis();
                        received=false;
                        while (!received) {
                            received = Request.getInstance().receive();
                            if (System.currentTimeMillis() - timeout > 1000)
                                break;
                        }
                        if (!received) {
                            System.out.println("Server is not available");
                            System.out.print("Send again?(y/n)\n>");
                        }
                        } while((!received || reader.ready()) && reader.readLine().equals("y"));
                    if (received) {
                        Request.getInstance().prossessing();
                        ArrayList<Object> arr=CommandTypeResponseDecoder.decode(CommandTypeInformation.ResponsedParametersOfCommndType(commandName));
                        arr.stream().forEach(System.out::println);
                    }
                    //ВОТ ТУТ НАДО ВСТАВИТЬ КОД КОТОРЫЙ БУДЕТ ЖДАТЬ СООБЩЕНИЯ ИЗ СЕРВЕРА

                }
            } catch (NoSourceException|IOException  e) {
                System.out.println(e.getMessage());
                return;
            }
        }

    }

    /**
     * method calls CommandFactory and CommandParameterDistributor
     * 
     * @param stringCommand full input string with name of command and arguments
     * @return Command object, which ready to execute.
     * @throws NoSourceException
     * @see CommandFactory
     * @see CommandParameterDistributor
     * @see Command
     */
    private static Command parseStringCommand(String stringCommand){
        String[] cav = stringCommand.split(" ", 2);
        if (cav.length > 0)
            return  CommandFactory.createNewCommand(cav[0]);
        return null;

    }
    private static void formClientCommandArg(Command command, String vararg) throws NoSourceException {
        CommandParameterDistributor.fillIn(command, vararg);
    }
    private static CommandType parseStringServerCommand(String stringCommand) {
        String[] cav=stringCommand.split(" ",2);
        if(cav.length>0)
             return CommandTypeInformation.commandTypeByCommandName(cav[0]);        
        return null;
    }
    private static void formThePackageOut(CommandType command, String commandName, String vararg)throws NoSourceException, IOException {
        //PackageOut.getInstance().getObjectOutputStream().writeObject(currentAccount);//нулевым в поток должен идти аккаунт
        PackageOut.getInstance().getObjectOutputStream().writeObject(command);//Первым в потоке должен быть тип команды
        CommandTypeParameterDistributor.fillIn(commandName, vararg);//дальше идут параметры

    }

}
