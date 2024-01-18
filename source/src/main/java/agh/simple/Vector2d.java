package agh.simple;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.hash;

public class Vector2d {
    private final int x;
    private final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public String toString(){
        return String.format("(%d,%d)", this.x, this.y);
    }
    public boolean precedes(Vector2d other){ return this.x <= other.x && this.y <= other.y;}
    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    public Vector2d upperRight(Vector2d other){
        return new Vector2d(max(this.x,other.x),max(this.y,other.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(min(this.x,other.x),min(this.y,other.y));
    }
    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public int hashCode() {
        return hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        return this.x == ((Vector2d) other).getX() && this.y == ((Vector2d) other).getY();
    }
}
