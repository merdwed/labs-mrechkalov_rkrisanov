package DataClasses;

/**
 * @author Drukharion
 */
public class Location {
    public Location(Double x,long y,long z,String name){
    }
    public Location(){
    }
    private double x; //Поле не может быть null
    private long y;
    private long z;
    private String name; //Длина строки не должна быть больше 852, Поле не может быть null

    /**
     * set location coordinate x
     * @param x - location coordinate x
     */
    public void setX(Double x){
        this.x=x;
    }
    /**
     * @return x - location coordinate x
     */
    public double getX(){
        return x;
    }
    /**
     * set location coordinate y
     * @param y - location coordinate y
     */
    public void setY(long y){
        this.y=y;
    }
    /**
     * @return y - location coordinate y
     */
    public long getY(){
        return y;
    }
    /**
     * set location coordinate z
     * @param z - location coordinate z
     */
    public void setZ(long z){
        this.z=z;
    }
    /**
     * @return z - location coordinate z
     */
    public long getZ(){
        return z;
    }
    /**
     * set location name
     * @param name - location name
     */
    public void setName(String name){
        this.name=name;
    }
    /**
     * @return name - location name
     */
    public String getName(){
        return name;
    }

    /**
     * @return Location class as string
     */
    public String toString(){
        return  "    Location:\n"+
                "      x: " + x + "\n" +
                "      y: " + y + "\n" +
                "      z: " + z + "\n" +
                "      name: " + name + "\n";
    }
}
