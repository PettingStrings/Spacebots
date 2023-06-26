package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class Move extends BotCommand{
    Vector direction;
    int velocity;
    public Move(Vector direction, int velocity){
        this.direction = direction;
        this.velocity = velocity;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        target.setDirection(direction);
        target.setVelocity(velocity);
        target.move();
        return instructionPointer+1;
    }

    @Override
    public void initialize() {

    }
}