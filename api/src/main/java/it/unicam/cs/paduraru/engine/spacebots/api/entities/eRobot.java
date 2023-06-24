package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.Entity;

import java.util.List;

public class eRobot extends Entity {

    List<Label> labels;

    public eRobot(Point origin) {
        super();
        SetPosition(origin);
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    public void removeLabel(Label label) {
        labels.remove(label);
    }
}
