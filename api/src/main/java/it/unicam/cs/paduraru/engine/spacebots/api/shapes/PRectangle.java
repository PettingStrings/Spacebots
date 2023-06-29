package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

public class PRectangle extends PShape {
    public PRectangle(double width, double height){
        setData(new double[]{width, height});
    }
    public double getWidth() {
        return getData()[0];
    }
    public double getHeight() {
        return getData()[1];
    }

    @Override
    public Object deepCopy() {
        return new PRectangle(getWidth(),getHeight());
    }
}
