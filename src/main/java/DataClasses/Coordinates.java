package DataClasses;

import java.io.Serializable;

/**
 * @author Drukharion
 */

public class Coordinates implements Serializable {
    public Coordinates(float x, long y){
        this.x=x;
        this.y=y;
    };
    public Coordinates(){
    };
    private float x;
    private long y;

    /**
     * set coordinate x
     * @param x - coordinate x
     */
    public void setX(float x){
        this.x=x;
    }

    /**
     * @return x - coordinate x
     */
    public float getX(){
        return x;
    }

    /**
     * set coordinate y
     * @param y - coordinate y
     */
    public void setY(long y){
        this.y=y;
    }

    /**
     * @return y - coordinate y
     */
    public long getY(){
        return y;
    }

    /**
     *
     * @return Coordinates class as string
     */
    public String toString(){
        return  "  coordinates:\n"+
                "    x: " + x + "\n" +
                "    y: " + y + "\n";
    }
}
