package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.Util;

public class PVector implements DeepCopy{
    double x,y;

    public PVector(){
        x = y = 0;
    }
    public PVector(double x, double y){
        this.x = x;
        this.y = y;
    }
    //region Getters-Setters
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    //endregion
    public static PVector random(double minX, double maxX, double minY, double maxY) {
        return new PVector(Util.randDouble(minX, maxX), Util.randDouble(minY, maxY));
    }
    public PVector add(PVector other) {
        return new PVector(x + other.getX(), y + other.getY());
    }

    public PVector divScalar(double i) {
        return new PVector(divide(x,i), divide(y,i));
    }
    private double divide(double dividend, double divisor){
        if(dividend == 0 || divisor == 0) return 0;
        return dividend/divisor;
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
    public PVector distance(PVector other){
        return new PVector(other.getX() - getX(), other.getY() - getY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PVector vector)) return false;
        return x == vector.x && y == vector.y;
    }

    @Override
    public Object deepCopy() {
        return  new PVector(x,y);
    }
}
