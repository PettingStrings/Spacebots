package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

import it.unicam.cs.paduraru.engine.Point;

public class Circle extends Shape {
    public Circle(int radius){
        data = new int[]{radius};
    }

    public int getRadius() {
        return data[0];
    }
}
