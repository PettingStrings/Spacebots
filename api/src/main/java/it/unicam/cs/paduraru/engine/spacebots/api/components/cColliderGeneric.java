package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

public class cColliderGeneric extends cCollider{
    public cColliderGeneric(PEntity parent, PShape shape) {
        super(parent, shape);
    }

    @Override
    public void OnColliding(cCollider second) {
        return;
    }

    @Override
    public void OnExit(cCollider second) {
        return;
    }
}
