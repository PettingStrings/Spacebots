package it.unicam.cs.paduraru.engine;

/**
 * Definisce una proprietà all'entità a cui è associato.
 * Un componente viene gestito da un sistema.
 */
public class PComponent implements PDeepCopy {
    private long ID;
    private PEntity parent;
    protected PComponent(PEntity parent){
        this.parent = parent;
    }

    protected PComponent(PComponent comp) {
        this.setID(comp.ID);
        this.parent = comp.parent;
    }

    public void setID(long newID) {
        ID = newID;
    }
    public long getID(){
        return ID;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(!(object instanceof PComponent)) return false;
        return ID == ((PComponent)object).ID;
    }
    public PEntity getParent() {
        return parent;
    }

    public void setParent(PEntity parent) {
        this.parent = parent;
    }
    @Override
    public Object deepCopy() {
        PComponent component = new PComponent((PEntity) parent.deepCopy());
        component.ID = this.ID;
        return component;
    }
}
