package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Loop infinito
 */
public class Forever extends LoopCommand{

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Forever();
    }

    @Override
    public String convertToString() {
        return "DO FOREVER";
    }
}
