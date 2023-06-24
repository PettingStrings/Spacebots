package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.Point;

public class Move extends BotCommand{
    Point velocity;
    public Move(Point vel){
        velocity = vel;
    }
    @Override
    public int execute(Entity target, int instructionPointer) {
        target.setPosition(target.getPosition().add(velocity));
        return instructionPointer+1;
    }

    @Override
    public void initialize() {

    }
}
