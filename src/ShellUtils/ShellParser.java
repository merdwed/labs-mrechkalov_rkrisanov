package ShellUtils;
import DataClasses.TicketType;

/**
 * @author
 * full static class for shell-parsing input String
 */
public class ShellParser {
    public static  Long parseLong(String inputSting) {
        try {
            return Long.valueOf(inputSting.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            System.out.println("'" + inputSting + "'" + " - wrong number format. Value must be integer. Try again or leave empty to end.");
        }
        return null;
    }

    public static Integer parseInteger(String inputSting) {
        try {
            return Integer.valueOf(inputSting.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            System.out.println("'" + inputSting + "'" + " - wrong number format. Value must be integer. Try again or leave empty to end.");
        }
        return null;
    }

    public static Double parseDouble(String inputSting) {
        try {
            return Double.valueOf(inputSting.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            System.out.println("'" + inputSting + "'" + " - wrong number format. Value can be real. Try again or leave empty to end command.");
        }

        return null;
    }

    public static Float parseFloat(String inputSting) {
            try {
                return Float.valueOf(inputSting.split(" ", 2)[0]);
            } catch (NumberFormatException e) {
                System.out.println("'" + inputSting + "'" + " - wrong number format. Value can be real. Try again or leave empty to end command.");
            }
        return null;
    }

    public static TicketType parseTicketType(String inputSting) {
            try {
                return TicketType.valueOf(inputSting.split(" ", 2)[0].toUpperCase());
            }
            catch (IllegalArgumentException e) {
                System.out.println("'" + inputSting + "'" + " - wrong ticket type. Try again or leave empty to end command.");
            }

        return null;
    }

    public static String parseString(String inputString){

        return inputString;
    }
}
