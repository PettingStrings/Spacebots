package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.PEntity;

public class ELabelledArea extends PEntity {
    PLabel label;
    PShape shape;

    public PLabel getLabel() {
        return label;
    }
}
