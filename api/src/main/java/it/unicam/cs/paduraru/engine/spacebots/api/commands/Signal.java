package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
//Ho pensato che cmq esssendo hardcoded nel programma del robot la label da segnalare,
//esso possa segnalare anche label con cui non è entrato a contatto
public class Signal implements BotCommand{
    PLabel label;
    public  Signal(PLabel label){
        this.label = label;
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.signal(label);
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Signal((PLabel) this.label.deepCopy());
    }

    @Override
    public String convertToString() {
        return "SIGNAL "+label.convertToString();
    }
}
