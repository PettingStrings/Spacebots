package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public class PColliderRobot extends PCollider {
    public PColliderRobot(ERobot parent, PShape shape) {
        super(parent, shape);
    }

    private PColliderRobot(PComponent pComponent, PShape pShape) {
        super(pComponent,pShape);
    }

    @Override
    public void OnColliding(PCollider second) {
        //Possibiletà di aggiugnere collider specifici per ogni forma
        PEntity secondParent = second.getParent();
        if(secondParent instanceof ELabelledArea)
        {
            ELabelledArea area = (ELabelledArea) secondParent;
            ERobot robotParent = (ERobot) parent;
            robotParent.addLabel(area.getLabel());
        }
    }

    @Override
    public void OnExit(PCollider second) {
        //Possibiletà di aggiugnere collider specifici per ogni forma
        PEntity secondParent = second.getParent();
        if(secondParent instanceof ELabelledArea)
        {
            ELabelledArea area = (ELabelledArea) secondParent;
            ERobot robotParent = (ERobot) parent;
            robotParent.removeLabel(area.getLabel());
        }
    }

    @Override
    public Object deepCopy() {
        return new PColliderRobot((PComponent) super.deepCopy(),(PShape)this.shape.deepCopy());
    }
}
