package it.unicam.cs.paduraru.engine.spacebots.api.environments.builder;

import it.unicam.cs.paduraru.engine.EnvironmentBuilder;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.Vector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.Util;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Rectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.List;

public class SpaceBotsEnvironmentBuilder extends EnvironmentBuilder {
    private boolean isFinalized;
    public SpaceBotsEnvironmentBuilder(){
        super();
        isFinalized = false;
        environment.addSystem(new SysCollision());
        environment.addSystem(new SysCEU());
    }
    public void createSwarm(Pair<Integer, Integer> rangeX, Pair<Integer, Integer> rangeY, int numBots, List<BotCommand> commands) throws Exception {

        Vector origin;
        ERobot bot;

        for(int i = 0; i < numBots; i++){
            origin = new Vector(Util.randInt(rangeX.getFirst(),rangeX.getSecond()),
                    Util.randInt(rangeY.getFirst(), rangeY.getSecond() ));

            bot = new ERobot(origin);

            environment.addEntity(bot);
            environment.addComponent(new cColliderRobot(bot, new Rectangle(10,10)));
            environment.addComponent(new cCEU(bot, commands));
        }

    }

    public void addLabelledArea(ELabelledArea area) throws Exception {
        isFinalized();
        environment.addEntity(area);
    }

    private void isFinalized() throws Exception {
        if(isFinalized) throw new Exception("Cannot modify finalized environment");
    }

    public void finalizeEnvironment() {
        if(isFinalized) return;

        isFinalized = true;
        environment.initialize();
    }
}
