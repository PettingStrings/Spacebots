package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public class Forever extends BotCommand{

    @Override
    public int execute(PRobot target, int instructionPointer) {
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Forever();
    }
}
