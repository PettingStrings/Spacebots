package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;

public abstract class cCollider extends PComponent {
    Shape shape;
    PVector position;

    public cCollider(PEntity parent, Shape shape) {
        super(parent);
        position = parent.GetPosition();
        this.shape = shape;
    }

    public abstract void OnColliding(cCollider second);

    public Shape getShape() {
        return shape;
    }

    public PVector getPosition() {
        return parent.getPosition();
    }

    public abstract void OnExit(cCollider second);/* {
        //Possibiletà di aggiugnere collider specifici per ogni forma
        Entity secondParent = second.getParent();
        if(parent instanceof eRobot){
            if(secondParent instanceof eLabelledArea)
            {
                eLabelledArea area = (eLabelledArea) secondParent;
                eRobot robotParent = (eRobot) parent;
                robotParent.removeLabel(area.getLabel());
            }
        }
    }*/
}
