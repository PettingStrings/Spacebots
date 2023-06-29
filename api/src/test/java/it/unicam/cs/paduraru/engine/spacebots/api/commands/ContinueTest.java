package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinueTest {

    @Test
    void execute() {
        int ip = 0, steps = 3;
        PRobot bot = new PRobot(new PVector(0,0));
        Move move =  new Move(new PVector(1,0), 1);
        BotCommand[] commands = new BotCommand[] { move, new Continue(steps) };
        //Execute Move
        ip = commands[ip].execute(bot,ip);
            //Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(2,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(3,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(4,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(4,0)) && ip == 2);

        ip--;
//Continue
        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(5,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(6,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(7,0)) && ip == 1);

        ip = commands[ip].execute(bot,ip);
        assertTrue(bot.getPosition().equals(new PVector(7,0)) && ip == 2);
    }
}