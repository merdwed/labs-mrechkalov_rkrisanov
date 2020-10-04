package DataClasses;

import java.io.Serializable;

public class Account implements Serializable{

    public Account(String usr, String pass) {
        this.user=usr;
        this.password=pass;
    }
    private String user;
    private String password;
    public String getLogin(){
        return user;
    }
    public String getPassword(){
        return password;
    }
}
