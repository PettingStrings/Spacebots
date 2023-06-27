package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalTest {

    @Test
    void execute() throws Exception {
        PLabel label = new PLabel("Ciao_");
        ERobot robot = new ERobot(new PVector(1,1));
        Signal signal = new Signal(label);
        assertTrue(robot.getSignaledLabels().size() == 0);
        signal.execute(robot, 0);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);
        signal.execute(robot, 0);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);
    }
}