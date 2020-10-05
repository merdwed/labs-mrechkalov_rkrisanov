package server.DataBase;

import DataClasses.Ticket;
import DataClasses.Account;
import java.sql.*;

public class DataBase {
    private static DataBase dataBase = new DataBase();
    public static DataBase getInstance() {
        return dataBase;
    }
    //static private DBConnection dBConnection = new DBConnection();= "jdbc:postgresql://127.0.0.1:5432/mydb";
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
                    +account.getLogin()+"'AND password = '"+account.getPassword()+"';");
            String name=null;
            while (rs.next()) {
                name = rs.getString(1);
            }
            if (name==null) return false;
            return (name.equals(account.getLogin()));
    }

    public static void main(String[] argv) throws SQLException {
        try {
            connection = DriverManager.getConnection(DB_URL, superAccount.getLogin(), superAccount.getPassword());
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            System.out.println(DataBase.getInstance().checkAccount(new Account("a","0")));
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static Statement getStatement() {
        return statement;
    }
}
