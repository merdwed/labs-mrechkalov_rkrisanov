package client.ClientNet;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import DataClasses.Account;
import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;
import client.GraphicsUtils.GlobalWindow;
import client.ShellUtils.NoSourceException;

public class ClientNetMediator {
    private static Account currentAccount = null;

    public static void setCurrentAccount(Account cA) {
        currentAccount = cA;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void formThePackageOut(CommandType command, List<Serializable> arrayArg) {
        PackageOut.getInstance().remake();
        try {
            PackageOut.getInstance().getObjectOutputStream().writeObject(currentAccount);//нулевым в поток должен идти аккаунт
            PackageOut.getInstance().getObjectOutputStream().writeObject(command);// Первым в потоке должен быть тип команды
            CommandTypeParameterDistributor.fillIn(arrayArg);//дальше идут параметры
        } catch (IOException e) {
            System.out.println("exception in formThePackageOut");
            e.printStackTrace();
        } 
    }
    private static class sendAndRecieve implements Runnable{
        CommandType commandType;
        Request request;
        sendAndRecieve(CommandType commandType, Request request){
            this.commandType=commandType;
            this.request=request;
        }
        @Override
        public void run(){
            GlobalWindow.getInstance().setHostAndPortColor(Color.PINK);
            try {
                Answer.send();
                
                boolean received = request.receive();
                GlobalWindow.getInstance().setHostAndPortColor(Color.GREEN);
                request.prossessing();
                ArrayList<Object> arr = CommandTypeResponseDecoder
                        .decode(CommandTypeInformation.ResponsedParametersOfCommndType(commandType));
                CommandTypeResponseDecoder.process(commandType, arr);
                arr.stream().filter(Objects::nonNull).forEach(System.out::println);
            }catch (AsynchronousCloseException e){

            }catch(UnresolvedAddressException e){

            }
            catch (IOException | IllegalArgumentException e) {
                System.out.println("exception in sendAndRecieve");
            }
            
            System.out.println("end of thred");
        }
    };
    private static Thread th=null;
    public static void sendAndRecieveFromServer(CommandType commandType) {
        if(th!=null)
            th.interrupt();
        th=new Thread(new sendAndRecieve(commandType,new Request()));
        th.start();
    
    }
}
