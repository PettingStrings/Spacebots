package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Entity;

public class Done extends BotCommand{
    public final int ip;
    public Done(int loopPointer){
        this.ip = loopPointer;
    }
    @Override
    public int execute(Entity target, int instructionPointer) {
        return ip;
    }

    @Override
    public void initialize() {

    }
}
