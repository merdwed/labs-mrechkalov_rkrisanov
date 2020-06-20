import DataClasses.JsonFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file;
        String fileName="NewCollection";
        if(args.length>0) {
            fileName=args[0];
            JsonFile.getJsonFile().setPathName(fileName);
            JsonFile.getJsonFile().readJSON();
        }
        else{
            JsonFile.getJsonFile().setPathName(fileName);
        }
        ShellUtils.ShellIO.initAndPushSource(null);//null is special value for console. usualy arg is path to file
        ShellUtils.ShellInterpretator.run();
    }
}