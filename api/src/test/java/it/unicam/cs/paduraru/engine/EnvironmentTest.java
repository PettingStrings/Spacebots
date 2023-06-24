package it.unicam.cs.paduraru.engine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    private final class TestSystem extends System{

        @Override
        public void addComponents(List<Component> components) {
            for (Component comp: components) {
                this.addComponent(comp);
            }
        }

        @Override
        public void run() {

        }

        @Override
        public void addComponent(Component comp) {
            this.components.add(comp);
        }
    }

    Environment env = new Environment();
    @Test
    void initialize() {
    }

    @Test
    void getEntities() {
        env.clear();
        Entity temp = new Entity();
        env.addEntity(temp);
        assertTrue( env.getEntities().stream().count() == 1 && env.getEntities().contains(temp));
    }

    @Test
    void addEntity() {
        env.clear();
        Entity temp = new Entity();
        env.addEntity(temp);
        assertTrue(env.entities.stream().count() == 1);
    }

    @Test
    void addSystem() {
        env.clear();
        Entity tempEnt = new Entity();
        TestSystem tempSys = new TestSystem();
        Component tempComp = new Component(tempEnt);

        env.addSystem(tempSys);

        assertTrue(env.getSystems().stream().count() == 1 &&
                env.getSystems().contains(tempSys));

    }

    @Test
    void addComponent() {
    }
}