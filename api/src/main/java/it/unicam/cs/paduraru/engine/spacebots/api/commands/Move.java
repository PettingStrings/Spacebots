package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public class Move extends BotCommand{
    PVector direction;
    double velocity;
    public Move(PVector direction, double velocity){
        this.direction = direction;
        this.velocity = velocity;
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.setDirection(direction);
        target.setVelocity(velocity);
        target.move();
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Move((PVector) this.direction.deepCopy(),this.velocity);
    }
}
