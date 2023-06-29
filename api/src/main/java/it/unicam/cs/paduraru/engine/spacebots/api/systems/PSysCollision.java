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

public class PSysCollision extends PSystem {

    public enum CollisionType{ RECT_RECT, RECT_CIRCLE, CIRCLE_RECT, CIRCLE_CIRCLE }
    List<Pair<PCollider, PCollider>> lastColliding;

    public PSysCollision(){
        lastColliding = new ArrayList<>();
    }
    @Override
    public void addComponents(List<PComponent> componentsToAdd) {
        getComponents().addAll(componentsToAdd.stream()
                .filter(component -> component instanceof PCollider)
                .toList());
    }

    @Override
    public void run() {
        List<Pair<PCollider, PCollider>> currentColliding = getIntersectingColliderPairs();
        fireOnColliding(currentColliding);
        lastColliding.removeAll(currentColliding);
        fireOnExit(lastColliding);
        lastColliding = currentColliding;
    }

    private void fireOnExit(List<Pair<PCollider, PCollider>> lastColliding) {
        lastColliding.forEach(pair -> pair.first().OnExit(pair.second()));
    }

    public List<PCollider> checkInCircle(PVector origin, int radius){
        PEntity temp = new PEntity(origin);
        PColliderGeneric coll = new PColliderGeneric(temp, new PCircle(radius));

        return getComponents().stream()
                .map(component -> new Pair<PCollider, PCollider>(coll, (PCollider) component))
                .filter(PSysCollision::isColliding)
                .map(Pair::second)
                .toList();
    }

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

//region Collision Detection-Resolution
    private void fireOnColliding(List<Pair<PCollider, PCollider>> pairs) {
        pairs.forEach(pair -> pair.first().OnColliding(pair.second()));
    }

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
                rect2 = (PRectangle)pair.first().getShape();

        return  position1.getX() < position2.getX() + rect2.getWidth() &&
                position2.getX() + rect1.getWidth() > position2.getY() &&
                position1.getY() < position2.getY() + rect2.getHeight() &&
                position2.getY() + rect1.getHeight() > position2.getY();
    }
    //endregion
}
