package it.unicam.cs.paduraru.engine.spacebots.api.environments;

import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpaceBotsPEnvironmentBuilderTest {

    @Test
    void createSwarm() throws Exception {
        SpaceBotsEnvironmentBuilder builder = new SpaceBotsEnvironmentBuilder();

        try {
            builder.createSwarm(new Pair<>(-10.0,10.0), new Pair<>(-10.0, 10.0), 50, new ArrayList<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        builder.finalizeEnvironment();

        assertTrue(builder.getEnvironment().getEntities().stream().count() == 50);

    }
}