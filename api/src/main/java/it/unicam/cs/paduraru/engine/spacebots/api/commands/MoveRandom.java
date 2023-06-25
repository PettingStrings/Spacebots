package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

//MOVE RANDOM x1 x2 y1 y2 s: si muove alla velocità s espressa in m/s verso una posizione (x,y)
//scelta casualmente nell’intervallo [x1, x2] e [y1, y2];
public class MoveRandom extends BotCommand{
    int minX, maxX, minY, maxY, velocity;

    public MoveRandom(int minX, int maxX, int minY, int maxY, int velocity){
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.velocity = velocity;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        target.setDirection(Point.random(minX, maxX, minY, maxY));
        target.setVelocity(velocity);
        target.move();
        return 0;
    }

    @Override
    public void initialize() {

    }
}
