package server;

import DataClasses.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class ToTestServer {

    public static void main(String[] args) throws IOException, InterruptedException {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
//        CommandType commandType = CommandType.ADD;
        Location location = new Location(12d,23,34,"HYE");
        Coordinates coordinates = new Coordinates(1,2);
        Person person = new Person(12,32,location);
        Ticket ticket = new Ticket(TicketType.VIP,98.2,"Justion",coordinates,person);
        DatagramChannel chan = DatagramChannel.open();
        chan.configureBlocking( false );
        chan.bind( new InetSocketAddress( 7777 ) );

        ByteBuffer buffer = ByteBuffer.allocate( 4*1024 );
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String string="wds";
        while (true) {

            buffer.clear();
            System.out.println("trying non-blocking receive...");
            SocketAddress from = chan.receive(buffer);
            System.out.println("non-blocking receive done.");

            if (from != null) {
                buffer.flip();
                System.out.printf("<<<--- got [%x] byte from %s%n", buffer.get(), from);
            }
            if (reader.ready()){
                if (reader.readLine().equals("exit"))
                    break;
            }


            System.out.println( "sleeping..." );
            TimeUnit.SECONDS.sleep( 5 );
        }

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


//        Scanner in = new Scanner(System.in);
//        CommandType commandType=null;
//        do {
//            PackageOut.getInstance().remake();
//            try {
//                commandType = CommandType.valueOf(in.nextLine());
//                switch (commandType) {
//                    case ADD: {
//                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
//                        PackageOut.getInstance().getObjectOutputStream().writeObject(ticket);
//                        Answer.send();
//                        break;
//                    }
//                    case SHOW: {
//                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
//                        Answer.send();
//                        Request request = new Request();
//                        try {
//                            ArrayList hyy = (ArrayList) PackageIn.getInstance().getObjectInputStream().readObject();
//                            System.out.println(hyy.toString());
//                        } catch (ClassNotFoundException classNotFoundException) {
//                            classNotFoundException.printStackTrace();
//                        }
//                        break;
//                    }
//                    case INFO: {
//                        PackageOut.getInstance().getObjectOutputStream().writeObject(commandType);
//                        Answer.send();
//                        Request request = new Request();
//                        try {
//                            String hyy = (String) PackageIn.getInstance().getObjectInputStream().readObject();
//                            System.out.println(hyy);
//                        } catch (ClassNotFoundException classNotFoundException) {
//                            classNotFoundException.printStackTrace();
//                        }
//                        break;
//                    }
//                    default: {
//                        System.out.println("Hyy na");
//                        break;
//                    }
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Hye tobye, a ne wrong commandType");
//            }
//        } while (commandType != CommandType.EXIT);
    }
}
