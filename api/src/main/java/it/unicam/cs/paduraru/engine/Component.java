package it.unicam.cs.paduraru.engine;

public class Component {
    long ID;
    protected Entity parent;
    protected Component(Entity parent){
        this.parent = parent;
    }
    public void setID(long newID) {
        ID = newID;
    }
    public long getID(){
        return ID;
    }


    public Entity getParent() {
        return parent;
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Component)) return false;
        return ID == ((Component)object).ID;
    }
}
