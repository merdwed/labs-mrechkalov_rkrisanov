package client;
import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        client.ShellUtils.ShellIO.initAndPushSource(null);//null is special value for console. usualy arg is path to file
        client.ShellUtils.ShellInterpretator.run();
    }
}