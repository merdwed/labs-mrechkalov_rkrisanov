package DataClasses.locales;

import java.util.ListResourceBundle;

public class Message_es_EC extends ListResourceBundle {
    private final Object[][]   contents =
            {
                    {"Wrong user", "Usuario incorrecto. No hay acceso"},
                    {"Wrong user\nAccount isn't exist", "Usuario incorrecto \nLa cuenta no existe"},
                    {"Ticket added successful", "Ticket agregado exitosamente"},
                    {"Ticket deleted successful", "Ticket eliminado correctamente"},
                    {"Ticket updated successful", "Ticket actualizado correctamente"},
                    {"Ticket didn't add", "El ticket no se agregó"},
                    {"User's tickets is cleaned up", "Los tickets del usuario se limpian"},
                    {"Every ticket from the collection less the ticket is removed", "Se quita cada boleto de la colección menos el boleto"},
                    {"Account created successful", "Cuenta creada correctamente"},
                    {"Account deleted successful", "Cuenta eliminada correctamente"},
                    {"Collection sorted by price", "Colección ordenada por precio"},
                    {"Collection sorted less by ticketType", "Colección ordenada menos por ticketType"},
                    {"Collection sorted by name", "Colección ordenada por nombre"},
                    {"Ticket not found", "Ticket not found"},
                    {"Collection:", "Colección:"},
            };

    public Object[][] getContents() {return contents; }
}
