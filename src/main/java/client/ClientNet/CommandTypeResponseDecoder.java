package client.ClientNet;

import java.io.IOException;
import java.util.ArrayList;

public class CommandTypeResponseDecoder {
    public static void decode(ArrayList<Class> classes)throws IOException{
        for(Class currentClass:classes){
            try{
                if(currentClass==String.class){
                    String message = (String)client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                    System.out.println(message);
                    continue;
                }
                if(currentClass==ArrayList.class){
                    java.util.ArrayList arr = (java.util.ArrayList) client.ClientNet.PackageIn.getInstance().getObjectInputStream().readObject();
                    System.out.println(arr.toString());
                    continue;
                }
            }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Произошла ошибка при попытке десериализовать сообщение");
            }
            System.out.println("unsupported Class " + currentClass.toString() + " please look do response decoder");
        }
          
    }
}
