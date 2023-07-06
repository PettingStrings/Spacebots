package it.unicam.cs.paduraru.spacebots.app.environmentsParsers;

import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;
import it.unicam.cs.paduraru.engine.PEnvironment;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PAreaLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpaceBotsEnvironmentParser {
    private SpaceBotsEnvironmentBuilder envBuilder;
    private SpaceBotsEnvironmentHandler envHandler;
    private FollowMeParser parser;
    private List<BotCommand> program;

    public SpaceBotsEnvironmentParser(SpaceBotsEnvironmentHandler handle){
        envHandler = handle;
        envBuilder = new SpaceBotsEnvironmentBuilder();
        parser = new FollowMeParser(envHandler);
        program = new ArrayList<>();
    }
    //region Parse Shapes
    public void parseEnvironment(File file) throws FollowMeParserException, IOException {
        parser.parseEnvironment(file).stream()
                .map(SpaceBotsEnvironmentParser::convertShapeDataToAreaEntity)
                .forEach(envBuilder::addLabelledArea);
    }

    public static Pair<PAreaLabel, PShape> convertShapeDataToAreaEntity(ShapeData followShape){
        /*label CIRCLE x y r
            label RECTANGLE x y w h */
        PAreaLabel areaLabel = new PAreaLabel(new PVector(followShape.args()[0], followShape.args()[1]),
                new PLabel(followShape.label()));
        return new Pair<>(areaLabel,  shapeDataShapeToPShape(followShape));
    }

    public static PShape shapeDataShapeToPShape(ShapeData followShape){
        PShape shape = null;

        switch (followShape.shape()){
            case "CIRCLE" -> shape = shapeDataToPCircle(followShape);
            case "RECTANGLE" -> shape = shapeDataToPRect(followShape);
        }

        return shape;
    }

    private static PCircle shapeDataToPCircle(ShapeData followShape){
        return new PCircle(followShape.args()[2]);
    }

    private static PRectangle shapeDataToPRect(ShapeData followShape){
        return new PRectangle(followShape.args()[2], followShape.args()[3]);
    }
//endregion

    //region Parse Program

    public void parseProgram(File file) throws FollowMeParserException, IOException {
        parser.parseRobotProgram(file);
        program = envHandler.getProgram();
    }

    //endregion

    public PEnvironment getEnvironment(){
        return (PEnvironment) envBuilder.getEnvironment().deepCopy();
    }

    public List<BotCommand> getProgram(){
        return new ArrayList<>(program);
    }
}
