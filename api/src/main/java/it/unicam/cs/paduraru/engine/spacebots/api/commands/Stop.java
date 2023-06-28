package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
//STOP: arresta il proprio movimento. Il robot non si muove più ma continua a segnalare le proprie condizioni.
//Comando inutile, per come l'ho pensato io il programma, il robot si muove quando esegue l'istruzione move o continue
//
public class Stop extends BotCommand{
    @Override
    public int execute(ERobot target, int instructionPointer) {
        return instructionPointer+1;
    }

    @Override
    public Object deepCopy() {
        return new Stop();
    }
}
