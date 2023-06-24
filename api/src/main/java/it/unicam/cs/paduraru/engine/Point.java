package it.unicam.cs.paduraru.engine;

public class Point {
    int x,y;

    public Point(){
        x = y = 0;
    }
    public Point(int x,int y){
        this.x = x;
        this.y = y;
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
        x += other.x;
        y += other.y;
        return this;
    }
}
