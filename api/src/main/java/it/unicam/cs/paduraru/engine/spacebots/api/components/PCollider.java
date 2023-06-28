package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public abstract class PCollider extends PComponent {
    PShape shape;
    PVector position;
    public PCollider(PEntity parent, PShape shape) {
        super(parent);
        this.position = parent.GetPosition();
        this.shape = shape;
    }
    protected PCollider(PComponent component, PShape shape) {
        super(component);
        position = parent.GetPosition();
        this.shape = shape;
    }
    public PShape getShape() {
        return shape;
    }

    public PVector getPosition() {
        return parent.getPosition();
    }

    public abstract void OnExit(PCollider second);
    public abstract void OnColliding(PCollider second);
}
