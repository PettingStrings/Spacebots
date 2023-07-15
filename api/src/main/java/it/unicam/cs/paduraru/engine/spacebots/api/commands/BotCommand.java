package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PDeepCopy;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Definisce la base per la creazione di un comando per i Robot.
 */
public interface BotCommand extends PDeepCopy {
    /**
     * Esegue il comando.
     * @param target Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero di riga della prossima istruzione.
     */
    int execute(PRobot target, int instructionPointer);
}
