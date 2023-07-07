package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Si ripete fino a quando il Robot non entra in contatto con una data Label.
 */
public class Until extends LoopCommand{
    PLabel label;

    public Until(PLabel label){
        this.label = label;
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
       if(target.getCurrentLabels().contains(label))
           return getDoneIp()+1;

       return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        Until command = new Until((PLabel) this.label.deepCopy());
        command.setDoneIp(getDoneIp());
        return command;
    }

    @Override
    public String convertToString() {
        return null;
    }
}
