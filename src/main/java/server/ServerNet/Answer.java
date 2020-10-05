package server.ServerNet;

import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;

import java.io.IOException;
import java.util.ArrayList;

public class Answer {
    public Answer(){
        packageOut = new PackageOut();
        currans =  new ArrayList<Object>();
    }
    private ArrayList<Object> currans;
    private PackageOut packageOut;
    public void send() throws IOException {
        packageOut.ToClose();
        Connection.getInstance().getServer().send(packageOut.getBufferOut(),Connection.getInstance().getRemoteAdd());
    }
    public void prepare(CommandType commandType) throws IOException {
        ArrayList<Class> requestAnsForm =  CommandTypeInformation.ResponsedParametersOfCommndType(commandType);
        while(requestAnsForm.size()-currans.size()>0)
            currans.add(null);
        for (Object obj:
        currans) {
            packageOut.getObjectOutputStream().writeObject(obj);
        }
    }
    public void setToCurrans(Object obj){
        currans.add(obj);
    }
}
