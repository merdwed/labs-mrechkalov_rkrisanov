package client.ShellUtils;

import DataClasses.CommandTypeUtils.CommandType;
import client.ClientCommands.Command;
import client.ClientCommands.CommandFactory;
import client.ClientCommands.CommandParameterDistributor;
import client.ClientNet.*;

import java.io.IOException;

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
        while (true) {
            try {
                stringCommand = ShellIO.readString(">");
                if (stringCommand == null || stringCommand.isEmpty())
                    continue;
                PackageOut.getInstance().remake();//Я вынужден поместить его здесь, перед parseStringServeCommand
                CommandType commandType=parseStringServerCommand(stringCommand);
                if(commandType==null){//если это не серверная команда
                    Command command = parseStringCommand(stringCommand);//пробовать выполнить клиентскую
                    command.execute();
                }
                else {//если это серверная команда

                    Answer.send();
//                    if (commandType==CommandType.SHOW){
//                        Request request = new Request();
//                        ArrayList hyy = (ArrayList)PackageIn.getInstance().getObjectInputStream().readObject();
//                        System.out.println(hyy.toString());
//                    }
//                    else if(commandType==CommandType.UPDATE){
//                        PackageIn.getInstance().setBufferIn(PackageOut.getInstance().getBufferOut());
//                        Object object = PackageIn.getInstance().getObjectInputStream().readObject();
//                        System.out.println(object.getClass());
//                        object = PackageIn.getInstance().getObjectInputStream().readObject();
//                        System.out.println(object.getClass());
//                    }
                    Request request = new Request();
                    CommandTypeFactory.processCommand(commandType);//CommandTypeFactory.prepareCommand(commandType)
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
    private static Command parseStringCommand(String stringCommand) throws NoSourceException {
        String[] cav = stringCommand.split(" ", 2);
        Command tempCommand = null;
        if (cav.length > 0)
            tempCommand = CommandFactory.createNewCommand(cav[0]);
        if (cav.length > 1) {
            CommandParameterDistributor.fillIn(tempCommand, cav[1]);
        } else {
            CommandParameterDistributor.fillIn(tempCommand, null);
        }
        return tempCommand;

    }

    private static CommandType parseStringServerCommand(String stringCommand) throws NoSourceException, IOException {
        String[] cav=stringCommand.split(" ",2);
        CommandType tempCommand=null;
        if(cav.length>0)
             tempCommand= CommandTypeFactory.getCommandType(cav[0]);
        if(tempCommand == null)
        return null;
        PackageOut.getInstance().getObjectOutputStream().writeObject(tempCommand);//Первым в потоке должен быть тип команды
        if(cav.length>1) {
            CommandTypeParameterDistributor.fillIn(tempCommand, cav[1]);
        }
        else{
            CommandTypeParameterDistributor.fillIn(tempCommand,null);
        }
        return tempCommand;
    }


}
