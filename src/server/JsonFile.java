package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DataClasses.Ticket;


/**
 * Command works with Json-file contain colletion
 * @author Drukharion
 */
public class JsonFile {
    /**
     * constraction without param
     */
    public JsonFile(){
    }
    private static JsonFile jsonfile=new JsonFile();
    public static JsonFile getJsonFile() {
        return jsonfile;
    }
    private String pathname=null;

    /**
     * set current pathname collection
     * @param pathname
     */
    public void setPathName(String pathname){
        this.pathname=pathname;
    }

    /**
     * @return pathname current file
     */
    public String getPathName() {
        return pathname;
    }
    /**
     * method read current collection from file
     * @throws FileNotFoundException
     * @see GsonBuilder
     * @see FileInputStream
     * @see BufferedInputStream
     * @see Scanner
     * @see Dataset
     */
    public void readJSON() throws FileNotFoundException {
        Dataset dataset=Dataset.getCurrentInstance();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        File file = new File(pathname);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 100);
        Scanner scanner = new Scanner(bufferedInputStream);
        String string = "";
        while (scanner.hasNextLine())
            string += scanner.nextLine();
        Ticket[] tickets = gson.fromJson(string, Ticket[].class);
        scanner.close();

        for (Ticket ticket : tickets) {
            dataset.add(ticket);
        }
        return;

    }

    /**
     * method save current collection to file
     * @throws IOException
     * @see GsonBuilder
     * @see FileOutputStream
     * @see BufferedOutputStream
     * @see DataOutputStream
     * @see Dataset
     * @see Commands.SaveCommand
     */
    public void writeJSON() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileOutputStream fileOutputStream  = new FileOutputStream (pathname);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream,100);
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeBytes(gson.toJson(Dataset.getCurrentInstance().getSortedArrayList(Dataset.idComparator)));
        dataOutputStream.close();
    }
}
