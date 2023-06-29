package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

public class Unsignal extends BotCommand{
    PLabel label;
    public  Unsignal(PLabel label){
        this.label = label;
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.unsignal(label);
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Unsignal((PLabel) this.label.deepCopy());
    }
}
