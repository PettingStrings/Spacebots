package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

public class RepeatN extends BotCommand{
    int doneIp, currentIteration;
    final int maxIteration;

    public RepeatN(int maxIteration){
        this.maxIteration = maxIteration;
        initialize();
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        if(isFinished) initialize();

        currentIteration++;
        if(currentIteration > maxIteration){
            isFinished = true;
            return doneIp+1;
        }

        return instructionPointer+1;
    }

    @Override
    public void initialize() {
        currentIteration = 0;
        isFinished = false;
    }

    public int getDoneIp() {
        return doneIp;
    }

    public void setDoneIp(int doneIp) {
        this.doneIp = doneIp;
    }
}
