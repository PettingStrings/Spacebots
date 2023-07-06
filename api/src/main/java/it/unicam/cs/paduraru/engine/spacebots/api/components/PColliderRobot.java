package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

/**
 * Collider specifico per un Robot.
 */
public class PColliderRobot extends PCollider {
    public PColliderRobot(PRobot parent, PShape shape) {
        super(parent, shape);
    }

    private PColliderRobot(PComponent pComponent, PShape pShape) {
        super(pComponent,pShape);
    }

    @Override
    public void OnColliding(PCollider second) {
        PEntity secondParent = second.getParent();
        if(secondParent instanceof PAreaLabel)
        {
            PAreaLabel area = (PAreaLabel) secondParent;
            PRobot robotParent = (PRobot) getParent();
            robotParent.addLabel(area.getLabel());
        }
    }

    @Override
    public void OnExit(PCollider second) {
        PEntity secondParent = second.getParent();
        if(secondParent instanceof PAreaLabel)
        {
            PAreaLabel area = (PAreaLabel) secondParent;
            PRobot robotParent = (PRobot) getParent();
            robotParent.removeLabel(area.getLabel());
        }
    }

    @Override
    public Object deepCopy() {
        return new PColliderRobot((PComponent) super.deepCopy(),(PShape)this.getShape().deepCopy());
    }
}
