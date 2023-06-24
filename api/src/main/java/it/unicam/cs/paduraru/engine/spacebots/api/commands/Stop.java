package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Entity;

public class Stop extends BotCommand{
    @Override
    public int execute(Entity target, int instructionPointer) {
        return 0;
    }

    @Override
    public void initialize() {

    }
}
