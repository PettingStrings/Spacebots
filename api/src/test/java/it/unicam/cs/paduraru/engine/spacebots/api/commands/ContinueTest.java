package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinueTest {

    @Test
    void execute() {
        int ip = 0, steps = 3;
        ERobot bot = new ERobot(new Vector(0,0));
        Move move =  new Move(new Vector(1,0), 1);
        BotCommand[] commands = new BotCommand[] { move, new Continue(steps) };
        //Execute Move
        ip = commands[ip].execute(bot,ip);
            //Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(2,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(3,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(4,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(4,0)) && ip == 2);

        ip--;
//Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(5,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(6,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(7,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new Vector(7,0)) && ip == 2);
    }
}