package server;

import DataClasses.Comparators.IdComparator;
import DataClasses.Comparators.PriceComparator;
import DataClasses.Comparators.TicketTypeComparator;
import DataClasses.Ticket;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Drukharion
 * @see server.ServerCollectionInterface
 */
public class Dataset implements ServerCollectionInterface, Serializable {
    private static Dataset currentInstance=new Dataset();

    public static Dataset getCurrentInstance() {
        return currentInstance;
    }

    private java.util.HashSet<Ticket> collection = new java.util.HashSet<Ticket>();
    public ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * @param element be added to collection
     */
    public void add(Ticket element) {
        if(element==null)return;
        if(element.getId()==null || element.getId()==0) {
            ArrayList<Ticket> temp = getSortedArrayList(idComparator);
            Long index = 1L;
            for (Ticket el : temp) {
                if (!el.getId().equals(index)) {
                    break;
                }
                index++;
            }
            element.setId(index);
        }
        element.setCreationDate(LocalDate.now());
        collection.add(element);
    }

    /**
     * @param id indicates which ite to remove of collection
     */
    public void remove(Long id) {
        if(id==null)return;
        for (Ticket element: collection) {
            if(element.getId().equals(id)){
                collection.remove(element);
                return;
            }
        }
    }
    public boolean exist(Long id){
        if(id==null)return false;
        for (Ticket element: collection) {
            if(element.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public Ticket getTicket(Long id){
        if(exist(id))
        for (Ticket element: collection) {
            if(element.getId().equals(id)){
                return element;
            }
        }
        return null;
    }

    /**
     * @param comp comparator for sorting
     * @return temp - sorted collection list
     */
    public ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp) {

        ArrayList<Ticket> temp=new ArrayList<Ticket>(collection);
        temp.sort(comp);
        return temp;
    }
    public ArrayList<Ticket> getArrayListCollection(){
        return new ArrayList<Ticket>(collection);
    }

    /**
     * @return size of collection
     */
    public String getInfo(){
        return "Size: " + collection.size();
    }
    public static Comparator<Ticket>priceComparator = new PriceComparator();
    public static Comparator<Ticket>idComparator = new IdComparator();
    public static Comparator<Ticket>ticketTypeComparator=new TicketTypeComparator().thenComparing(new PriceComparator());


}
