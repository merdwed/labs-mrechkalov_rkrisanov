package DataClasses.Comparators;

import java.util.Comparator;

import DataClasses.Ticket;

public class TicketTypeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if(o1.getType()==null && o2.getType()==null)return 0;
        if(o1.getType()==null)return -1;
        if(o2.getType()==null)return 1;
        return o1.getType().compareTo(o2.getType());
    }
}