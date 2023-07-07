package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Ferma il movimento del Robot. NOTA BENE: visto che i Robot si muove solo quando il comando MOVE, MOVERANDOM e CONTINUE
 * vengono eseguiti, il comando stop risulta inutile. Per non renderlo totalmente inutile ho fatto che la velocità del Robot
 * diventi 0.
 */
public class Stop implements BotCommand{
    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.setVelocity(0);
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Stop();
    }

    @Override
    public String convertToString() {
        return null;
    }
}
