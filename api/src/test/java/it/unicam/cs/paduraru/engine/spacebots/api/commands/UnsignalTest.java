package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignalTest {
    @Test
    void execute() throws Exception {
        PLabel label = new PLabel("Ciao_");
        PRobot robot = new PRobot(new PVector(1,1));
        robot.signal(label);
        Unsignal unsignal = new Unsignal(label);
        assertTrue(robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 1);

        unsignal.execute(robot, 0);
        assertTrue(!robot.getSignaledLabels().contains(label) && robot.getSignaledLabels().size() == 0);

    }

}