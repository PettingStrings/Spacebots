package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.Util;

import java.util.Objects;

public class Point {
    int x,y;

    public Point(){
        x = y = 0;
    }
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public static Point random(int minX, int maxX, int minY, int maxY) {
        return new Point(Util.randInt(minX, maxX),Util.randInt(minY, maxY));
    }

    public void SetX(int newVal){
        x = newVal;
    }
    public int getX(){
        return x;
    }
    public void SetY(int newVal){
        y = newVal;
    }
    public int getY(){
        return y;
    }

    public Point add(Point other) {
        return new Point(x + other.getX(), y + other.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point divScalar(int i) {
        return new Point(x/i, y/i);
    }
}
