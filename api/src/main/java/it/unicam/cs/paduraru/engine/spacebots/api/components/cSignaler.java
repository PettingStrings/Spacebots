package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.Component;

public class cSignaler extends Component{
    Label label;

    protected cSignaler(Entity parent) {
        super(parent);
    }
}
