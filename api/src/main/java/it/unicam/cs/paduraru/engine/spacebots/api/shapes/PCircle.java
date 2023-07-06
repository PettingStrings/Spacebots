package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

/**
 * Rappresenta un Cerchio.
 */
public class PCircle extends PShape {
    public PCircle(double radius){
        setData(new double[]{radius});
    }
    public double getRadius() {
        return getData()[0];
    }
    public void setRadius(double newRadius){getData()[0] = newRadius;}
    @Override
    public Object deepCopy() {
        return new PCircle(getRadius());
    }
}
