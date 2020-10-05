package server.DataBase;

public class DataBaseConnection {
    private String hostname = "127.0.0.1";
    private String DatBaseName ="mydb";
    private String DatBaseType = "postgresql";
    private Integer PORT = 5432;
    static private String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setDataBasePORT(Integer PORT) {
        this.PORT = PORT;
    }

    public void setDataBaseName(String DBname) {
        this.DatBaseName = DBname;
    }

    public void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }
    public void update(){
        DB_URL = "jdbc:"+DatBaseType+"://"+hostname+":"+ PORT.toString()+"/"+DatBaseName;
    }

    public String getDbUrl() {
        return DB_URL;
    }
}
