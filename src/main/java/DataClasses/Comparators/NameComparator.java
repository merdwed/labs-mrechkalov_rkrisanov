package DataClasses.Comparators;

import DataClasses.Ticket;

import java.util.Comparator;

public class NameComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
