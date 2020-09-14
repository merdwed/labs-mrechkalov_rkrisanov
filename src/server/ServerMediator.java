package server;
import DataClasses.Ticket;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerMediator implements ServerCollectionInterface, Serializable {
    private static ServerMediator instance=new ServerMediator();
    public static ServerMediator getInstance(){//yeah, its Singleton and what, how can I make better?
        return instance;
    }
    @Override
    public void add(Ticket element) {
        Dataset.getCurrentInstance().add(element);
    }
    @Override
    public void remove(Long id) {
        Dataset.getCurrentInstance().remove(id);
    }
    @Override
    public ArrayList<Ticket> getArrayListCollection(){
        return Dataset.getCurrentInstance().getArrayListCollection();
    }
    @Override
    public String getInfo(){
        return Dataset.getCurrentInstance().getInfo();
    }
}