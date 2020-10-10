package DataClasses;

import java.util.*;
import java.util.stream.Collectors;

public class Locales {
    private Locales(){
        supportedlocales.add(new Locale("es","ec"));
        supportedlocales.add(new Locale("ru"));
        supportedlocales.add(new Locale("sg"));
        supportedlocales.add(new Locale("sr"));
    }
    private static Locales locales = new Locales();

    private HashSet<Locale> supportedlocales = new HashSet<Locale>();
    private ResourceBundle bundle = ResourceBundle.getBundle("DataClasses.locales.Message");

    public static Locales getInstance() {
        return locales;
    }

    public String getString(String message){
        return bundle.getString(message);
    }
    public boolean resetLocale(String request){
        String[] language = request.split("_");
        Locale locale=null;
        if (language.length>1)
            locale = new Locale(language[0],language[1]);
        else locale = new Locale(language[0]);

        for (Locale currlocale:
                supportedlocales) {
            if (currlocale.equals(locale)) {
                bundle = ResourceBundle.getBundle("DataClasses.locales.Message",currlocale);
            }
        }
        return (bundle.getLocale().equals(locale));
    }
    public void resetLocale(){
            bundle = ResourceBundle.getBundle("DataClasses.locales.Message",new Locale(""));
    }
    public List<String> getListOfLocales(){
        return new ArrayList<String> (supportedlocales.stream().map((t)->t.toString()).collect(Collectors.toList()));
    }
    public String getSupportedlocales() {
        String supportedlanguage="";
        for (Locale locale:
                supportedlocales) {
            supportedlanguage+=locale.toString();
            supportedlanguage+=" - "+locale.getDisplayName(locale)+"\n";
        }
        return supportedlanguage;
    }
}
