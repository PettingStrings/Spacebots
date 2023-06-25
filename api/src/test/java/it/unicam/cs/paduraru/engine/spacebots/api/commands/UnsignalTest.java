package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignalTest {
    @Test
    void execute() throws Exception {
        Label label = new Label("Ciao_");
        ERobot robot = new ERobot(new Point(1,1));
        robot.signal(label);
        Unsignal unsignal = new Unsignal(label);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);

        unsignal.execute(robot, 0);
        assertTrue(!robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 0);

    }

}