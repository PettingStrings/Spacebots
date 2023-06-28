package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SysCollision extends PSystem {

    public enum CollisionType{ RECT_RECT, RECT_CIRCLE, CIRCLE_RECT, CIRCLE_CIRCLE }
    List<Pair<PCollider, PCollider>> lastColliding;

    public SysCollision(){
        lastColliding = new ArrayList<>();
    }
    @Override
    public void addComponents(List<PComponent> componentsToAdd) {
        List<PComponent> newColliders = componentsToAdd.stream().filter(component -> component instanceof PCollider).toList();
        components.addAll(newColliders);
    }

    @Override
    public void run() throws Exception {
        List<Pair<PCollider, PCollider>> currentColliding = getIntersectingColliderPairs();
        resolveCollidingCollisions(currentColliding);
        lastColliding.removeAll(currentColliding);
        fireOnExit(lastColliding);
        lastColliding = currentColliding;
    }

    private void fireOnExit(List<Pair<PCollider, PCollider>> lastColliding) {
        lastColliding.forEach(pair -> pair.getFirst().OnExit(pair.getSecond()));
    }

    public List<PCollider> checkInCircle(PVector origin, int radius) throws Exception {
        PEntity temp = new PEntity(origin);
        PColliderGeneric coll = new PColliderGeneric(temp, new PCircle(radius));
        List<Pair<PCollider, PCollider>> pairs =
                components.stream().map(component -> new Pair<PCollider, PCollider>(coll, (PCollider) component)).collect(Collectors.toList());

        List<PCollider> collidingColliders = new ArrayList<>();
        for (Pair<PCollider, PCollider> pair: pairs) {
            if(isColliding(pair))
                collidingColliders.add(pair.getSecond());
        }

        return collidingColliders;
    }

    @Override
    public void addComponent(PComponent comp) {
        if(comp instanceof PCollider)
            this.components.add(comp);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy() {
        SysCollision sys = new SysCollision();

        sys.components = this.components.stream()
                .map(com -> (PComponent)com.deepCopy()).collect(Collectors.toList());

        sys.lastColliding = this.lastColliding.stream()
                .map(comp -> (Pair<PCollider, PCollider>)comp.deepCopy()).collect(Collectors.toList());

        return sys;
    }

//region Collision Detection-Resolution
    private void resolveCollidingCollisions(List<Pair<PCollider, PCollider>> pairs) {
        pairs.forEach(pair -> pair.getFirst().OnColliding(pair.getSecond()));
    }

    private List<Pair<PCollider, PCollider>> getIntersectingColliderPairs() throws Exception {
        List<Pair<PCollider, PCollider>> toResolve = new ArrayList<>();

        for (PComponent comp1: components) {
            for (PComponent comp2: components) {
                if(comp1.equals(comp2)) continue;

                Pair<PCollider, PCollider> pair = new Pair<>((PCollider) comp1,(PCollider) comp2);

                if(isColliding(pair)) toResolve.add(pair);
            }
        }

        return toResolve;
    }

    public static boolean isColliding(Pair<PCollider, PCollider> pair) throws Exception {
        boolean isColliding = false;
        switch (getCollisionType(pair)){

            case RECT_RECT -> isColliding = SysCollision.collisionRectToRect(pair);

            case RECT_CIRCLE -> isColliding = SysCollision.collisionRectToCircle(pair);

            case CIRCLE_RECT -> isColliding = SysCollision.collisionCircleToRect(pair);

            case CIRCLE_CIRCLE -> isColliding = SysCollision.collisionCircleToCircle(pair);

        }
        return isColliding;
    }
    public static CollisionType getCollisionType(Pair<PCollider, PCollider> pair) throws Exception {
        if(pair.getFirst().getShape() instanceof PCircle){

            if(pair.getSecond().getShape() instanceof PCircle)
                return CollisionType.CIRCLE_CIRCLE;
            else if(pair.getSecond().getShape() instanceof PRectangle)
                return CollisionType.CIRCLE_RECT;

        }else if(pair.getFirst().getShape() instanceof PRectangle){
            if(pair.getSecond().getShape() instanceof PCircle)
                return CollisionType.RECT_CIRCLE;
            else if(pair.getSecond().getShape() instanceof PRectangle)
                return CollisionType.RECT_RECT;
        }
        throw new Exception("Unknown shapes: " + pair.getFirst().toString() + " " + pair.getSecond().getClass().toString());
    }
    public static boolean collisionCircleToCircle(Pair<PCollider, PCollider> pair) {
        PVector position1 = pair.getFirst().getPosition(), position2 = pair.getSecond().getPosition();
        int r1  = ((PCircle)pair.getFirst().getShape()).getRadius(),
                r2 = ((PCircle)pair.getSecond().getShape()).getRadius();

        return Math.abs(Math.pow(position1.getX() - position2.getX(), 2) +
                Math.pow(position1.getY() - position2.getY(), 2))
                < Math.pow(r1 + r2, 2);
    }

    public static boolean collisionCircleToRect(Pair<PCollider, PCollider> pair) {
        PCircle circle = (PCircle)pair.getFirst().getShape();
        PRectangle rect = (PRectangle)pair.getSecond().getShape();

        PVector cPos = pair.getFirst().getPosition(), rPos = pair.getSecond().getPosition();

        PVector distance = new PVector(Math.abs(cPos.getX() - rPos.getX()), Math.abs(cPos.getY() - rPos.getY()));

        if (distance.getX() > (rect.getWidth()/2 + circle.getRadius())) { return false; }
        if (distance.getY() > (rect.getHeight()/2 + circle.getRadius())) { return false; }

        if (distance.getX() <= (rect.getWidth()/2)) { return true; }
        if (distance.getY() <= (rect.getHeight()/2)) { return true; }

        double cornerDist = Math.pow(distance.getX() - rect.getWidth()/2,2) +
                Math.pow(distance.getY() - rect.getHeight()/2,2);

        return (cornerDist <= (circle.getRadius()^2));
    }

    public static boolean collisionRectToCircle(Pair<PCollider, PCollider> pair) {
        return collisionCircleToRect(new Pair<>(pair.getSecond(), pair.getFirst()));
    }

    public static boolean collisionRectToRect(Pair<PCollider, PCollider> pair) {
        //AABB method
        PVector position1 = pair.getFirst().getPosition(),
                position2 = pair.getSecond().getPosition();

        PRectangle rect1 = (PRectangle)pair.getFirst().getShape(),
                rect2 = (PRectangle)pair.getFirst().getShape();

        return  position1.getX() < position2.getX() + rect2.getWidth() &&
                position2.getX() + rect1.getWidth() > position2.getY() &&
                position1.getY() < position2.getY() + rect2.getHeight() &&
                position2.getY() + rect1.getHeight() > position2.getY();
    }
    //endregion
}
