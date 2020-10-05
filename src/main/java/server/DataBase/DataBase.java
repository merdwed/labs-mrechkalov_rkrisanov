package server.DataBase;

import DataClasses.Ticket;
import DataClasses.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class DataBase {
    private static DataBase dataBase = new DataBase();
    public static DataBase getInstance() {
        return dataBase;
    }
    static private String DB_URL= "jdbc:postgresql://127.0.0.1:5432/mydb";
    static private Account superAccount=new Account("postgres","142857");
    static private Connection connection=null;
    static private Statement statement=null;
    public void Connect(){
        try {
            connection = DriverManager.getConnection(DB_URL, superAccount.getLogin(), superAccount.getPassword());
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    public boolean checkAccount(Account account) throws SQLException{
            statement = connection.createStatement();
            ResultSet rs;
            rs= statement.executeQuery("SELECT username FROM users WHERE username = '"
                    +account.getLogin()+"'AND password = '"+ DigestUtils.md5Hex(account.getPassword())+"';");
            String name=null;
            while (rs.next()) {
                name = rs.getString(1);
            }
            if (name==null) return false;
            return (name.equals(account.getLogin()));
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }
    public void setSuperAccount(String username,String password){
        superAccount=new Account(username,password);
    }
}
