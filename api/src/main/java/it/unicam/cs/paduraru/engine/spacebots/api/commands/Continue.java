package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

//Come Move prende l'ultimo che è stato aggiunto. Il continue non va mai chiamato senza un precedente move
public class Continue  extends BotCommand{
    int maxSteps, currentStep;
    boolean isFinished;

    public Continue(int steps){
        maxSteps = steps;
        isFinished = false;
        initialize();
    }
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

    public void initialize() {
        currentStep = maxSteps;
        isFinished = false;
    }

    @Override
    public Object deepCopy() {
        return new Continue(maxSteps);
    }
}
