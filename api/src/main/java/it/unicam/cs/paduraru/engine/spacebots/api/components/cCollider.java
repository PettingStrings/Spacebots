package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public class cCollider extends PComponent {
    PShape shape;
    PVector position;

    public cCollider(PEntity parent, PShape shape) {
        super(parent);
        position = parent.GetPosition();
        this.shape = shape;
    }

    protected cCollider(PComponent com, PShape shape) {
        super(com);
        position = parent.GetPosition();
        this.shape = shape;
    }

    protected cCollider(cCollider col){
        this(col, col.shape);
    }

    public void OnColliding(cCollider second){

    }

    public PShape getShape() {
        return shape;
    }

    public PVector getPosition() {
        return parent.getPosition();
    }

    public void OnExit(cCollider second){

    }

    @Override
    public Object deepCopy() {
        return new cCollider((PComponent) super.deepCopy(),(PShape)this.shape.deepCopy());
    }
}
