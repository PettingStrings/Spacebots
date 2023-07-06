package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public class RepeatN extends LoopCommand{
    int doneIp, currentIteration;
    final int maxIterations;
    boolean isFinished;

    public RepeatN(int maxIterations){
        this.maxIterations = maxIterations;
        this.isFinished = false;
        initialize();
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
        if(isFinished) initialize();

        currentIteration++;
        if(currentIteration > maxIterations){
            isFinished = true;
            return doneIp+1;
        }

        return instructionPointer+1;
    }

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

    @Override
    public Object deepCopy() {
        RepeatN command = new RepeatN(this.maxIterations);
        command.currentIteration = this.currentIteration;
        command.doneIp = this.doneIp;
        return command;
    }

    @Override
    public String convertToString() {
        return "REPEAT " + maxIterations;
    }
}
