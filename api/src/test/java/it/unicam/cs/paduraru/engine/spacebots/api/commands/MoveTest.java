package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void execute() {
        int ip = 0;
        ERobot bot = new ERobot(new Point(0,0));
        Move commMove = new Move(new Point(1,1),1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(1,1)) && ip == 1);

        ip = commMove.execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(2,2)) && ip == 2);
    }
}