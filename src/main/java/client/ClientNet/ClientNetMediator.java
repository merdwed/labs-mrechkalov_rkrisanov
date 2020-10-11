package client.ClientNet;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    static ReentrantLock lock = new ReentrantLock();
    public static PackageOut formThePackageOut(CommandType command, List<Serializable> arrayArg) {
        PackageOut packageOut = new PackageOut();
        packageOut.remake();
        try {
            packageOut.getObjectOutputStream().writeObject(currentAccount);//нулевым в поток должен идти аккаунт
            packageOut.getObjectOutputStream().writeObject(command);// Первым в потоке должен быть тип команды
            CommandTypeParameterDistributor.fillIn(arrayArg,packageOut);//дальше идут параметры
        } catch (IOException e) {
            System.out.println("exception in formThePackageOut");
            e.printStackTrace();
        } 
        return packageOut;
    }
    private static class sendAndRecieve implements Runnable{
        CommandType commandType;
        Request request;
        PackageOut packageOut;
        sendAndRecieve(CommandType commandType, PackageOut packageOut){
            this.commandType=commandType;
            this.request=new Request();
            this.packageOut = packageOut;
            System.out.println("Constructor!");
        }
        @Override
        public void run(){
            lock.lock();
            System.out.println("locked in thread");
            try {
                System.out.println("start thread!");
                GlobalWindow.getInstance().setHostAndPortColor(Color.PINK);
           
                Answer.send(packageOut);
           
                while(true){
                //request=new Request();
                
                boolean received = request.receive();
                lock.unlock();
                System.out.println("unlocked in thread");
                System.out.println("recieved!");
                GlobalWindow.getInstance().setHostAndPortColor(Color.GREEN);
                CompletableFuture.runAsync(()-> {
                    try {
                        ArrayList<Object> arr = CommandTypeResponseDecoder.decode(
                            CommandTypeInformation.ResponsedParametersOfCommndType(commandType),
                            request.prossessing());
                        CommandTypeResponseDecoder.process(commandType, arr);
                        arr.stream().filter(Objects::nonNull).forEach(System.out::println);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
                Thread.currentThread().sleep(5);
                }
            }catch (AsynchronousCloseException|InterruptedException e){

            }catch(UnresolvedAddressException e){

            }
            catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("exception in sendAndRecieve");
            }
            
        }
    };
    private static Thread th=null;
    public static void sendAndRecieveFromServer(CommandType commandType,PackageOut packageOut) {
        lock.lock();
        System.out.println("locked in main");
        if(th!=null){
            th.interrupt();
            System.out.println("end thread!");
        }
        Connection.getInstance().reinit();
        

        th=new Thread(new sendAndRecieve(commandType,packageOut));
        
        th.start();
        
        lock.unlock();
        System.out.println("unlocked in main");
        while(lock.isLocked()==false);
    
    }
}
