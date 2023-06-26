package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

public class Circle extends Shape {
    public Circle(int radius){
        data = new int[]{radius};
    }

    public int getRadius() {
        return data[0];
    }
}
