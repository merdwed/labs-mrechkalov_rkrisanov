package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import DataClasses.Ticket;
import server.ServerMediator;

public class ClientMediator implements CollectionInterface {
    private static ClientMediator instance=new ClientMediator();
    public static ClientMediator getInstance(){//yeah, its Singleton and what, how can I make better?
        return instance;
    }
    @Override
    public void add(Ticket element) {
        ServerMediator.getInstance().add(element);
    }

    @Override
    public void remove(Long id) {
        ServerMediator.getInstance().remove(id);
    }

    @Override
    public ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp) {
        ArrayList<Ticket> collection=ServerMediator.getInstance().getArrayListCollection();
        Collections.sort(collection,comp);
        return collection;
    }

    @Override
    public String getInfo() {
        return ServerMediator.getInstance().getInfo();
    }
   
}
