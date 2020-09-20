package DataClasses.Exception;

public class WrongTicketTypeException extends Exception {
    @Override
    public String toString() {
        return "Your Ticket Type is absolutely bullshit";
    }
}
