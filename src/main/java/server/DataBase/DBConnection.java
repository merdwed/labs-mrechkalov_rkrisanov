package server.DataBase;

public class DBConnection {
    private String hostname = "127.0.0.1";
    private String DBname ="mydb";
    private String BDType = "postgresql";
    private Integer PORT = 5432;
    static private String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPORT(Integer PORT) {
        this.PORT = PORT;
    }

    public void setDBname(String DBname) {
        this.DBname = DBname;
    }

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }
    public void update(){
        DB_URL = "jdbc:"+BDType+"://"+hostname+":"+ PORT.toString()+"//"+DBname;
    }
}
