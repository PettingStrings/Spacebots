package it.unicam.cs.paduraru.engine;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GameController {

    public static Environment DefaultEnvironment;
    static List<Environment> environments = new ArrayList<Environment>();
    static int currentEnvironment;

    private GameController(){};
    public static void Initialize(){
        for (Environment env: environments)
            env.initialize();
    }

    public static void createEnvironment() {
        environments.add(new Environment());
    }
    public static void createEnvironment(Environment defaultEnvironment) {
        environments.add(defaultEnvironment);
    }


    public static void changeEnvironment(int idEnv) throws Exception {
        if(idEnv >= environments.stream().count())
            throw new Exception("Environment not exists");

        currentEnvironment = idEnv;

    }

    public static void StepCurrentEnvironment() {
    }

    public static List<Entity> GetCurrentEntities() {
        return environments.get(currentEnvironment).getEntities();
    }

    public static void addEnvironment(Environment environment) {
        environments.add(environment);
    }

    public static int getCurrentEnvironment() {
        return currentEnvironment;
    }

    public static void setCurrentEnvironment(int currentEnvironment) {
        GameController.currentEnvironment = currentEnvironment;
    }

    public static void runCurrentEnvironment() throws Exception {
        environments.get(currentEnvironment).run();
    }

    public static List<cCollider> checkInCircle(Vector origin, int radius) throws Exception {
        SysCollision sys =(SysCollision) environments.get(currentEnvironment).getSystems().stream().
                filter(system -> system instanceof SysCollision).collect(Collectors.toList()).get(0);
        if(sys == null) throw new Exception("Current environment has no SysCollision");
        return sys.checkInCircle(origin, radius);
    }
}
