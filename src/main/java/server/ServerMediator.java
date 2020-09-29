package server;
import DataClasses.Ticket;

import java.util.ArrayList;
import java.util.Comparator;

public class ServerMediator implements ServerCollectionInterface {
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

    public boolean exist(Long id){
        return Dataset.getCurrentInstance().exist(id);
    }

    public ArrayList<Ticket> getArrayListCollection(){
        return Dataset.getCurrentInstance().getArrayListCollection();
    }

    public ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp) {
        ArrayList<Ticket> collection=ServerMediator.getInstance().getArrayListCollection();
        collection.sort(comp);
        return collection;
    }
    @Override
    public String getInfo(){
        return Dataset.getCurrentInstance().getInfo();
    }
}