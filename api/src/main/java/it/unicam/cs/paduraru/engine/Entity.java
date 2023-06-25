package it.unicam.cs.paduraru.engine;

public class Entity {
    private long ID;
    private Point position;
    public Entity(Point origin){
        position = origin;
    }

    public Entity(){};
    public void setID(long newID){
        ID = newID;
    }
    public long setID(){
        return ID;
    }

    protected void SetPosition(Point newPosition){
        position = newPosition;
    }

    public Point GetPosition(){
        return position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point other) {
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
