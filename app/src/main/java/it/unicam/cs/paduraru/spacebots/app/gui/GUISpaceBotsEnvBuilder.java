package it.unicam.cs.paduraru.spacebots.app.gui;

import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

import java.util.ArrayList;
import java.util.List;

public class GUISpaceBotsEnvBuilder extends SpaceBotsEnvironmentBuilder{
    private ArrayList<GUIEntity> guiEntities;

    public GUISpaceBotsEnvBuilder(){
        super();
        guiEntities = new ArrayList<>();
    }

    @Override
    public void createSwarm(Pair<Double, Double> rangeX, Pair<Double, Double> rangeY, int numBots, List<BotCommand> commands) throws Exception {
        super.createSwarm(rangeX, rangeY, numBots, commands);
        for (var ent: environment.getEntities()) {
            PShape shape = ((PColliderRobot)environment.getComponentOf(ent, PColliderRobot.class).get(0)).getShape();
            guiEntities.add(new GUIEntity(ent, GUIAppUtil.convertToFXShape(shape)));
        }
    }

    public ArrayList<GUIEntity> getGuiEntities() {
        return guiEntities;
    }

    public void setGuiEntities(ArrayList<GUIEntity> guiEntities) {
        this.guiEntities = guiEntities;
    }

}
