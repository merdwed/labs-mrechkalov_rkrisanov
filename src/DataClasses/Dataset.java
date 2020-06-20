package DataClasses;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

/**
 * @author Drukharion
 * @see DataClasses.CollectionInterface
 */
public class Dataset implements CollectionInterface {
    private static Dataset currentInstance=new Dataset();

    public static Dataset getCurrentInstance() {
        return currentInstance;
    }

    private java.util.HashSet<Ticket> collection = new java.util.HashSet<Ticket>();

    /**
     * @param element be added to collection
     */
    public void add(Ticket element) {
        if(element==null)return;
        if(element.getId()==null || element.getId()==0) {
            ArrayList<Ticket> temp = getSortedArrayList(idComparator);
            Long index = new Long(1);
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

    /**
     * @param comp comparator for sorting
     * @return temp - sorted collection list
     */
    public ArrayList<Ticket> getSortedArrayList(Comparator<Ticket> comp) {

        ArrayList<Ticket> temp=new ArrayList<Ticket>(collection);
        Collections.sort(temp,comp);
        return temp;
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

    /**
     * @see java.util.Comparator
     */
    private static class IdComparator implements Comparator<Ticket>{

        public int compare(Ticket t1, Ticket t2){
            return Long.compare(t1.getId(),t2.getId());
        }
    }
    private static class PriceComparator implements Comparator<Ticket>{
        @Override
        public int compare(Ticket t1, Ticket t2){
            return Double.compare(t1.getPrice(),t2.getPrice());
        }
    }

    private static class TicketTypeComparator implements Comparator<Ticket>{
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getType().compareTo(o2.getType());
        }
    }
}
