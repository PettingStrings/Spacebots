package it.unicam.cs.paduraru.engine;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.PSysCollision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gestisce gli environment
 */
public final class GameController {
    static List<PEnvironment> environments = new ArrayList<>();
    static List<List<PEnvironment>> history = new ArrayList<>();
    static int currentEnvironment;

    /**
     * Esegue un passo di simulazione
     */
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

    /**
     * @param origin Centro dell' area da scansionare
     * @param shape Forma dell' area da scansionare
     * @return Lista dei collider trovati. Se l' environment non ha un PSYsCollision viene data una lista vuota.  
     */
    public static List<PCollider> scanInShapeArea(PVector origin, PShape shape){
        PSysCollision sys = (PSysCollision) environments.get(currentEnvironment)
                .getSystems().stream().
                filter(system -> system instanceof PSysCollision)
                .toList().get(0);

        if(sys == null) return new ArrayList<>();

        PEnvironment curEnv = environments.get(currentEnvironment);

        PEntity tempEnt = new PEntity(origin);
        PColliderGeneric tempColl = new PColliderGeneric(tempEnt, shape);

        return curEnv.components.stream()
                .filter(component -> component instanceof PCollider)
                .map(component -> new Pair<PCollider, PCollider>(tempColl, (PCollider) component))
                .filter(PSysCollision::isColliding)
                .map(Pair::second)
                .collect(Collectors.toList());
    }

    /**
     * Scannerizza l'area con una forma circolare.
     * @param origin Centro dell' area da scansionare
     * @param radius Raggio del cerchio.
     * @return Lista dei collider trovati. Se l' environment non ha un PSYsCollision viene data una lista vuota.
     */
    public static List<PCollider> scanInRoundArea(PVector origin, double radius){
        return scanInShapeArea(origin, new PCircle(radius));
    }

    /**
     * Torna indietro di un passo nella simulazione
     */
    public static void stepBackCurrentEnvironment() {
        environments.set(currentEnvironment, history.get(currentEnvironment).remove(history.get(currentEnvironment).size()-1));
    }

    public static void setCurrentEnvironment(PEnvironment environment) {
        environments.set(currentEnvironment, environment);
    }

    /**
     * Va avanti di stepNum passi nella simulazione.
     * @param stepNum Numero di passi da fare
     */
    public static void stepForwardCurrentEnvironment(int stepNum) {
        for (int i = 0; i < stepNum; i++) {
            stepForwardCurrentEnvironment();
        }
    }

    /**
     * Va indietro di stepNum passi.
     * @param stepNum Numero di passi da fare.
     */
    public static void stepBackCurrentEnvironment(int stepNum) {
        for (int i = 0; i < stepNum; i++) {
            stepBackCurrentEnvironment();
        }
    }
}
