package it.unicam.cs.paduraru.spacebots.app.console;

import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.environments.builder.SpaceBotsEnvironmentBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void LogLn(String msg){
        System.out.println(">"+msg);
    }
    public static void main(String[] args) {
        System.out.println("==== Console App =====");
        System.out.println("====    START    =====\n");

        Pair<Integer,Integer> rangeX = new Pair<>(-50,50), rangeY = new Pair<>(-50,50) ;
        List<BotCommand> commands = new ArrayList<>();

        SpaceBotsEnvironmentBuilder envBuilder = new SpaceBotsEnvironmentBuilder();

        try {
            envBuilder.createSwarm(rangeX, rangeY,50,commands);
        } catch (Exception e) {
            LogLn(e.getMessage());
            System.exit(1);
        }

        envBuilder.finalizeEnvironment();

        GameController.addEnvironment(envBuilder.getEnvironment());

        try {
            GameController.runCurrentEnvironment();
        } catch (Exception e) {
            LogLn("Error running environment");
            System.exit(1);
        }

        System.out.println("\n====    END   =====\n");
    }

}
