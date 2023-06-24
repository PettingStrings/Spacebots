package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Entity;

public class Continue  extends BotCommand{
    int maxSteps, currentStep;
    public Continue(int steps){
        maxSteps = steps;
        initialize();
    }
    @Override
    public int execute(Entity target, int instructionPointer) {
        if(isFinished) initialize();
        return instructionPointer-1;
    }

    @Override
    public void initialize() {
        currentStep = maxSteps;
    }
}
