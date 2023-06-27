package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.Util;

import java.util.Objects;

public class PVector {
    double x,y;

    public PVector(){
        x = y = 0;
    }
    public PVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static PVector random(double minX, double maxX, double minY, double maxY) {
        return new PVector(Util.randInt(minX, maxX),Util.randInt(minY, maxY));
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

    public PVector add(PVector other) {
        return new PVector(x + other.getX(), y + other.getY());
    }

    public PVector divScalar(double i) {
        return new PVector(x/i, y/i);
    }
    public PVector mulScalar(double i){
        return new PVector(x*i, y*i);
    }
    public double magnitude(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public PVector normalize(){
        return divScalar(magnitude());
    }
    public PVector distance(PVector from){
        return new PVector(from.getX() - getX(), from.getY() - getY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PVector vector)) return false;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
