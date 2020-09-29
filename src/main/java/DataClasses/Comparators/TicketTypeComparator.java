package DataClasses.Comparators;

import java.util.Comparator;

import DataClasses.Ticket;

public class TicketTypeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getType().compareTo(o2.getType());
    }
}