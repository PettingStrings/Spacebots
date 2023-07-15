package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import jdk.jfr.consumer.RecordedThread;

/**
 * Delimita la fine di un qualsiasi comando di loop.
 * Esso funziona memorizzando in che posizione sta il loop
 */
public class Done implements BotCommand{
    public final int ip;
    public Done(int loopPointer){
        this.ip = loopPointer;
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l'istruzione.
     * @return Numero della riga del loop a cui appartiene.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        return ip;
    }

    @Override
    public Object deepCopy() {
        return new Done(ip);
    }
}
