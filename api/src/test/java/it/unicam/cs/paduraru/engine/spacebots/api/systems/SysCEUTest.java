package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SysCEUTest {

    @Test
    void run() {
        SysCEU sys = new SysCEU();
        PVector origin1 = new PVector(0,0), origin2 = new PVector(10,10);
        PRobot bot1 = new PRobot(origin1), bot2 = new PRobot(origin2);

        ArrayList<ArrayList<BotCommand>> programs = new ArrayList<>();

        programs.add(new ArrayList<>());
        programs.add(new ArrayList<>());

        programs.get(0).add(new Forever());
        programs.get(0).add(new Move(new PVector(1,0),1));
        programs.get(0).add(new Done(0));

        programs.get(1).add(new Forever());
        programs.get(1).add(new Move(new PVector(0,1),1));
        programs.get(1).add(new Done(0));

        PCEU ceu1 = new PCEU(bot1, programs.get(0)), ceu2 = new PCEU(bot2, programs.get(1));

        sys.addComponent(ceu1);
        sys.addComponent(ceu2);


        for (int i = 0; i < 100; i++) {

            assertEquals(0, ((PCEU)sys.getComponents().get(0)).getInstructionPointer());
            assertEquals(0, ((PCEU)sys.getComponents().get(1)).getInstructionPointer());

            sys.run();

            assertEquals(1, ((PCEU)sys.getComponents().get(0)).getInstructionPointer());
            assertEquals(1, ((PCEU)sys.getComponents().get(1)).getInstructionPointer());

            sys.run();

            assertTrue(bot1.getPosition().equals(new PVector(1+i,0)));
            assertEquals(2, ((PCEU)sys.getComponents().get(0)).getInstructionPointer());
            assertTrue(bot2.getPosition().equals(new PVector(10,11+i)));
            assertEquals(2, ((PCEU)sys.getComponents().get(1)).getInstructionPointer());

            sys.run();

        }

    }
}