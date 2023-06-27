package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;

public class cColliderRobot extends cCollider{
    public cColliderRobot(ERobot parent, Shape shape) {
        super(parent, shape);
    }

    @Override
    public void OnColliding(cCollider second) {
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
    public void OnExit(cCollider second) {
        //Possibiletà di aggiugnere collider specifici per ogni forma
        PEntity secondParent = second.getParent();
        if(secondParent instanceof ELabelledArea)
        {
            ELabelledArea area = (ELabelledArea) secondParent;
            ERobot robotParent = (ERobot) parent;
            robotParent.removeLabel(area.getLabel());
        }
    }
}
