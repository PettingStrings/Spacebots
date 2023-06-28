package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.DeepCopy;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public abstract class BotCommand implements DeepCopy {
    boolean isFinished = false;
    public abstract int execute(ERobot target, int instructionPointer);
    public abstract void initialize();
}
