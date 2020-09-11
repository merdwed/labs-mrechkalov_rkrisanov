package server;
import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        String fileName="NewCollection";
        if(args.length>0) {
            fileName=args[0];
            JsonFile.getJsonFile().setPathName(fileName);
            JsonFile.getJsonFile().readJSON();
        }
        else{
            JsonFile.getJsonFile().setPathName(fileName);
        }
    }
}