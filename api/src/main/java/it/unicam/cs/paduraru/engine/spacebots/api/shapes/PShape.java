package it.unicam.cs.paduraru.engine.spacebots.api.shapes;

import it.unicam.cs.paduraru.engine.DeepCopy;

public class PShape implements DeepCopy {
    int[] data;

    public PShape(){}
    public PShape(int[] data){
        this.data = data;
    }
    public int[] getData() {
        return data;
    }

    @Override
    public Object deepCopy() {
        return new PShape(this.data.clone());
    }
}
