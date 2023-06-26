package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UntilTest {

    @Test
    void execute() throws Exception {
        int ip = 0;
        Label condition = new Label("Harooo everynyan_"), fakeLabel = new Label("Oh My Gaaah!_");
        ERobot robot = new ERobot(new Vector(0,0));
        BotCommand[] commands = new BotCommand[]{
                new Until(condition), new Move(new Vector(1,1),1), new Done(0)
        };
        ((Until)commands[0]).setDoneIp(2);
        //No conditions on robot
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 1);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 2);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 0);
        //Fake label on robot
        robot.addLabel(fakeLabel);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 1);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 2);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 0);
        //Condition on robot
        robot.addLabel(condition);
        ip = commands[ip].execute(robot, ip);
        assertTrue(ip == 3);
    }
}