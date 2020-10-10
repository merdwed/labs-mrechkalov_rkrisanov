package DataClasses.locales;

import java.util.ListResourceBundle;

public class Message_sg extends ListResourceBundle {
    private final Object[][]   contents =
            {
                    {"Wrong user", "Përdorues i pavlefshëm. Nuk ka qasje"},
                    {"Wrong user\nAccount isn't exist", "Përdorues i pavlefshëm\n" +
                            "Llogaria nuk ekziston"},
                    {"Ticket added successful", "Bileta u shtua me sukses"},
                    {"Ticket deleted successful", "Bileta u fshi me sukses"},
                    {"Ticket updated successful", "Bileta u azhurnua me sukses"},
                    {"Ticket didn't add", "Asnjë biletë nuk është shtuar}"},
                    {"User's tickets is cleaned up", "Biletat e përdoruesit u fshinë me sukses"},
                    {"Every ticket from the collection less the ticket is removed", "Çdo biletë më e vogël se ajo aktuale fshihet"},
                    {"Account created successful", "Llogaria u krijua me sukses"},
                    {"Account deleted successful", "Llogaria u fshi me sukses"},
                    {"Collection sorted by price", "Koleksioni i renditur sipas çmimit"},
                    {"Collection sorted less by ticketType", "Koleksioni i renditur sipas llojit të biletës"},
                    {"Collection sorted by name", "Koleksioni i renditur sipas emrit"},
                    {"Ticket not found", "Nuk u gjet asnjë biletë"},
                    {"Collection:", "Mbledhja:"},
            };

    public Object[][] getContents() {return contents; }
}
