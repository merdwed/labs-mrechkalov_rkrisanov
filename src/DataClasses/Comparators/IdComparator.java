package DataClasses.Comparators;

import java.util.Comparator;

import DataClasses.Ticket;

public class IdComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2){
        return Long.compare(t1.getId(),t2.getId());
    }
}