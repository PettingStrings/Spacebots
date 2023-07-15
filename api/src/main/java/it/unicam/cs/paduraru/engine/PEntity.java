package it.unicam.cs.paduraru.engine;

/**
 * Rappresenta un'oggetto dentro un PEnvironment.
 */
public class PEntity implements PDeepCopy {
    private long ID;
    private PVector position;
    public PEntity(){}
    public PEntity(PVector origin){
        position = origin;
    }

    protected PEntity(PEntity o){
        this.setID(o.ID);
        this.position = o.position;
    }

    public void setID(long newID){
        ID = newID;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector other) {
        this.position = other;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof PEntity)) return false;
        return ID == ((PEntity)object).ID;
    }

    @Override
    public Object deepCopy() {
        PEntity copied = new PEntity();

        copied.ID = this.ID;
        copied.position = (PVector) this.position.deepCopy();

        return copied;

    }

    @Override
    public String convertToString() {
        return "PEntity Not Implemented";
    }
}
