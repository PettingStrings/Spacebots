package it.unicam.cs.paduraru.spacebots.app.console;

import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Done;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Forever;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.Move;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("==== Console App =====");
        System.out.println("====    START    =====\n");

        Pair<Double,Double> rangeX = new Pair<>(-50.0,50.0), rangeY = new Pair<>(-50.0,50.0) ;
        List<BotCommand> commands = new ArrayList<>();

        commands.add(new Forever());
        commands.add(new Move(new PVector(1,1), 5));
        commands.add(new Done(0));

        SpaceBotsEnvironmentBuilder envBuilder = new SpaceBotsEnvironmentBuilder();

        try {
            envBuilder.createSwarm(rangeX, rangeY,5,commands);
            //envBuilder.addLabelledArea(new ELabelledArea());
        } catch (Exception e) {
            LogLn(e.getMessage());
            System.exit(1);
        }

        envBuilder.addSystem(new SysCollision());
        envBuilder.addSystem(new SysCEU());

        GameController.addEnvironment(envBuilder.getEnvironment());

        for (int i = 0; i < 100; i++) {

            LogLn(String.format("Iteration %d:", i));
            LogEntitiesLocations(envBuilder.getEnvironment().getEntities());

            try {
                GameController.runCurrentEnvironment();
            } catch (Exception e) {
                LogLn("Error running environment");
                System.exit(1);
            }
        }

        System.out.println("\n====    END   =====\n");
    }

    public static void LogLn(String msg){
        System.out.println(">"+msg);
    }

    private static void LogEntitiesLocations(List<PEntity> entities) {
        entities.forEach(entity ->
                System.out.println(entity.getClass() +
                        "-> x:" + entity.getPosition().getX() +
                        " y:" + entity.getPosition().getX()));
    }

}
