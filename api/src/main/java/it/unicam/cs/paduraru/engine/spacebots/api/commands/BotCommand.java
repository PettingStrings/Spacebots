package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Entity;

public abstract class BotCommand {
    boolean isFinished = false;
    public abstract int execute(Entity target, int instructionPointer);
    public abstract void initialize();
}
