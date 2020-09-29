package DataClasses.Exception;

public class IdLessZeroException extends Exception {
    @Override
    public String toString() {
        return "Id ticket less zero";
    }
}
