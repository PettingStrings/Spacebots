package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

import it.unicam.cs.paduraru.engine.PDeepCopy;

/**
 * Rappresenta una forma generica.
 */
public class PShape implements PDeepCopy {
    private double[] data;

    public PShape(){}
    public PShape(double[] data){
        this.data = data;
    }
    public double[] getData() {
        return data;
    }
    public void setData(double[] newArr){data = newArr;}
    @Override
    public Object deepCopy() {
        return new PShape(this.data.clone());
    }

    @Override
    public String convertToString() {
        return null;
    }
}
