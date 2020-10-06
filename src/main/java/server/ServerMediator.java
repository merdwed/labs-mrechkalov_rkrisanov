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
        Dataset.getCurrentInstance().lock.writeLock().lock();
        Dataset.getCurrentInstance().add(element);
        Dataset.getCurrentInstance().lock.writeLock().unlock();
    }
    @Override
    public void remove(Long id) {
        Dataset.getCurrentInstance().lock.writeLock().lock();
        Dataset.getCurrentInstance().remove(id);
        Dataset.getCurrentInstance().lock.writeLock().unlock();
    }

    public boolean exist(Long id){
        Dataset.getCurrentInstance().lock.readLock().lock();
        boolean b= Dataset.getCurrentInstance().exist(id);
        Dataset.getCurrentInstance().lock.readLock().unlock();
        return b;
    }

    public ArrayList<Ticket> getArrayListCollection(){
        Dataset.getCurrentInstance().lock.readLock().lock();
        ArrayList<Ticket> temp= Dataset.getCurrentInstance().getArrayListCollection();
        Dataset.getCurrentInstance().lock.readLock().unlock();
        return temp;
    }

    public ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp) {
        Dataset.getCurrentInstance().lock.readLock().lock();
        ArrayList<Ticket> collection=ServerMediator.getInstance().getArrayListCollection();
        Dataset.getCurrentInstance().lock.readLock().unlock();
        collection.sort(comp);
        return collection;
    }
    @Override
    public String getInfo(){
        Dataset.getCurrentInstance().lock.readLock().lock();
        String tempString =Dataset.getCurrentInstance().getInfo();
        Dataset.getCurrentInstance().lock.readLock().unlock();
        return tempString;
    }
}