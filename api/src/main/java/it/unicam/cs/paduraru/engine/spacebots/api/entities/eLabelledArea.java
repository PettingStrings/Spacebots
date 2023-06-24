package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;
import it.unicam.cs.paduraru.engine.Entity;

public class eLabelledArea extends Entity {
    Label label;
    Shape shape;

    public Label getLabel() {
        return label;
    }
}
