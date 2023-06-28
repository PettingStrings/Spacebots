package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class Forever extends BotCommand{

    @Override
    public int execute(ERobot target, int instructionPointer) {
        return instructionPointer+1;
    }

    @Override
    public void initialize() {

    }

    @Override
    public Object deepCopy() {
        return new Forever();
    }
}
