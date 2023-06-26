package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForeverTest {

    @Test
    void execute() {
        int ip = 0, iterations = 10;
        ERobot robot = new ERobot(new Vector(0,0));
        BotCommand[] commands = new BotCommand[]{
            new Forever(), new Move(new Vector(1,1),1), new Move(new Vector(1,1),1), new Done(0)
        };
        for (int i = 0; i < 100; i++) {
            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 1);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 2);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 3);

            ip = commands[ip].execute(robot, ip);
            assertTrue(ip == 0);
        }

    }
}