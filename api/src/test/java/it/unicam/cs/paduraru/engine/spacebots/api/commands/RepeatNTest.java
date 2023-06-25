package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatNTest {

    @Test
    void execute() {
        int ip = 0;
        ERobot robot = new ERobot(new Point(0,0));
        BotCommand[] commands = new BotCommand[]{
            new RepeatN(2), new Move(new Point(1,1),1), new Done(0)
        };
        ((RepeatN)commands[0]).setDoneIp(2);
        for (int i = 0; i < 100; i++) {

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 1);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 2);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 0);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 1);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 2);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 0);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 3);

            if (ip == 3) ip = 0;
        }
    }
}