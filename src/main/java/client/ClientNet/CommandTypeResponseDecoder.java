package client.ClientNet;

import java.io.IOException;
import java.util.ArrayList;

public class CommandTypeResponseDecoder {
    public static ArrayList<Object> decode(ArrayList<Class> classes)throws IOException{
        ArrayList<Object> tempArray=new ArrayList<Object>();
        for(Class currentClass:classes){
            try{
                if(currentClass==String.class){
                    String message = (String)client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                    //System.out.println(message);
                    tempArray.add(message);
                    continue;
                }
                if(currentClass==ArrayList.class){
                    java.util.ArrayList arr = (java.util.ArrayList) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                    tempArray.add(arr);
                    //System.out.println(arr.toString());
                    continue;
                }
                if(currentClass==Boolean.class){
                    Boolean b=(Boolean) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
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
}
