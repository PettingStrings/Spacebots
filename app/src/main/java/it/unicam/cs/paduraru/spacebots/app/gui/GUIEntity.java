package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.PEntity;
import javafx.scene.shape.Shape;

public class GUIEntity{
    PEntity entity;
    Shape shape;

    public GUIEntity(PEntity pEntity, Shape fxShape) {
        entity = pEntity;
        shape = fxShape;
        shape.setLayoutX(entity.getPosition().getX());
        shape.setLayoutY(entity.getPosition().getY());
    }
}
