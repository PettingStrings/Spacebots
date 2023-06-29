package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.DeepCopy;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public abstract class BotCommand implements DeepCopy {
    public abstract int execute(PRobot target, int instructionPointer);
}
