package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Segnala una data label. Ho lasciato la possibiltà di segnalare una label con cui non si è entrati in contatto.
 */
public class Signal implements BotCommand{
    PLabel label;
    public  Signal(PLabel label){
        this.label = label;
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.signal(label);
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Signal((PLabel) this.label.deepCopy());
    }
}
