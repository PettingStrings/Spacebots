package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;

public class cColliderGeneric extends cCollider{
    public cColliderGeneric(Entity parent, Shape shape) {
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