package server.ServerNet;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashSet;

public class ActiveUsers {
    ActiveUsers(){

    }
    private static ActiveUsers actionUses = new ActiveUsers();

    public static ActiveUsers getInstance() {
        return actionUses;
    }
    private boolean collectionChanged =false;

    private HashSet<SocketAddress> users = new HashSet<SocketAddress>();
    public void add(SocketAddress socketAddress){
        users.add(socketAddress);
    }
    public void CollectionChanged(){
        collectionChanged=true;
    }
    public void send() throws IOException {
        if (collectionChanged) {
            for (SocketAddress socketAddress :
                    users) {
                Answer answer = new Answer();
                answer.setToCurrans("execute show");
                answer.prepare();
                answer.send(socketAddress);
            }
            collectionChanged = false;
        }
    }
}
