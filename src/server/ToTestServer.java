package server;

import DataClasses.*;
import DataClasses.CommandTypeUtils.CommandType;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Scanner;

import client.ClientNet.*;


public class ToTestServer {

    public static void main(String[] args) throws IOException {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
//        CommandType commandType = CommandType.ADD;
        Location location = new Location(12d,23,34,"HYE");
        Coordinates coordinates = new Coordinates(1,2);
        Person person = new Person(12,32,location);
        Ticket ticket = new Ticket(TicketType.VIP,98.2,"Justion",coordinates,person);
//
//        //Формирование команды ADD
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(commandType);
//        objectOutputStream.writeObject(ticket);
//        objectOutputStream.flush();
//        byteArrayOutputStream.flush();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
//        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
//
//
//        DatagramChannel client = DatagramChannel.open();
//
//        client.bind(null);
//        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8989);
//        //Отправка ADD
//        client.send(byteBuffer.duplicate(), serverAddress);
//        client.send(byteBuffer.duplicate(), serverAddress);
//        client.send(byteBuffer.duplicate(), serverAddress);
//        //Формирование команды INFO
//        CommandType commandType2 = CommandType.SHOW;
//        byteArrayOutputStream = new ByteArrayOutputStream();
//        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(commandType2);
//        objectOutputStream.flush();
//        byteArrayOutputStream.flush();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
//        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
//        client.send(byteBuffer, serverAddress);
//
//        byteBuffer= ByteBuffer.allocate(4096);
//        client.receive(byteBuffer);
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.capacity());
//        ByteArrayInputStream byteArrayInputStream;
//        ObjectInputStream objectInputStream;
//        byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
//        objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        try {
//            ArrayList size=(ArrayList)objectInputStream.readObject();
//            System.out.println(size.toString());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (EOFException e){
//            System.out.println("Hyu tebe, a ne collection");
//        }
//        //Команда на выключение сервере
//        byteArrayOutputStream = new ByteArrayOutputStream();
//        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(CommandType.EXIT);
//        objectOutputStream.flush();
//        byteArrayOutputStream.flush();
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
//        byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
//        client.send(byteBuffer, serverAddress);


        Scanner in = new Scanner(System.in);
        CommandType commandType=null;
        do {
            PackageOut.getInstance().remake();
            try {
                commandType = CommandType.valueOf(in.nextLine());
                switch (commandType) {
                    case ADD: {
                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
                        PackageOut.getInstance().getObjectOutputStream().writeObject(ticket);
                        Answer.send();
                        break;
                    }
                    case SHOW: {
                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
                        Answer.send();
                        Request request = new Request();
                        try {
                            ArrayList hyy = (ArrayList) PackageIn.getInstance().getObjectInputStream().readObject();
                            System.out.println(hyy.toString());
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        break;
                    }
                    case INFO: {
                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
                        Answer.send();
                        Request request = new Request();
                        try {
                            String hyy = (String) PackageIn.getInstance().getObjectInputStream().readObject();
                            System.out.println(hyy);
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        break;
                    }
                    case EXIT: {
                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
                        Answer.send();
                        break;
                    }
                    default: {
                        System.out.println("Hyy na");
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Hye tobye, a ne wrong commandType");
            }
        } while (commandType != CommandType.EXIT);
    }
}
