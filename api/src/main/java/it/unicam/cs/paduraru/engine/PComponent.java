package it.unicam.cs.paduraru.engine;

public class PComponent implements DeepCopy{
    long ID;
    protected PEntity parent;
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


    public PEntity getParent() {
        return parent;
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof PComponent)) return false;
        return ID == ((PComponent)object).ID;
    }

    @Override
    public Object deepCopy() {
        PComponent component = new PComponent((PEntity) parent.deepCopy());
        component.ID = this.ID;
        return component;
    }
}
