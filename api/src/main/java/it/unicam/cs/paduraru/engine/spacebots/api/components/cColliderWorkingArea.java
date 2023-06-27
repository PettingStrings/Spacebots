package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public class cColliderWorkingArea extends cCollider{
    public cColliderWorkingArea(ELabelledArea parent, PShape shape) {
        super(parent, shape);
    }
//code smell
    @Override
    public void OnColliding(cCollider second) {
        //Non serve
    }

    @Override
    public void OnExit(cCollider second) {
        //Non serve
    }
}
