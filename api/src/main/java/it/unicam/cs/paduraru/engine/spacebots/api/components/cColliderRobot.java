package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.eRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;

public class cColliderRobot extends cCollider{
    public cColliderRobot(eRobot parent, Shape shape) {
        super(parent, shape);
    }

    @Override
    public void OnColliding(cCollider second) {

    }

    @Override
    public void OnExit(cCollider second) {

    }
}
