package it.unicam.cs.paduraru.engine;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GameController {

    public static PEnvironment DefaultEnvironment;
    static List<PEnvironment> environments = new ArrayList<>();
    static List<List<PEnvironment>> history = new ArrayList<>();
    static int currentEnvironment;

    private GameController(){};
    public static void Initialize(){
        for (PEnvironment env: environments)
            env.initialize();
    }

    public static void createEnvironment() {
        environments.add(new PEnvironment());
    }
    public static void createEnvironment(PEnvironment defaultEnvironment) {
        environments.add(defaultEnvironment);
    }


    public static void changeEnvironment(int idEnv) throws Exception {
        if(idEnv >= environments.stream().count())
            throw new Exception("Environment not exists");

        currentEnvironment = idEnv;

    }

    public static void stepForwardCurrentEnvironment() throws Exception {
        history.get(currentEnvironment).add((PEnvironment)environments.get(currentEnvironment).deepCopy());
        environments.get(currentEnvironment).run();
    }

    public static List<PEntity> GetCurrentEntities() {
        return environments.get(currentEnvironment).getEntities();
    }

    public static void addEnvironment(PEnvironment environment) {
        environments.add(environment);
        history.add(new ArrayList<>());
    }
    public static PEnvironment getEnvironment(int i){
        return environments.get(i);
    }

    public static int getCurrentEnvironment() {
        return currentEnvironment;
    }

    public static void setCurrentEnvironment(int currentEnvironment) {
        GameController.currentEnvironment = currentEnvironment;
    }

    public static void runCurrentEnvironment() throws Exception {
        history.get(currentEnvironment).add((PEnvironment)environments.get(currentEnvironment).deepCopy());
        environments.get(currentEnvironment).run();
    }

    public static List<PCollider> checkInCircle(PVector origin, int radius) throws Exception {
        SysCollision sys = (SysCollision) environments.get(currentEnvironment).getSystems().stream().
                filter(system -> system instanceof SysCollision).collect(Collectors.toList()).get(0);
        if(sys == null) throw new Exception("Current environment has no SysCollision");

        PEnvironment env = environments.get(currentEnvironment);

        PEntity tempEnt = new PEntity(origin);
        PColliderGeneric tempColl = new PColliderGeneric(tempEnt, new PCircle(radius));
        env.addEntity(tempEnt);

        List<Pair<PCollider, PCollider>> pairs =
                env.components.stream().map(component ->
                        new Pair<PCollider, PCollider>(tempColl, (PCollider) component)).collect(Collectors.toList());
        //sys.isColliding lancia un eccezione, quindi non posso usare le stream
        List<PCollider> collidingColliders = new ArrayList<>();
        for (Pair<PCollider, PCollider> pair: pairs) {
            if(sys.isColliding(pair))
                collidingColliders.add(pair.getSecond());
        }

        return collidingColliders;
    }

    public static void stepBackCurrentEnvironment() {
        environments.set(currentEnvironment, history.get(currentEnvironment).remove(history.get(currentEnvironment).size()-1));
    }
}
