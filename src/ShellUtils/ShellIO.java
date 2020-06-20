package ShellUtils;

import DataClasses.Ticket;
import DataClasses.TicketType;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author merdwed
 * full static class for reading values from current source of commands
 */
public class ShellIO {
    /**
     * show the type of current source of commands
     */
    private enum Mode{CONSOLE,FILE}

    /**
     * current type of source
     * @see Mode
     */
    private static Mode mode=Mode.CONSOLE;
    /**
     * stack of sources. last is current
     * @see ShellIO#getSource()
     */
    private static Stack<Scanner>  sources=new Stack<Scanner>();

    /**
     *
     * @return current source of commands
     * @throws NoSourceException
     */
    private static Scanner getSource() throws NoSourceException{
        if(sources.empty()==true)
            throw new NoSourceException("program doesn't have a source of commands");
        return sources.peek();
    }
    /**
     * prints string if type of current source is CONSOLE
     * @see ShellIO#mode
     */
    public static void printMessageIfConsole(String str){
        if(mode==Mode.CONSOLE)
            System.out.print(str);
    }
    public static void initAndPushSource(String filename) {
        InputStream source;
        if(filename == null){
            source=System.in;
            mode= Mode.CONSOLE;
        }
        else{
            try {
                source = new FileInputStream(filename);
                mode = Mode.FILE;
            }catch (FileNotFoundException e){
                System.out.println("file " + filename + " not found");
                return;
            }
        }
        BufferedInputStream buffSource=new BufferedInputStream(source);
        sources.push(new Scanner(buffSource));
    }
    public static void popSource(){
        sources.pop();
        if(sources.size() == 1)
            mode = Mode.CONSOLE;
    }
    public static Long readLong(String message) throws NoSourceException{
        String tempStr;
        Long tempLong;
        printMessageIfConsole(message);
        while (getSource().hasNextLine()) {
            tempStr=getSource().nextLine();
            if(tempStr.isEmpty())
                return null;
            tempLong=ShellParser.parseLong(tempStr);
            if(tempLong!=null)
                return tempLong;
            printMessageIfConsole(message);
        }
        popSource();
        return null;
    }
    public static Integer readInteger(String message)throws NoSourceException{
        String tempStr;
        Integer tempInteger;
        printMessageIfConsole(message);
        while (getSource().hasNextLine()) {

            tempStr=getSource().nextLine();
            if(tempStr.isEmpty())
                return null;
            tempInteger=ShellParser.parseInteger(tempStr);
            if(tempInteger!=null)
                return tempInteger;
            printMessageIfConsole(message);
        }
        popSource();
        return null;
    }
    public static Double readDouble(String message)throws NoSourceException{
        String tempStr;
        Double tempDouble;
        printMessageIfConsole(message);
        while (getSource().hasNextLine()) {
            tempStr=getSource().nextLine();
            if(tempStr.isEmpty())
                return null;
            tempDouble=ShellParser.parseDouble(tempStr);
            if(tempDouble!=null)
                return tempDouble;
            printMessageIfConsole(message);
        }
        popSource();
        return null;
    }
    public static Float readFloat(String message)throws NoSourceException {
        String tempStr;
        Float tempFloat;
        printMessageIfConsole(message);
        while (getSource().hasNextLine()) {
            tempStr=getSource().nextLine();
            if(tempStr.isEmpty())
                return null;
            tempFloat=ShellParser.parseFloat(tempStr);
            if(tempFloat!=null)
                return tempFloat;
            printMessageIfConsole(message);
        }
        popSource();
        return null;
    }
    public static TicketType readTicketType(String message)throws NoSourceException {
        String tempStr;
        TicketType tempType;
        printMessageIfConsole("type of ticket can be:");
        for (TicketType tp : TicketType.values())
            printMessageIfConsole(tp.name() + "  ");
        printMessageIfConsole("\n");
        printMessageIfConsole(message);
        while (getSource().hasNextLine()) {
            tempStr=getSource().nextLine();
            if (tempStr.isEmpty())
                return null;
            tempType=ShellParser.parseTicketType(tempStr);
            if(tempType!=null)
                return tempType;


            printMessageIfConsole("type of ticket can be:");
            for (TicketType tp : TicketType.values())
                printMessageIfConsole(tp.name() + "  ");
            printMessageIfConsole("\n");
            printMessageIfConsole(message);
        }
        popSource();
        return null;
    }
    public static String readString(String message)throws NoSourceException{
        printMessageIfConsole(message);
        if(getSource().hasNextLine())
            return ShellParser.parseString(getSource().nextLine());
        else
            popSource();
        return "";
    }
    public static Ticket readTicket()throws NoSourceException {
        try {//Дикий костыль, только для finnaly
            Ticket tempTicket = new Ticket();
            Long tempLong = null;
            Integer tempInteger = null;
            Double tempDouble = null;
            Float tempFloat = null;
            String tempString = null;
            TicketType tempTicketType = null;

            printMessageIfConsole("fill in the data for the object\nif you leave fields empty, command doesn't execute. There is a fields that you can leave empty, it will be write.\n");
            do {
                tempString = readString("  enter the name:");
                if (tempString == null || tempString.equals("")) return null;
            } while (tempString == null);
            tempTicket.setName(tempString);

            printMessageIfConsole("  fill in the coordinates\n");
            tempFloat = readFloat("    enter the real number x:");
            if (tempFloat == null) return null;
            tempTicket.setCoordinatesX(tempFloat);

            tempLong = readLong("    enter the integer number y:");
            if (tempLong == null) return null;
            tempTicket.setCoordinatesY(tempLong);

            do {
                tempDouble = readDouble("  enter the real number of price:");
                if (tempDouble == null) return null;
                if (tempDouble <= 0)
                    printMessageIfConsole("  price must be more then 0. Try again or leave empty to end command.\n");
            } while (tempDouble <= 0);
            tempTicket.setPrice(tempDouble);


            tempTicket.setType(readTicketType("  enter the type of ticket or leave empty:"));

            printMessageIfConsole("  fill in the data of person. If you don't want do it, leave first field empty\n");
            do {
                tempDouble = readDouble("    enter the real number of height:");
                if (tempDouble == null) {
                    tempTicket.setPerson(null);
                    return tempTicket;
                }
                if (tempDouble <= 0)
                    printMessageIfConsole("    height must be more then 0. Try again or leave empty.\n");
            } while (tempDouble <= 0);
            tempTicket.setPersonHeight(tempDouble);

            do {
                tempInteger = readInteger("    enter the integer number of weight:");
                if (tempInteger == null) break;
                if (tempInteger <= 0)
                    printMessageIfConsole("    weight must be more then 0. Try again or leave empty to end command.\n");
            } while (tempInteger <= 0);
            tempTicket.setPersonWeight(tempInteger);

            printMessageIfConsole("    fill in the data of location of person. If you don't want do it, leave first field empty\n");
            tempDouble = readDouble("      enter the real number of X:");
            if (tempDouble == null) {
                tempTicket.setPersonLocation(null);
                return tempTicket;
            }
            tempTicket.setPersonLocationX(tempDouble);

            tempLong = readLong("      enter the integer number of Y:");
            if (tempLong == null) return null;
            tempTicket.setPersonLocationY(tempLong);

            tempLong = readLong("      enter the integer number of Z:");
            if (tempLong == null) return null;
            tempTicket.setPersonLocationZ(tempLong);
            do {
                tempString = readString("       enter the location name:");
                if (tempString == null || tempString.equals("")) return null;
            } while (tempString.length() > 852);
            tempTicket.setPersonLocationName(tempString);
            return tempTicket;
        }
        finally{
            printMessageIfConsole("end of reading ticket\n");//Дикий костыль, мне было лень вставлять эту строку перед каждым return
        }
    }
}
