package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

public class PCircle extends PShape {
    public PCircle(int radius){
        data = new int[]{radius};
    }

    public int getRadius() {
        return data[0];
    }
}
