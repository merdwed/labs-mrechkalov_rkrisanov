package DataClasses.Exception;

public class TicketIsNotExistException extends Exception {
    @Override
    public String toString() {
        return "Ticket with the id isn't exist";
    }
}
