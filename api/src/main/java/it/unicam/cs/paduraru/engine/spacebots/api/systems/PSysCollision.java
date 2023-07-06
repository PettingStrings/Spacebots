package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * Sistema che si occupa di rilevare le collisioni tra {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}.
 */
public class PSysCollision extends PSystem {

    public enum CollisionType{ RECT_RECT, RECT_CIRCLE, CIRCLE_RECT, CIRCLE_CIRCLE }

    /**
     * Lista contenente le coppie che sono entrate in collisione lo step precedente.
     */
    List<Pair<PCollider, PCollider>> lastColliding;

    public PSysCollision(){
        lastColliding = new ArrayList<>();
    }
    /**
     * Aggiunge solo elementi di una lista appartenenti a {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}.
     * @param components Lista contenente gli elementi da aggiungere.
     */
    @Override
    public void addComponents(List<PComponent> components) {
        getComponents().addAll(components.stream()
                .filter(component -> component instanceof PCollider)
                .toList());
    }

    /**
     * {@inheritDoc}
     * Esegue anche gli eventi dei {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}.
     */
    @Override
    public void run() {
        List<Pair<PCollider, PCollider>> currentColliding = getIntersectingColliderPairs();
        fireOnColliding(currentColliding);
        lastColliding.removeAll(currentColliding);
        fireOnExit(lastColliding);
        lastColliding = currentColliding;
    }

    /**
     * Genera una lista di tutti i {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}
     * che sono dentro un'area circolare.
     * @param origin Origine dell'area circolare.
     * @param radius Raggio dell'area circolare.
     * @return Lista dei componenti dentro l'area circolare.
     */
    public List<PCollider> checkInCircle(PVector origin, int radius){
        PEntity temp = new PEntity(origin);
        PColliderGeneric coll = new PColliderGeneric(temp, new PCircle(radius));

        return getComponents().stream()
                .map(component -> new Pair<PCollider, PCollider>(coll, (PCollider) component))
                .filter(PSysCollision::isColliding)
                .map(Pair::second)
                .toList();
    }

