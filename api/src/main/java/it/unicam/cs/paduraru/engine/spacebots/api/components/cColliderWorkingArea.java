package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.eLabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;

public class cColliderWorkingArea extends cCollider{
    public cColliderWorkingArea(eLabelledArea parent, Shape shape) {
        super(parent, shape);
    }

    @Override
    public void OnColliding(cCollider second) {

    }

    @Override
    public void OnExit(cCollider second) {

    }
}
