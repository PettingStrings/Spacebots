package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.PComponent;

public class cSignaler extends PComponent {
    PLabel label;

    protected cSignaler(PEntity parent) {
        super(parent);
    }
}
