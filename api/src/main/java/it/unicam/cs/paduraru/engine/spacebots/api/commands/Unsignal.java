package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Smette di segnalare una data Label. Se la Label non dovesse esistere non succede nulla.
 */
public class Unsignal implements BotCommand{
    PLabel label;
    public  Unsignal(PLabel label){
        this.label = label;
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.unsignal(label);
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Unsignal((PLabel) this.label.deepCopy());
    }

    @Override
    public String convertToString() {
        return null;
    }
}
