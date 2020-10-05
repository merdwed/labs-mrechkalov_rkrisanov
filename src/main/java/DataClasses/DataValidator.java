package DataClasses;

import DataClasses.Exception.WrongIdException;
import DataClasses.Exception.WrongNameException;

public class DataValidator {
    public static String validator(String name) throws WrongNameException {
        if (name == null ||name.equals("")) {
            throw new WrongNameException();
        } else
            return name;
    }
    public static Long validator(Long id) throws WrongIdException {
        if (id == null ||id<=0) {
            throw new WrongIdException();
        } else
            return id;
    }
}
