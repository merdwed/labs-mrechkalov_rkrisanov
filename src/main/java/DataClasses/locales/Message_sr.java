package DataClasses.locales;

import java.util.ListResourceBundle;

public class Message_sr extends ListResourceBundle {
    private final Object[][]   contents =
            {
                    {"Wrong user", "Погрешан корисник. Нема приступа"},
                    {"Wrong user\nAccount isn't exist", "Погрешан корисник \nРачун не постоји"},
                    {"Ticket added successful", "Улазница је додата успешно"},
                    {"Ticket deleted successful", "Улазница је избрисана"},
                    {"Ticket updated successful", "Улазница је ажурирана успешно"},
                    {"Ticket didn't add", "Улазница није додата"},
                    {"User's tickets is cleaned up", "Корисничке карте су очишћене"},
                    {"Every ticket from the collection less the ticket is removed", "Свака карта из колекције умањена за карту се уклања"},
                    {"Account created successful", "Налог је успешно направљен"},
                    {"Account deleted successful", "Налог је избрисан"},
                    {"Collection sorted by price", "Колекција сортирана по цени"},
                    {"Collection sorted less by ticketType", "Колекција је сортирана мање према типу типа"},
                    {"Collection sorted by name", "Колекција је сортирана по имену"},
                    {"Ticket not found", "Улазница није пронађена"},
                    {"Collection:", "Колекција:"},
            };

    public Object[][] getContents() {return contents; }
}