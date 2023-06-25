package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class Done extends BotCommand{
    public final int ip;
    public Done(int loopPointer){
        this.ip = loopPointer;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        return ip;
    }

    @Override
    public void initialize() {

    }
}
