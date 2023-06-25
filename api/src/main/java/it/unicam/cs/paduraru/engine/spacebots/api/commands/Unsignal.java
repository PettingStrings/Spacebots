package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class Unsignal extends BotCommand{
    Label label;
    public  Unsignal(Label label){
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
