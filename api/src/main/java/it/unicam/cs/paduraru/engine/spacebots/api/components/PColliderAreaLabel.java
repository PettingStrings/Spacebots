package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

/**
 * Collider specifico per un'AreaLabel.
 */
public class PColliderAreaLabel extends PCollider {
    public PColliderAreaLabel(PAreaLabel parent, PShape shape) {
        super(parent, shape);
    }

    public PColliderAreaLabel(PComponent pComponent, PShape shape) {
        super(pComponent,shape);
    }

    @Override
    public void OnColliding(PCollider second) {/*Non serve*/}
    @Override
    public void OnExit(PCollider second) {/*Non serve*/}
    @Override
    public Object deepCopy() {
        return new PColliderAreaLabel((PComponent) super.deepCopy(),(PShape)this.getShape().deepCopy());
    }
}
