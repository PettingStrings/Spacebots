package it.unicam.cs.paduraru.engine;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.PSysCollision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GameController {
    static List<PEnvironment> environments = new ArrayList<>();
    static List<List<PEnvironment>> history = new ArrayList<>();
    static int currentEnvironment;

    public static void stepForwardCurrentEnvironment(){
        history.get(currentEnvironment).add((PEnvironment)environments.get(currentEnvironment).deepCopy());
        environments.get(currentEnvironment).run();
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

    public static void runCurrentEnvironment(){
        history.get(currentEnvironment).add((PEnvironment)environments.get(currentEnvironment).deepCopy());
        environments.get(currentEnvironment).run();
    }

    public static List<PCollider> scanInShapeArea(PVector origin, PShape shape){
        PSysCollision sys = (PSysCollision) environments.get(currentEnvironment)
                .getSystems().stream().
                filter(system -> system instanceof PSysCollision)
                .toList().get(0);

        if(sys == null) return new ArrayList<>();

        PEnvironment curEnv = environments.get(currentEnvironment);

        PEntity tempEnt = new PEntity(origin);
        PColliderGeneric tempColl = new PColliderGeneric(tempEnt, shape);

        return curEnv.components.stream().map(component ->
                        new Pair<PCollider, PCollider>(tempColl, (PCollider) component))
                .filter(PSysCollision::isColliding)
                .map(Pair::second)
                .collect(Collectors.toList());
    }
    public static List<PCollider> scanInRoundArea(PVector origin, int radius){
        return scanInShapeArea(origin, new PCircle(radius));
    }

    public static void stepBackCurrentEnvironment() {
        environments.set(currentEnvironment, history.get(currentEnvironment).remove(history.get(currentEnvironment).size()-1));
    }
}
