package server;
import DataClasses.Ticket;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * interface standardizes interaction with collection on server part
 * @author merdwed
 */
interface ServerCollectionInterface {
    /**
     * @param element be added to collection
     */
    public void add(Ticket element);

    /**
     * @param id indicates which ite to remove of collection
     */
    public void remove(Long id);

    /**
     * @return returned ArrayList of all items in collection
     */
    public ArrayList<Ticket> getArrayListCollection();
    /**
     * @return returned Sorted ArrayList of all items in collection
     */
    public java.util.ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp);


    /**
     * @return String-format common information about current collection
     */
    public String getInfo();
}
