package client.ClientNet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DataClasses.Ticket;
import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;
import client.GraphicsUtils.Dataset;
import client.GraphicsUtils.GlobalWindow;

public class CommandTypeResponseDecoder {
    public static ArrayList<Object> decode(ArrayList<Class> classes, PackageIn packageIn)throws IOException{
        ArrayList<Object> tempArray=new ArrayList<Object>();
        for(Class currentClass:classes){
            try{
                if(currentClass==String.class){
                    String message = (String)packageIn.getObjectInputStream().readObject();
                    tempArray.add(message);
                    continue;
                }
                if(currentClass==ArrayList.class){
                    java.util.ArrayList arr = (java.util.ArrayList) packageIn.getObjectInputStream().readObject();
                    tempArray.add(arr);
                    continue;
                }
                if(currentClass==Boolean.class){
                    Boolean b=(Boolean) packageIn.getObjectInputStream().readObject();
                    tempArray.add(b);
                    continue;
                }
            }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Произошла ошибка при попытке десериализовать сообщение");
            }
            System.out.println("unsupported Class " + currentClass.toString() + " please look do response decoder");
        }
        return tempArray; 
    }
    public static void process(CommandType command, List<Object> response){
        int index=0;
        for(Class cl: CommandTypeInformation.ResponsedParametersOfCommndType(command)){
            if(cl == String.class){
                if(((String)response.get(index)).equals("execute show")){
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                    ClientNetMediator.formThePackageOut(CommandType.SHOW, null)
                    );
                    //GlobalWindow.getInstance().update(null);
                }
                else{
                GlobalWindow.getInstance().printInformation((String)response.get(index));
                }
            }
            if(cl == ArrayList.class){
                Dataset.getCurrentInstance().update((ArrayList<Ticket>)(response.get(index)));
                GlobalWindow.getInstance().update(null);
            }
            index++;
        }

    }
}
