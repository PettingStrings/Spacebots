package it.unicam.cs.paduraru.engine.spacebots.api.environments.builder;

import it.unicam.cs.paduraru.engine.EnvironmentBuilder;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.Util;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderWorkingArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ELabelledArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.List;

public class SpaceBotsEnvironmentBuilder extends EnvironmentBuilder {
    public SpaceBotsEnvironmentBuilder(){
        super();
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
            environment.addComponent(new PColliderRobot(bot, new PCircle(5)));
            environment.addComponent(new PCEU(bot, commands));
        }

    }

    public void addLabelledArea(ELabelledArea area, PShape shape) {
        environment.addEntity(area);
        environment.addComponent(new PColliderWorkingArea(area, shape));
    }


    public void finalizeEnvironment() {
    }


}
