package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.Util;

import java.util.Objects;

public class Vector {
    double x,y;

    public Vector(){
        x = y = 0;
    }
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Vector random(double minX, double maxX, double minY, double maxY) {
        return new Vector(Util.randInt(minX, maxX),Util.randInt(minY, maxY));
    }

    public void SetX(double newVal){
        x = newVal;
    }
    public double getX(){
        return x;
    }
    public void SetY(double newVal){
        y = newVal;
    }
    public double getY(){
        return y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY());
    }

    public Vector divScalar(double i) {
        return new Vector(x/i, y/i);
    }
    public Vector mulScalar(double i){
        return new Vector(x*i, y*i);
    }
    public double magnitude(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public Vector normalize(){
        return divScalar(magnitude());
    }
    public Vector distance(Vector from){
        return new Vector(from.getX() - getX(), from.getY() - getY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
