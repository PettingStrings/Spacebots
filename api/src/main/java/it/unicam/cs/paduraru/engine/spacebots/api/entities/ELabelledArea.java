package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.PEntity;

import java.util.ArrayList;
import java.util.Objects;

public class ELabelledArea extends PEntity {
    PLabel label;

    public ELabelledArea(PVector origin, PLabel label){
        SetPosition(origin);
        this.label = label;
    }
    protected ELabelledArea(PEntity o) {
        super(o);
    }

    public PLabel getLabel() {
        return label;
    }

    @Override
    public Object deepCopy() {
        ELabelledArea area = new ELabelledArea((PEntity)super.deepCopy());
        area.label = (PLabel) this.label.deepCopy();
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ELabelledArea)) return false;
        if (super.equals(o)) return true;

        return false;
    }
}
