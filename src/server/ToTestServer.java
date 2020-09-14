package server.ServerNet;

import DataClasses.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class App {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
        CommandType commandType = CommandType.ADD;
        Location location = new Location(12d,23,34,"HYE");
        Coordinates coordinates = new Coordinates(1,2);
        Person person = new Person(12,32,location);
        Ticket ticket = new Ticket(TicketType.VIP,98.2,"Justion",coordinates,person);
        CommandType commandType2 = CommandType.INFO;


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(commandType);
        objectOutputStream.writeObject(ticket);
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        //objectOutputStream.flush();
        //byte[] bytes = byteArrayOutputStream.toByteArray();
        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
//        for (byte i:
//             byteArrayOutputStream.toByteArray()) {
//            System.out.println(i);
//        }


        DatagramChannel client = DatagramChannel.open();

        client.bind(null);
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8989);

        client.send(byteBuffer, serverAddress);

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(commandType2);
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        client.send(byteBuffer, serverAddress);

        byteBuffer.clear();
        client.receive(byteBuffer);
        //byteBuffer.flip();
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        String dataset;
        try {
            dataset = (String)objectInputStream.readObject();
            System.out.println(dataset);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
            System.out.println("Hyu tebe, a ne collection");
        }

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(CommandType.EXIT);
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        client.send(byteBuffer, serverAddress);
    }
}
