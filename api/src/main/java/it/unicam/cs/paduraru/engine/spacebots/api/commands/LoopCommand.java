package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public abstract class LoopCommand implements BotCommand {
    private int doneIp;
    public int getDoneIp() {
        return doneIp;
    }

    public void setDoneIp(int doneIp) {
        this.doneIp = doneIp;
    }
}
