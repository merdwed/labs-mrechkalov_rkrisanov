package server.Commands;

import java.io.IOException;

public abstract class Command {
    public static void execute() throws IOException {}
    public static String getDocumentation(){
        return null;
    }
}
