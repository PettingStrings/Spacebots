package it.unicam.cs.paduraru.engine;

public class PComponent {
    long ID;
    protected PEntity parent;
    protected PComponent(PEntity parent){
        this.parent = parent;
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
}
