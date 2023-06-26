package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalTest {

    @Test
    void execute() throws Exception {
        Label label = new Label("Ciao_");
        ERobot robot = new ERobot(new Vector(1,1));
        Signal signal = new Signal(label);
        assertTrue(robot.getSignaledLabels().size() == 0);
        signal.execute(robot, 0);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);
        signal.execute(robot, 0);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);
    }
}