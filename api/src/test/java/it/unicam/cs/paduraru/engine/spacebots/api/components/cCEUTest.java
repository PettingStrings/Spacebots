package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class cCEUTest {

    @Test
    void executeNextLine() {
        ERobot robot = new ERobot(new Vector(0,0));
        ArrayList<BotCommand> commands = new ArrayList<>();

        commands.add(new Forever());
        commands.add(new Move(new Vector(1,0),1));
        commands.add(new Done(0));

        cCEU ceu = new cCEU(robot, commands);

        for (int i = 0; i < 100; i++) {
            assertEquals(0, ceu.instructionPointer);
            ceu.executeNextLine();
            assertEquals(1, ceu.instructionPointer);
            ceu.executeNextLine();
            assertTrue(robot.getPosition().equals(new Vector(1+i,0)));
            assertEquals(2, ceu.instructionPointer);
            ceu.executeNextLine();
        }
    }
}