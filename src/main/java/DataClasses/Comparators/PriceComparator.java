package DataClasses.Comparators;

import java.util.Comparator;

import DataClasses.Ticket;

public class PriceComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2){
        return Double.compare(t1.getPrice(),t2.getPrice());
    }
}