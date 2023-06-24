package it.unicam.cs.paduraru.engine;

public class Component {
    long ID;
    protected Entity parent;
    protected Component(Entity parent){
        this.parent = parent;
    }
    protected void SetID(long newID) {
        ID = newID;
    }
    public long GetID(){
        return ID;
    }


    protected Entity getParent() {
        return parent;
    }
}
