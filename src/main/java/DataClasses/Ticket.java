package DataClasses;

import java.io.Serializable;

/**
 * @author Drukharion
 */
public class Ticket implements Serializable {
    public Ticket(TicketType type,Double price, String name,Coordinates coordinates,Person person){
        this.type = type;
        this.price=price;
        this.name = name;
        this.coordinates = coordinates;
        this.person = person;
    }

    /**
     * constractioni without param
     */
    public Ticket(){
    }
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person=new Person();// = new Person(); //Поле может быть null
    public void setPerson(Person p){
        person=p;
    }
    public void setPersonLocation(Location l){
        this.person.setLocation(l);
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * set id
     * @param id - identificator
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @return id - identificator
     */
    public Long getId(){
        return id;
    }
    /**
     * set creationDate
     * @param creationDate - date when ticket was created
     */
    public void setCreationDate(java.time.LocalDate creationDate){
        this.creationDate = creationDate;
    }

    /**
     * @return creationData - date when ticket was created
     */
    public java.time.LocalDate getCreationDate(){
        return creationDate;
    }

    /**
     * set type Ticket as string
     * @param type type Ticket
     */
    public void setType(String type){
        this.type=TicketType.valueOf(type);
    }

    /**
     * set type Ticket
     * @param type type Ticket
     */
    public void setType(TicketType type){
        this.type=type;
    }

    /**
     * @return type
     */
    public TicketType getType(){
        return type;
    }

    /**
     * set price of Ticket
     * @param price price of Ticket
     */
    public void setPrice(Double price){
        this.price=price;
    }

    /**
     * @return price
     */
    public Double getPrice(){
        return price;
    }

    /**
     * set x coordinate
     * @param x - coordinate x
     */
    public void setCoordinatesX(float x){
        this.coordinates.setX(x);
    }

    /**
     * @return coordinates.getX
     */
    public float getCoordinatesX(){
        return coordinates.getX();
    }

    /**
     * set y coordinate
     * @param y coordinate y
     */
    public void setCoordinatesY(long y){
        this.coordinates.setY(y);
    }

    /**
     * @return coordinates.getY
     */
    public long getCoordinatesY(){
        return(coordinates.getY());
    }

    /**
     * set person height
     * @param height person height
     */
    public void setPersonHeight(double height){
        this.person.setHeight(height);
    }

    /**
     * @return person.getHeight
     */
    public double getPersonHeight(){
        return person.getHeight();
    }

    /**
     * set person weight
     * @param weight person weight
     */
    public void setPersonWeight(Integer weight){
        this.person.setWeight(weight);
    }

    /**
     * @return person.getWeight
     */
    public Integer getPersonWeight(){
        return person.getWeight();
    }

    /**
     * set location coordinate x
     * @param x location x
     */
    public void setPersonLocationX(double x){
        this.person.setLocationX(x);
    }

    /**
     * @return person.getLocationX
     */
    public double getPersonLocationX(){
        return person.getLocationX();
    }

    /**
     * set location coordinate y
     * @param y location y
     */
    public void setPersonLocationY(long y){
        this.person.setLocationY(y);
    }
    /**
     * @return person.getLocationY
     */
    public long getPersonLocationY(){
        return person.getLocationY();
    }

    /**
     * set location coordinate z
     * @param z location z
     */
    public void setPersonLocationZ(long z){
        this.person.setLocationZ(z);
    }

    /**
     * @return person.getLocationZ
     */
    public long getPersonLocationZ(){
        return person.getLocationZ();
    }

    /**
     * set location name
     * @param name location name
     */
    public void setPersonLocationName(String name){
        this.person.setLocationName(name);
    }

    /**
     * @return person.getLocationName
     */
    public String getPersonLocationName(){
        return person.getLocationName();
    }


    /**
     * @return ticket as string
     */
    public String toString(){
        String tempString=  "Ticket "+id.toString() +"\n";
        tempString +=       "  name: " + name + "\n";
        tempString +=       coordinates.toString();
        tempString +=       "  creation date: " + creationDate.toString()+"\n";
        tempString +=       "  price: " + price.toString() +"\n";
        if(type!=null)
            tempString +=   "  type: " + type.toString() + "\n";
        if(person!=null)
            tempString += person.toString();
        return tempString;
    }
}
