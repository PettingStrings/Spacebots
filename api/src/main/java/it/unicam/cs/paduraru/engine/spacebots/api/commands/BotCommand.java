package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public abstract class BotCommand {
    boolean isFinished = false;
    public abstract int execute(ERobot target, int instructionPointer);
    public abstract void initialize();
}
