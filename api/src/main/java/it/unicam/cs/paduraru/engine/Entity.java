package it.unicam.cs.paduraru.engine;

public class Entity {
    private long ID;
    private Vector position;
    public Entity(Vector origin){
        position = origin;
    }

    public Entity(){};
    public void setID(long newID){
        ID = newID;
    }
    public long setID(){
        return ID;
    }

    protected void SetPosition(Vector newPosition){
        position = newPosition;
    }

    public Vector GetPosition(){
        return position;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector other) {
        this.position = other;
    }
    public <T extends  Component> T getComponent(){
        //Call game, environment, susmem la caca
        return null;
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Entity)) return false;
        return ID == ((Entity)object).ID;
    }
}
