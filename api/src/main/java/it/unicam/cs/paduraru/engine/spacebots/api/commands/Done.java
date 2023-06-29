package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public class Done extends BotCommand{
    public final int ip;
    public Done(int loopPointer){
        this.ip = loopPointer;
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
        return ip;
    }

    @Override
    public Object deepCopy() {
        return new Done(ip);
    }
}
