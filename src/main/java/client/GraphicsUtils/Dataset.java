package client.GraphicsUtils;

import DataClasses.Comparators.IdComparator;
import DataClasses.Comparators.PriceComparator;
import DataClasses.Comparators.TicketTypeComparator;
import DataClasses.Ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Drukharion
 * @see server.ServerCollectionInterface
 */
public class Dataset {
    private static Dataset currentInstance=new Dataset();

    public static Dataset getCurrentInstance() {
        return currentInstance;
    }

    private List<Ticket> collection = new LinkedList<Ticket>();
    public ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * @param element be added to collection
     */
    public void update(List<Ticket> collection) {
        if(collection==null)return;
        this.collection=new LinkedList<Ticket>(collection);
    }

    /**
     * @param id indicates which ite to remove of collection
     */
   
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
    
    public static Comparator<Ticket>priceComparator = new PriceComparator();
    public static Comparator<Ticket>idComparator = new IdComparator();
    public static Comparator<Ticket>ticketTypeComparator=new TicketTypeComparator().thenComparing(new PriceComparator());
    public static Comparator<Ticket>nameComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    public static Comparator<Ticket>creatorComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getCreator().compareTo(o2.getCreator());
        }
    };
    public static Comparator<Ticket>heightComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {

            if(o1.getPerson()==null && o2.getPerson()==null)return 0;
            if(o1.getPerson()==null)return -1;
            if(o2.getPerson()==null)return 1;
            return Double.compare(o1.getPersonHeight(),o2.getPersonHeight());
        }
    };
    public static Comparator<Ticket>weightComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {

            if(o1.getPerson()==null && o2.getPerson()==null)return 0;
            if(o1.getPerson()==null)return -1;
            if(o2.getPerson()==null)return 1;
            return Integer.compare(o1.getPersonWeight(),o2.getPersonWeight());
        }
    };
    public static Comparator<Ticket>coordinatesXComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return Float.compare(o1.getCoordinatesX(),o2.getCoordinatesX());
        }
    };
    public static Comparator<Ticket>coordinatesYComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return Long.compare(o1.getCoordinatesY(),o2.getCoordinatesY());
        }
    };
    public static Comparator<Ticket>locationXComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            if((o1.getPerson()==null || o1.getPersonLocation()==null)&&(o2.getPerson()==null || o2.getPersonLocation()==null ))return 0;
            if((o1.getPerson()==null || o1.getPersonLocation()==null))return -1;
            if((o2.getPerson()==null || o2.getPersonLocation()==null))return 1;
            return Double.compare(o1.getPersonLocationX(),o2.getPersonLocationX());
        }
    };
    public static Comparator<Ticket>locationYComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            if((o1.getPerson()==null || o1.getPersonLocation()==null)&&(o2.getPerson()==null || o2.getPersonLocation()==null ))return 0;
            if((o1.getPerson()==null || o1.getPersonLocation()==null))return -1;
            if((o2.getPerson()==null || o2.getPersonLocation()==null))return 1;
            return Long.compare(o1.getPersonLocationY(),o2.getPersonLocationY());
        }
    };
    public static Comparator<Ticket>locationZComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            if((o1.getPerson()==null || o1.getPersonLocation()==null)&&(o2.getPerson()==null || o2.getPersonLocation()==null ))return 0;
            if((o1.getPerson()==null || o1.getPersonLocation()==null))return -1;
            if((o2.getPerson()==null || o2.getPersonLocation()==null))return 1;
            return Long.compare(o1.getPersonLocationZ(),o2.getPersonLocationZ());
        }
    };
    public static Comparator<Ticket>locationNameComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket o1, Ticket o2) {
            if((o1.getPerson()==null || o1.getPersonLocation()==null)&&(o2.getPerson()==null || o2.getPersonLocation()==null ))return 0;
            if((o1.getPerson()==null || o1.getPersonLocation()==null))return -1;
            if((o2.getPerson()==null || o2.getPersonLocation()==null))return 1;
            return o1.getPersonLocationName().compareTo(o2.getPersonLocationName());
        }
    };
}
