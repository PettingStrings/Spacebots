package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

//Come Move prende l'ultimo che è stato aggiunto. Il continue non va mai chiamato senza un precedente move
public class Continue  extends BotCommand{
    int maxSteps, currentStep;

    public Continue(int steps){
        maxSteps = steps;
        initialize();
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
        if(isFinished) initialize();

        currentStep--;

        if(currentStep < 0){
            isFinished = true;
            return instructionPointer+1;
        }

       target.move();

        return instructionPointer;
    }

    @Override
    public void initialize() {
        currentStep = maxSteps;
        isFinished = false;
    }
}