    /**
     * Aggiunge solo elementi appartenenti a {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}.
     * @param comp Elemento da aggiungere.
     */
    @Override
    public void addComponent(PComponent comp) {
        if(comp instanceof PCollider)
            getComponents().add(comp);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy() {
        PSysCollision sys = new PSysCollision();

        sys.setComponents(getComponents().stream()
                .map(com -> (PComponent)com.deepCopy()).collect(Collectors.toList()));

        sys.lastColliding = this.lastColliding.stream()
                .map(comp -> (Pair<PCollider, PCollider>)comp.deepCopy()).collect(Collectors.toList());

        return sys;
    }

    @Override
    public String convertToString() {
        return "PSysCollisione Not Implemented";
    }

    //region Collision Detection-Resolution
    /**
     * Esegue gli eventi di quando un componente sta collidendo.
     * @param pairs Coppie che sono in collisione.
     */
    private void fireOnColliding(List<Pair<PCollider, PCollider>> pairs) {
        pairs.forEach(pair -> pair.first().OnColliding(pair.second()));
    }

    /**
     * Esegue gli eventi di quando un componente è appena uscito da una collisione.
     * @param pairs Coppie che erano in collisione lo step precedente ma non in questo.
     */
    private void fireOnExit(List<Pair<PCollider, PCollider>> pairs) {
        pairs.forEach(pair -> pair.first().OnExit(pair.second()));
    }

    /**
     * @return Lista {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider} in collisione.
     */
    private List<Pair<PCollider, PCollider>> getIntersectingColliderPairs(){
        List<Pair<PCollider, PCollider>> toResolve = new ArrayList<>();

        for (PComponent comp1: getComponents()) {
            for (PComponent comp2: getComponents()) {
                if(comp1.equals(comp2)) continue;

                Pair<PCollider, PCollider> pair = new Pair<>((PCollider) comp1,(PCollider) comp2);

                if(isColliding(pair)) toResolve.add(pair);
            }
        }

        return toResolve;
    }

    /**
     * Controlla che la coppia di collider sia in collisione.
     * @param pair Coppia da controllare.
     * @return True se sono in collisione. Falso negli altri casi.
     */
    public static boolean isColliding(Pair<PCollider, PCollider> pair){
        boolean isColliding = false;
        switch (Objects.requireNonNull(getCollisionType(pair))){

            case RECT_RECT -> isColliding = PSysCollision.collisionRectToRect(pair);

            case RECT_CIRCLE -> isColliding = PSysCollision.collisionRectToCircle(pair);

            case CIRCLE_RECT -> isColliding = PSysCollision.collisionCircleToRect(pair);

            case CIRCLE_CIRCLE -> isColliding = PSysCollision.collisionCircleToCircle(pair);

        }
        return isColliding;
    }

    /**
     * Data una coppia di {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider}
     * genera il tipo di collisione tra quelli disponibili in CollisionType.
     * @param pair Coppia da controllare
     * @return Il tipo di collisione.
     */
    public static CollisionType getCollisionType(Pair<PCollider, PCollider> pair){
        if(pair.first().getShape() instanceof PCircle){

            if(pair.second().getShape() instanceof PCircle)
                return CollisionType.CIRCLE_CIRCLE;
            else if(pair.second().getShape() instanceof PRectangle)
                return CollisionType.CIRCLE_RECT;

        }else if(pair.first().getShape() instanceof PRectangle){
            if(pair.second().getShape() instanceof PCircle)
                return CollisionType.RECT_CIRCLE;
            else if(pair.second().getShape() instanceof PRectangle)
                return CollisionType.RECT_RECT;
        }
        //Null al posto di un eccezione
        return null;
    }

    public static boolean collisionCircleToCircle(Pair<PCollider, PCollider> pair) {
        PVector position1 = pair.first().getPosition(), position2 = pair.second().getPosition();
        double r1  = ((PCircle)pair.first().getShape()).getRadius(),
                r2 = ((PCircle)pair.second().getShape()).getRadius();

        return Math.abs(Math.pow(position1.getX() - position2.getX(), 2) +
                Math.pow(position1.getY() - position2.getY(), 2))
                < Math.pow(r1 + r2, 2);
    }

    public static boolean collisionCircleToRect(Pair<PCollider, PCollider> pair) {
        PCircle circle = (PCircle)pair.first().getShape();
        PRectangle rect = (PRectangle)pair.second().getShape();

        PVector cPos = pair.first().getPosition(), rPos = pair.second().getPosition();

        PVector distance = new PVector(Math.abs(cPos.getX() - rPos.getX()), Math.abs(cPos.getY() - rPos.getY()));

        if (distance.getX() > (rect.getWidth()/2 + circle.getRadius())) { return false; }
        if (distance.getY() > (rect.getHeight()/2 + circle.getRadius())) { return false; }

        if (distance.getX() <= (rect.getWidth()/2)) { return true; }
        if (distance.getY() <= (rect.getHeight()/2)) { return true; }

        double cornerDist = Math.pow(distance.getX() - rect.getWidth()/2,2) +
                Math.pow(distance.getY() - rect.getHeight()/2,2);

        return (cornerDist <= (Math.pow(circle.getRadius(), 2)));
    }

    public static boolean collisionRectToCircle(Pair<PCollider, PCollider> pair) {
        return collisionCircleToRect(new Pair<>(pair.second(), pair.first()));
    }

    public static boolean collisionRectToRect(Pair<PCollider, PCollider> pair) {
        //AABB method
        PVector position1 = pair.first().getPosition(),
                position2 = pair.second().getPosition();

        PRectangle rect1 = (PRectangle)pair.first().getShape(),
                rect2 = (PRectangle)pair.second().getShape();

        PVector centerDist = new PVector(Math.abs(position1.getX() - position2.getX()),
                Math.abs(position1.getY() - position2.getY()));

        return  (centerDist.getX() < (rect1.getWidth() + rect2.getWidth())/2 &&
                centerDist.getY() < (rect1.getHeight() + rect2.getHeight())/2);
    }
    //endregion
}
