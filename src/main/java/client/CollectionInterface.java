package client;

import java.util.Comparator;

import DataClasses.Ticket;

/**
 * interface standardizes interaction with collection
 * @author merdwed
 */
interface CollectionInterface {
    /**
     * @param element be added to collection
     */
    public void add(Ticket element);

    /**
     * @param id indicates which ite to remove of collection
     */
    public void remove(Long id);

    /**
     * @param comp comparator for sorting
     * @return sorted ArrayList of all items in collection
     */
    public java.util.ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp);

    /**
     * @return String-format common information about current collection
     */
    public String getInfo();
}
