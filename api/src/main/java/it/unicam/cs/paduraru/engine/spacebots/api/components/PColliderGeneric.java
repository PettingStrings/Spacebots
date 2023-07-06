package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

/**
 * Collider generico usato per testing.
 */
public class PColliderGeneric extends PCollider {
    public PColliderGeneric(PEntity parent, PShape shape) {
        super(parent, shape);
    }
    protected PColliderGeneric(PComponent component, PShape shape){
        super(component,shape);
    }
    @Override
    public void OnColliding(PCollider second) {
        return;
    }

    @Override
    public void OnExit(PCollider second) {
        return;
    }
    @Override
    public Object deepCopy() {
        return new PColliderGeneric((PComponent) super.deepCopy(),(PShape)this.getShape().deepCopy());
    }
}
