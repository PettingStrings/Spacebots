package it.unicam.cs.paduraru.engine.spacebots.api.environments.builder;

import it.unicam.cs.paduraru.engine.EnvironmentBuilder;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.Util;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderWorkingArea;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.List;

public class SpaceBotsEnvironmentBuilder extends EnvironmentBuilder {
    public SpaceBotsEnvironmentBuilder(){
        super();
        addSystem(new SysCEU());
        addSystem(new SysCollision());
    }
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands, PShape shape){
        PVector origin;
        PRobot bot;

        for(int i = 0; i < numBots; i++){
            origin = new PVector(Util.randInt(rangeX.getFirst(),rangeX.getSecond()),
                    Util.randInt(rangeY.getFirst(), rangeY.getSecond() ));

            bot = new PRobot(origin);

            addEntity(bot);
            addComponent(new PColliderRobot(bot, shape));
            addComponent(new PCEU(bot, commands));
        }
    }
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands){
        createSwarm(rangeX,rangeY,numBots,commands,new PCircle(5));
    }
    public void addLabelledArea(PAreaLabel area, PShape shape) {
        addEntity(area);
        addComponent(new PColliderWorkingArea(area, shape));
    }
}
