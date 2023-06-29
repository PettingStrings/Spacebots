package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
//Di defualt i collider hanno la stessa posizione del padre
public abstract class PCollider extends PComponent {
    private PShape shape;

    public PCollider(PEntity parent, PShape shape) {
        super(parent);
        this.shape = shape;
    }
    protected PCollider(PComponent component, PShape shape) {
        super(component);
        this.shape = shape;
    }
    public PShape getShape() {
        return shape;
    }

    public void setShape(PShape newShape){
        this.shape = newShape;
    }

    public PVector getPosition() {
        return parent.getPosition();
    }

    public abstract void OnExit(PCollider second);
    public abstract void OnColliding(PCollider second);
}
