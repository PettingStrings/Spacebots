package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.ASystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderGeneric;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SysCollision extends ASystem {

    public List<cCollider> checkInCircle(PVector origin, int radius) throws Exception {
        PEntity temp = new PEntity(origin);
        cColliderGeneric coll = new cColliderGeneric(temp, new PCircle(radius));
        List<Pair<cCollider, cCollider>> pairs =
                components.stream().map(component -> new Pair<cCollider,cCollider>(coll, (cCollider) component)).collect(Collectors.toList());

        List<cCollider> collidingColliders = new ArrayList<>();
        for (Pair<cCollider, cCollider> pair: pairs) {
            if(isColliding(pair))
                collidingColliders.add(pair.getSecond());
        }

        return collidingColliders;
    }

    public enum CollisionType{ RECT_RECT, RECT_CIRCLE, CIRCLE_RECT, CIRCLE_CIRCLE }
    List<Pair<cCollider, cCollider>> lastColliding;

    public SysCollision(){
        lastColliding = new ArrayList<>();
    }
    @Override
    public void addComponents(List<PComponent> componentsToAdd) {
        List<PComponent> newColliders = componentsToAdd.stream().filter(component -> component instanceof cCollider).toList();
        newColliders.forEach(component -> {component.setID(lastID.getAndAdd(1));});
        this.components.addAll(newColliders);
    }

    @Override
    public void run() throws Exception {
        List<Pair<cCollider, cCollider>> currentColliding = getIntersectingColliderPairs();
        resolveCollidingCollisions(currentColliding);
        lastColliding.removeAll(currentColliding);
        fireOnExit(lastColliding);
        lastColliding = currentColliding;
    }

    private void fireOnExit(List<Pair<cCollider, cCollider>> lastColliding) {
        lastColliding.forEach(pair -> pair.getFirst().OnExit(pair.getSecond()));
    }

    @Override
    public void addComponent(PComponent comp) {
        if(comp instanceof cCollider) {
            comp.setID(lastID.getAndAdd(1));
            this.components.add(comp);
        }
    }
//region Collision Detection-Resolution
    private void resolveCollidingCollisions(List<Pair<cCollider, cCollider>> pairs) {
        pairs.forEach(pair -> pair.getFirst().OnColliding(pair.getSecond()));
    }

    private List<Pair<cCollider, cCollider>> getIntersectingColliderPairs() throws Exception {
        List<Pair<cCollider, cCollider>> toResolve = new ArrayList<>();

        for (PComponent comp1: components) {
            for (PComponent comp2: components) {
                if(comp1.equals(comp2)) continue;

                Pair<cCollider, cCollider> pair = new Pair<>((cCollider) comp1,(cCollider) comp2);

                if(isColliding(pair)) toResolve.add(pair);
            }
        }

        return toResolve;
    }

    public static boolean isColliding(Pair<cCollider, cCollider> pair) throws Exception {
        boolean isColliding = false;
        switch (getCollisionType(pair)){

            case RECT_RECT -> isColliding = SysCollision.collisionRectToRect(pair);

            case RECT_CIRCLE -> isColliding = SysCollision.collisionRectToCircle(pair);

            case CIRCLE_RECT -> isColliding = SysCollision.collisionCircleToRect(pair);

            case CIRCLE_CIRCLE -> isColliding = SysCollision.collisionCircleToCircle(pair);

        }
        return isColliding;
    }
    public static CollisionType getCollisionType(Pair<cCollider, cCollider> pair) throws Exception {
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
    public static boolean collisionCircleToCircle(Pair<cCollider, cCollider> pair) {
        PVector position1 = pair.getFirst().getPosition(), position2 = pair.getSecond().getPosition();
        int r1  = ((PCircle)pair.getFirst().getShape()).getRadius(),
                r2 = ((PCircle)pair.getSecond().getShape()).getRadius();

        return Math.abs(Math.pow(position1.getX() - position2.getX(), 2) +
                Math.pow(position1.getY() - position2.getY(), 2))
                < Math.pow(r1 + r2, 2);
    }

    public static boolean collisionCircleToRect(Pair<cCollider, cCollider> pair) {
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

    public static boolean collisionRectToCircle(Pair<cCollider, cCollider> pair) {
        return collisionCircleToRect(new Pair<>(pair.getSecond(), pair.getFirst()));
    }

    public static boolean collisionRectToRect(Pair<cCollider, cCollider> pair) {
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
