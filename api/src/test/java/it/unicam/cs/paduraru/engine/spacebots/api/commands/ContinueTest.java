package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinueTest {

    @Test
    void execute() {
        int ip = 0, steps = 3;
        ERobot bot = new ERobot(new Point(0,0));
        Move move =  new Move(new Point(1,1), 1);
        BotCommand[] commands = new BotCommand[] { move, new Continue(steps) };
        //Execute Move
        ip = commands[ip].execute(bot,ip);
            //Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(2,2)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(3,3)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(4,4)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(4,4)) && ip == 2);

        ip--;
//Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(5,5)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(6,6)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(7,7)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Point(7,7)) && ip == 2);
    }
}