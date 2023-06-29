package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PCEUTest {

    @Test
    void executeNextLine() {
        PRobot robot = new PRobot(new PVector(0,0));
        ArrayList<BotCommand> commands = new ArrayList<>();

        commands.add(new Forever());
        commands.add(new Move(new PVector(1,0),1));
        commands.add(new Done(0));

        PCEU ceu = new PCEU(robot, commands);

        for (int i = 0; i < 100; i++) {
            assertEquals(0, ceu.getInstructionPointer());
            ceu.executeNextLine();
            assertEquals(1, ceu.getInstructionPointer());
            ceu.executeNextLine();
            assertTrue(robot.getPosition().equals(new PVector(1+i,0)));
            assertEquals(2, ceu.getInstructionPointer());
            ceu.executeNextLine();
        }
    }
}