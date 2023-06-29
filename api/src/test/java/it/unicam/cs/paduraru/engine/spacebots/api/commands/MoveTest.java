package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void execute() {
        int ip = 0;
        PRobot bot = new PRobot(new PVector(0,0));
        Move commMove = new Move(new PVector(1,0),1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(1,0)) && ip == 1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(2,0)) && ip == 2);
    }
}