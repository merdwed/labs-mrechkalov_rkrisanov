package DataClasses.locales;

import java.util.ListResourceBundle;

public class Message extends ListResourceBundle {
    private final Object[][]   contents =
            {
                    {"Wrong user", "Wrong user. There isn't access"},
                    {"Wrong user\nAccount isn't exist", "Wrong user\nAccount isn't exist"},
                    {"Ticket added successful", "Ticket added successful"},
                    {"Ticket deleted successful", "Ticket deleted successful"},
                    {"Ticket updated successful", "Ticket updated successful"},
                    {"Ticket didn't add", "Ticket didn't add"},
                    {"User's tickets is cleaned up", "User's tickets is cleaned up"},
                    {"Every ticket from the collection less the ticket is removed", "Every ticket from the collection less the ticket is removed"},
                    {"Account created successful", "Account created successful"},
                    {"Account deleted successful", "Account deleted successful"},
                    {"Collection sorted by price", "Collection sorted by price"},
                    {"Collection sorted less by ticketType", "Collection sorted less by ticketType"},
                    {"Collection sorted by name", "Collection sorted by name"},
                    {"Ticket not found", "Ticket not found"},
                    {"Collection:", "Collection:"},
            };

    public Object[][] getContents() {return contents; }
}
