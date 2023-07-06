package it.unicam.cs.paduraru.engine.spacebots.api.environments.builder;

import it.unicam.cs.paduraru.engine.EnvironmentBuilder;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.APIUtil;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.PSysCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.PSysCollision;

import java.util.List;

/**
 * {@inheritDoc}
 * Aggiugne metodi utili per creare un Environment per la simulazione dei robot.
 */
public class SpaceBotsEnvironmentBuilder extends EnvironmentBuilder{
    public SpaceBotsEnvironmentBuilder(){
        super();
        addSystem(new PSysCEU());
        addSystem(new PSysCollision());
    }

    /**
     * Crea uno sciame di robot in posizioni randomiche.
     * Tutto lo sciame avrà lo stesso programma.
     * @param rangeX Range di valori della coordinata X.
     * @param rangeY Range di valori della coordinata Y.
     * @param numBots Numero di robot da generare.
     * @param commands Programma che avrà ogni robot dello sciame.
     * @param shape Forma che avra ogni robot dello sciame.
     */
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands, PShape shape){
        PVector origin;
        PRobot bot;

        for(int i = 0; i < numBots; i++){
            origin = new PVector(APIUtil.randDouble(rangeX.first(),rangeX.second()),
                    APIUtil.randDouble(rangeY.first(), rangeY.second() ));

            bot = new PRobot(origin);

            addEntity(bot);
            addComponent(new PColliderRobot(bot, shape));
            addComponent(new PCEU(bot, commands));
        }
    }

    /**
     *  Crea uno sciame di robot in posizioni randomiche.
     *  Tutto lo sciame avrà lo stesso programma.
     *  In questo overloading la forma di Defualt è un PCircle largo 5.
     * @param rangeX Range di valori della coordinata X.
     * @param rangeY Range di valori della coordinata Y.
     * @param numBots Numero di robot da generare.
     * @param commands Programma che avrà ogni robot dello sciame.
     */
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands){
        createSwarm(rangeX,rangeY,numBots,commands,new PCircle(5));
    }

    public void addLabelledArea(PAreaLabel area, PShape shape) {
        addEntity(area);
        addComponent(new PColliderAreaLabel(area, shape));
    }
    public void addLabelledArea(Pair<PAreaLabel, PShape> lArea) {
        addLabelledArea(lArea.first(), lArea.second());
    }
}
