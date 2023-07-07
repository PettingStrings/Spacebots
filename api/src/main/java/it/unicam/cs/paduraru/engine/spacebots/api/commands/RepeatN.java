package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Loop che si ripete per un numero dato di volte.
 */
public class RepeatN extends LoopCommand{
    int currentIteration;
    final int maxIterations;
    boolean isFinished;

    public RepeatN(int maxIterations){
        this.maxIterations = maxIterations;
        this.isFinished = false;
        initialize();
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Prossima riga se non è finito. Se è finito ritorna la prossima riga dopo il done.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        if(isFinished) initialize();

        currentIteration++;
        if(currentIteration > maxIterations){
            isFinished = true;
            return getDoneIp()+1;
        }

        return instructionPointer+1;
    }

    public void initialize() {
        currentIteration = 0;
        isFinished = false;
    }

    @Override
    public Object deepCopy() {
        RepeatN command = new RepeatN(this.maxIterations);
        command.currentIteration = this.currentIteration;
        command.setDoneIp(this.getDoneIp());
        return command;
    }

    @Override
    public String convertToString() {
        return "REPEAT " + maxIterations;
    }
}
