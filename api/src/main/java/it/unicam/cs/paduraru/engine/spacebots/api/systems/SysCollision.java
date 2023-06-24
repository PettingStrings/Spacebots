package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.Component;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.System;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Circle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SysCollision extends System{

    public enum CollisionType{ RECT_RECT, RECT_CIRCLE, CIRCLE_RECT, CIRCLE_CIRCLE }
    List<Pair<cCollider, cCollider>> lastColliding;

    public SysCollision(){
        lastColliding = new ArrayList<>();
    }
    @Override
    public void addComponents(List<Component> componentsToAdd) {
        this.components.addAll(componentsToAdd.stream().filter( component -> component instanceof cCollider).toList());
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
    public void addComponent(Component comp) {
        if(comp instanceof cCollider)
            this.components.add(comp);
    }
//region Collision Detection-Resolution
    private void resolveCollidingCollisions(List<Pair<cCollider, cCollider>> pairs) {
        pairs.forEach(pair -> pair.getFirst().OnColliding(pair.getSecond()));
    }

    private List<Pair<cCollider, cCollider>> getIntersectingColliderPairs() throws Exception {
        List<Pair<cCollider, cCollider>> toResolve = new ArrayList<>();

        for (Component comp1: components) {
            for (Component comp2: components) {
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
        if(pair.getFirst().getShape() instanceof Circle){

            if(pair.getSecond().getShape() instanceof Circle)
                return CollisionType.CIRCLE_CIRCLE;
            else if(pair.getSecond().getShape() instanceof Rectangle)
                return CollisionType.CIRCLE_RECT;

        }else if(pair.getFirst().getShape() instanceof Rectangle){
            if(pair.getSecond().getShape() instanceof Circle)
                return CollisionType.RECT_CIRCLE;
            else if(pair.getSecond().getShape() instanceof Rectangle)
                return CollisionType.RECT_RECT;
        }
        throw new Exception("Unknown shapes: " + pair.getFirst().toString() + " " + pair.getSecond().getClass().toString());
    }
    public static boolean collisionCircleToCircle(Pair<cCollider, cCollider> pair) {
        Point position1 = pair.getFirst().getPosition(), position2 = pair.getSecond().getPosition();
        int r1  = ((Circle)pair.getFirst().getShape()).getRadius(),
                r2 = ((Circle)pair.getSecond().getShape()).getRadius();

        return Math.abs(Math.pow(position1.getX() - position2.getX(), 2) +
                Math.pow(position1.getY() - position2.getY(), 2))
                < Math.pow(r1 + r2, 2);
    }

    public static boolean collisionCircleToRect(Pair<cCollider, cCollider> pair) {
        Circle circle = (Circle)pair.getFirst().getShape();
        Rectangle rect = (Rectangle)pair.getSecond().getShape();

        Point cPos = pair.getFirst().getPosition(), rPos = pair.getSecond().getPosition();

        Point distance = new Point(Math.abs(cPos.getX() - rPos.getX()), Math.abs(cPos.getY() - rPos.getY()));

        if (distance.getX() > (rect.getWidth()/2 + circle.getRadius())) { return false; }
        if (distance.getY() > (rect.getHeight()/2 + circle.getRadius())) { return false; }

        if (distance.getX() <= (rect.getWidth()/2)) { return true; }
        if (distance.getY() <= (rect.getHeight()/2)) { return true; }

        int cornerDist = (distance.getX() - rect.getWidth()/2)^2 +
                (distance.getY() - rect.getHeight()/2)^2;

        return (cornerDist <= (circle.getRadius()^2));
    }

    public static boolean collisionRectToCircle(Pair<cCollider, cCollider> pair) {
        return collisionCircleToRect(new Pair<>(pair.getSecond(), pair.getFirst()));
    }

    public static boolean collisionRectToRect(Pair<cCollider, cCollider> pair) {
        //AABB method
        Point position1 = pair.getFirst().getPosition(),
                position2 = pair.getSecond().getPosition();

        Rectangle rect1 = (Rectangle)pair.getFirst().getShape(),
                rect2 = (Rectangle)pair.getFirst().getShape();

        return  position1.getX() < position2.getX() + rect2.getWidth() &&
                position2.getX() + rect1.getWidth() > position2.getY() &&
                position1.getY() < position2.getY() + rect2.getHeight() &&
                position2.getY() + rect1.getHeight() > position2.getY();
    }
    //endregion
}
