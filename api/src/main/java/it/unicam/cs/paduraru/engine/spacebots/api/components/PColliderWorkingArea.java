package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public class PColliderWorkingArea extends PCollider {
    public PColliderWorkingArea(PAreaLabel parent, PShape shape) {
        super(parent, shape);
    }

    public PColliderWorkingArea(PComponent pComponent, PShape shape) {
        super(pComponent,shape);
    }

    @Override
    public void OnColliding(PCollider second) {/*Non serve*/}
    @Override
    public void OnExit(PCollider second) {/*Non serve*/}
    @Override
    public Object deepCopy() {
        return new PColliderWorkingArea((PComponent) super.deepCopy(),(PShape)this.getShape().deepCopy());
    }
}
