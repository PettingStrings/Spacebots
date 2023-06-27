package it.unicam.cs.paduraru.engine.spacebots.api.environments.builder;

import it.unicam.cs.paduraru.engine.EnvironmentBuilder;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.Util;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
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
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands) throws Exception {

        PVector origin;
        ERobot bot;

        for(int i = 0; i < numBots; i++){
            origin = new PVector(Util.randInt(rangeX.getFirst(),rangeX.getSecond()),
                    Util.randInt(rangeY.getFirst(), rangeY.getSecond() ));

            bot = new ERobot(origin);

            environment.addEntity(bot);
            environment.addComponent(new cColliderRobot(bot, new PRectangle(10,10)));
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
