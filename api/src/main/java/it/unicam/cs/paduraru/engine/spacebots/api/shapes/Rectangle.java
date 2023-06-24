package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

public class Rectangle extends Shape {
    public Rectangle(int width, int height){
        data = new int[]{width, height};
    }

    public int GetWidth(){ return data[0]; }
    public int GetHeight(){ return data[1]; }

    public int getWidth() {
        return data[0];
    }

    public int getHeight() {
        return data[1];
    }
}
