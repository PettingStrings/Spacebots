package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class Unsignal extends BotCommand{
    PLabel label;
    public  Unsignal(PLabel label){
        this.label = label;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        target.unsignal(label);
        return instructionPointer+1;
    }

    @Override
    public void initialize() {

    }
}
