package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Definisce il modello per creare un comando di loop per i Robot.
 */
public abstract class LoopCommand implements BotCommand {
    private int doneIp;
    public int getDoneIp() {
        return doneIp;
    }
    public void setDoneIp(int doneIp) {
        this.doneIp = doneIp;
    }
}
