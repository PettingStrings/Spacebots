package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Questo comando fa continuare il robot a muoversi alla direzione e velocità attuale.
 */
public class Continue  implements BotCommand{
    int maxSteps, currentStep;
    boolean isFinished;

    public Continue(int steps){
        maxSteps = steps;
        isFinished = false;
        initialize();
    }

    /**
     *
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l'istruzione.
     * @return Numero della riga successiva.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        if(isFinished) initialize();

        currentStep--;

        if(currentStep < 0){
            isFinished = true;
            return instructionPointer+1;
        }

       target.move();

        return instructionPointer;
    }

    /**
     * Riporta la classe allo stato iniziale.
     */
    public void initialize() {
        currentStep = maxSteps;
        isFinished = false;
    }

    @Override
    public Object deepCopy() {
        return new Continue(maxSteps);
    }
}
