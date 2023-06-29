package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.PEntity;

public class PAreaLabel extends PEntity {
    private PLabel label;

    public PAreaLabel(PVector origin, PLabel label){
        SetPosition(origin);
        this.label = label;
    }
    protected PAreaLabel(PEntity entity) {
        super(entity);
    }

    public PLabel getLabel() {
        return label;
    }

    @Override
    public Object deepCopy() {
        PAreaLabel area = new PAreaLabel((PEntity)super.deepCopy());
        area.label = (PLabel) this.label.deepCopy();
        return area;
    }
}
