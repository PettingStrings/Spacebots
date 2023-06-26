package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void execute() {
        int ip = 0;
        ERobot bot = new ERobot(new Vector(0,0));
        Move commMove = new Move(new Vector(1,1),1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(1,1)) && ip == 1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(2,2)) && ip == 2);
    }
}