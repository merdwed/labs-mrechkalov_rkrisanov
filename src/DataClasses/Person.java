package DataClasses;

/**
 * @author Drukharion
 */

public class Person {
    public Person(double height, Integer weight, Location location){
    }
    public Person(){
    }
    private double height; //Значение поля должно быть больше 0
    private Integer weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location=new Location(); //Поле может быть null

    /**
     * set location
     * @param location = location class
     * @see Location
     */
    public void setLocation(Location location){
        this.location=location;
    }

    /**
     * set height person
     * @param height - height person
     */
    public void setHeight(double height){
        this.height = height;
    }

    /**
     * @return height - height person
     */
    public double getHeight(){
        return height;
    }

    /**
     * set weight person
     * @param weight - weight person
     */
    public void setWeight(Integer weight){
        this.weight = weight;
    }

    /**
     * @return weight - weight person
     */
    public Integer getWeight(){
        return weight;
    }

    /**
     * set location coordinate x
     * @param x
     * @see Location
     */
    public void setLocationX(double x){
        this.location.setX(x);
    }

    /**
     * @return location.getX
     * @see Location
     */
    public double getLocationX(){
        return location.getX();
    }

    /**
     * set location coordinate y
     * @param y
     * @see Location
     */
    public void setLocationY(long y){
        this.location.setY(y);
    }

    /**
     * @return location.getY
     * @see Location
     */
    public long getLocationY(){
        return location.getY();
    }

    /**
     * set location coordinate z
     * @param z
     * @see Location
     */
    public void setLocationZ(long z){
        this.location.setZ(z);
    }

    /**
     * @return location.getZ
     * @see Location
     */
    public long getLocationZ(){
        return location.getZ();
    }

    /**
     * set location name
     * @param name - location name
     * @see Location
     */
    public void setLocationName(String name){
        this.location.setName(name);
    }

    /**
     * @return location.getName
     * @see Location
     */
    public String getLocationName(){
        return location.getName();
    }

    /**
     * @return Person class as string
     */
    public String toString(){
        String tempString=
                "  person:\n"+
                "    height: " + height + "\n"+
                "    weight: " + weight + "\n";
        if(location!=null)
            tempString+=location.toString();
        return tempString;
    }
}
