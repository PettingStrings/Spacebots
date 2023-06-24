package it.unicam.cs.paduraru.engine;

public class Entity {
    private long ID;
    private Point position;
    public Entity(Point origin){
        position = origin;
    }

    public Entity(){};
    protected void SetID(long newID){
        ID = newID;
    }
    protected long GetID(){
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
}
