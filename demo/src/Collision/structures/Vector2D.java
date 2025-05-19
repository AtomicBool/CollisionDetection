package Collision.structures;

/**
 * Vector2D Utils
 */
public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D vec){
        return new Vector2D(
                this.x + vec.x,
                this.y + vec.y
        );
    }

    public Vector2D add(double x, double y){
        return new Vector2D(
                this.x + x,
                this.y + y
        );
    }

    public Vector2D rotate(double sin, double cos){
        return new Vector2D(
                this.x * cos - this.y * sin,
                this.x * sin + this.y * cos
        );
    }

    static public double dotProduct(Vector2D v1, Vector2D v2){
        return v1.x * v2.x + v1.y * v2.y;
    }
}
