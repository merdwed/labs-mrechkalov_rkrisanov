import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            ServerMain.main(args);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ClientMain.main(args);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
