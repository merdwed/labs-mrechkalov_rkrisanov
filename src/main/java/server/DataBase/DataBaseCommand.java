package server.DataBase;

import DataClasses.Ticket;
import server.Dataset;
import server.ServerNet.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCommand {
    private static Statement statement=null;
    private static ResultSet resultSet=null;
    public static Long getCurrId()throws SQLException{
        resultSet = statement.executeQuery("SELECT currval('bigserial')");
        resultSet.next();
        return resultSet.getLong(1);
    }
    public static boolean AccessToTicket(Long id,String username) throws SQLException {
        String request = "SELECT creater FROM ticket WHERE id="+id+";";
        resultSet=statement.executeQuery(request);
        resultSet.next();
        return resultSet.getString(1).equals(username);
    }
    public static boolean TicketIsExist(Long id) throws SQLException {
        String request = "SELECT creater FROM ticket WHERE id="+id+";";
        return statement.execute(request);
    }
    public static void RemoveTicket(Long id) throws SQLException {
        String request = "DELETE FROM ticket WHERE id="+id+";";
        statement.execute(request);
    }
    public static void AddTicket(Ticket ticket) throws SQLException {
        String request = "INSERT INTO Ticket (id,name,coordinates,type,price,person,creater)" +
                "VALUES(nextval('bigserial')" +
                ",'"+ticket.getName()+"'" +
                ",'("+ticket.getCoordinatesX()+","+ticket.getCoordinatesY()+")'" +
                ",'"+ticket.getType()+"'" +
                ","+ticket.getPrice() +
                ",(" +
                ticket.getPersonHeight() +
                "," +ticket.getPersonWeight()+
                ",(" + ticket.getPersonLocationX()+
                "," + ticket.getPersonLocationY()+
                "," +ticket.getPersonLocationZ()+
                ",'" +ticket.getPersonLocationName()+"')),"+
                "'"+Request.getInstance().getAccount().getLogin()+"');";
        statement.execute(request);
    }
    public static void UpdateTicket(Long id,Ticket ticket) throws SQLException {
        RemoveTicket(id);
        String request = "INSERT INTO Ticket (id,name,coordinates,type,price,person,creater)" +
                "VALUES("+id +
                ",'"+ticket.getName()+"'" +
                ",'("+ticket.getCoordinatesX()+","+ticket.getCoordinatesY()+")'" +
                ",'"+ticket.getType()+"'" +
                ","+ticket.getPrice() +
                ",(" +
                ticket.getPersonHeight() +
                "," +ticket.getPersonWeight()+
                ",(" + ticket.getPersonLocationX()+
                "," + ticket.getPersonLocationY()+
                "," +ticket.getPersonLocationZ()+
                ",'" +ticket.getPersonLocationName()+"')),"+
                "'"+Request.getInstance().getAccount().getLogin()+"');";
        statement.execute(request);
    }
    public static void getTicketCollection() throws SQLException {
        resultSet = statement.executeQuery("SELECT name,id,price,(coordinates).*,(person).location.*,(person).height,(person).weight,type,creater FROM ticket;");
        Ticket ticket;
        while (resultSet.next())
        {
            ticket = new Ticket();
            ticket.setName(resultSet.getString(1));
            ticket.setId(resultSet.getLong(2));
            ticket.setPrice(resultSet.getDouble(3));
            ticket.setCoordinatesX(resultSet.getFloat(4));
            ticket.setCoordinatesY(resultSet.getLong(5));
            ticket.setPersonLocationX(resultSet.getDouble(6));
            ticket.setPersonLocationY(resultSet.getLong(7));
            ticket.setPersonLocationZ(resultSet.getLong(8));
            ticket.setPersonLocationName(resultSet.getString(9));
            ticket.setPersonHeight(resultSet.getDouble(10));
            ticket.setPersonWeight(resultSet.getInt(11));
            ticket.setType(resultSet.getString(12));
            ticket.setCreator(resultSet.getString(13));
            Dataset.getCurrentInstance().add(ticket);
        }
    }
    public static void CreateNewAccount(String username,String password) throws SQLException {
        String request = "INSERT INTO users VALUES ('"+username+"','"+password+"');";
        statement.execute(request);
    }

    public static void setStatement(Statement statement) {
        DataBaseCommand.statement = statement;
    }
}
