package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PEnvironmentTest {

    private final class TestASystem extends PSystem {

        @Override
        public void addComponents(List<PComponent> components) {
            for (PComponent comp: components) {
                this.addComponent(comp);
            }
        }

        @Override
        public void run() {

        }

        @Override
        public void addComponent(PComponent comp) {
            this.components.add(comp);
        }

        /*Unused*/
        @Override
        public Object deepCopy() {
            return new TestASystem();
        }
    }

    PEnvironment env = new PEnvironment();
    @Test
    void initialize() {
    }

    @Test
    void getEntities() {
        env.clear();
        PEntity temp = new PEntity();
        env.addEntity(temp);
        assertTrue( env.getEntities().stream().count() == 1 && env.getEntities().contains(temp));
    }

    @Test
    void addEntity() {
        env.clear();
        PEntity temp = new PEntity();
        env.addEntity(temp);
        assertTrue(env.entities.stream().count() == 1);
    }

    @Test
    void addSystem() {
        env.clear();
        PEntity tempEnt = new PEntity();
        TestASystem tempSys = new TestASystem();
        PComponent tempComp = new PComponent(tempEnt);

        env.addSystem(tempSys);

        assertTrue(env.getSystems().stream().count() == 1 &&
                env.getSystems().contains(tempSys));

    }

    @Test
    void addComponent() {
    }

    @Test
    void test_DeepCopy(){
        PEnvironment env = new PEnvironment();
        ERobot robot = new ERobot(new PVector(0,0));
        cColliderRobot coll = new cColliderRobot(robot, new PCircle(5));

        env.addEntity(robot);
        env.addComponent(coll);

        PEnvironment copy = (PEnvironment) env.deepCopy();
    }
}