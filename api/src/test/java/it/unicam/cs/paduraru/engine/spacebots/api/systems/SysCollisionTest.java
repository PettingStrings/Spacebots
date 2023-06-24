package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.System;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.eRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Circle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Rectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SysCollisionTest {

    eRobot parent = new eRobot(new Point(0,0));
    Circle circle1 = new Circle(10), circle2 = new Circle(20);
    cCollider parentCollider = new cColliderRobot(parent, null);

    //region Overlapping circles
    eRobot ovCircle1 = new eRobot(new Point(0,0)), ovCircle2 = new eRobot(new Point(5,5));
    Circle ovCircleShape1 = new Circle(10), ovCircleShape2 = new Circle(5);
    cColliderRobot ovCircleCollider1 = new cColliderRobot(ovCircle1,ovCircleShape1),
            ovCircleCollider2 = new cColliderRobot(ovCircle2,ovCircleShape2);
    Pair<cCollider,cCollider> ovCirclesPair = new Pair<>(ovCircleCollider1,ovCircleCollider2);
    //endregion

    //region Overlapping rects
    eRobot ovRect1 = new eRobot(new Point(0,0)),ovRect2 = new eRobot(new Point(5,5));
    Rectangle ovRectShape1 = new Rectangle(5,5), ovRectShape2 = new Rectangle(7,7);
    cColliderRobot ovRectCollider1 = new cColliderRobot(ovRect1,ovRectShape1),
            ovRectCollider2 = new cColliderRobot(ovRect2,ovRectShape2);
    Pair<cCollider,cCollider> ovRectPair = new Pair<>(ovRectCollider1,ovRectCollider2);
    //endregion

    //region Non overlapping circles
    eRobot novCircle1 = new eRobot(new Point(0,0)), novCircle2 = new eRobot(new Point(20,20));
    Circle novCircleShape1 = new Circle(10), novCircleShape2 = new Circle(5);
    cColliderRobot novCircleCollider1 = new cColliderRobot(novCircle1,novCircleShape1),
            novCircleCollider2 = new cColliderRobot(novCircle2,novCircleShape2);
    Pair<cCollider,cCollider> novCirclesPair = new Pair<>(novCircleCollider1,novCircleCollider2);
    //endregion

    //region Non overlapping rects
    eRobot novRect1 = new eRobot(new Point(0,0)),novRect2 = new eRobot(new Point(5,5));
    Rectangle novRectShape1 = new Rectangle(5,5), novRectShape2 = new Rectangle(7,7);
    cColliderRobot novRectCollider1 = new cColliderRobot(novRect1,novRectShape1),
            novRectCollider2 = new cColliderRobot(novRect2,novRectShape2);
    Pair<cCollider,cCollider> novRectPair = new Pair<>(novRectCollider1,novRectCollider2);
    //endregion

    //region Overlapping rect-circle
    eRobot ovrcRect = new eRobot(new Point(0,0)), ovrcCircle = new eRobot(new Point(5,5));
    Shape ovrcRectShape = new Rectangle(10,10), ovrcCircleShape = new Circle(5);
    cColliderRobot ovrcRectCollider = new cColliderRobot(ovrcRect,ovrcRectShape),
    ovrcCircleCollider = new cColliderRobot(ovrcCircle,ovrcCircleShape);
    Pair<cCollider,cCollider> ovRcPair = new Pair<>(ovrcRectCollider,ovrcCircleCollider);
    //endregion

    //region Non overlapping rect-circle
    eRobot novrcRect = new eRobot(new Point(0,0)), novrcCircle = new eRobot(new Point(50,50));
    Shape novrcRectShape = new Rectangle(10,10), novrcCircleShape = new Circle(5);
    cColliderRobot novrcRectCollider = new cColliderRobot(novrcRect,novrcRectShape),
            novrcCircleCollider = new cColliderRobot(novrcCircle,novrcCircleShape);
    Pair<cCollider,cCollider> novRcPair = new Pair<>(novrcRectCollider,novrcCircleCollider);
    //endregion

    //[Non] Overlapping Cricle Rect
    Pair<cCollider,cCollider> ovCrPair = new Pair<cCollider,cCollider>(ovrcCircleCollider,ovrcRectCollider),
            novCrPair = new Pair<cCollider,cCollider>(novrcCircleCollider,novrcRectCollider);

    @Test
    void addComponents() {
        System temp = new SysCollision();
        temp.addComponents(List.of(new Component[]{parentCollider, new cCEU(parent, null)}));
        assertEquals(1, temp.getComponents().size());
    }

    @Test
    void run() {
    }

    @Test
    void addComponent() {
        System temp = new SysCollision();
        temp.addComponent(new cColliderRobot(parent, null));

        assertEquals(1, temp.getComponents().size());
    }

    @Test
    void getCollisionType() throws Exception {
        assertEquals(SysCollision.getCollisionType(ovCirclesPair), SysCollision.CollisionType.CIRCLE_CIRCLE);
        assertEquals(SysCollision.getCollisionType(ovRectPair), SysCollision.CollisionType.RECT_RECT);
        assertEquals(SysCollision.getCollisionType(novCirclesPair), SysCollision.CollisionType.CIRCLE_CIRCLE);
        assertEquals(SysCollision.getCollisionType(novRectPair), SysCollision.CollisionType.RECT_RECT);
        assertEquals(SysCollision.getCollisionType(ovRcPair), SysCollision.CollisionType.RECT_CIRCLE);
        assertEquals(SysCollision.getCollisionType(novRcPair), SysCollision.CollisionType.RECT_CIRCLE);
        assertEquals(SysCollision.getCollisionType(ovCrPair), SysCollision.CollisionType.CIRCLE_RECT);
        assertEquals(SysCollision.getCollisionType(novCrPair), SysCollision.CollisionType.CIRCLE_RECT);
    }

    @Test
    void collisionCircleToCircle() {
        assertTrue(SysCollision.collisionCircleToCircle(ovCirclesPair));
        assertFalse(SysCollision.collisionCircleToCircle(novCirclesPair));
    }

    @Test
    void collisionCircleToRect() {
        assertTrue(SysCollision.collisionCircleToRect(ovCrPair));
        assertFalse(SysCollision.collisionCircleToRect(novCrPair));
    }

    @Test
    void collisionRectToCircle() {
        assertTrue(SysCollision.collisionRectToCircle(ovRcPair));
        assertFalse(SysCollision.collisionRectToCircle(novRcPair));
    }

    @Test
    void collisionRectToRect() {
        assertTrue(SysCollision.collisionRectToRect(ovRectPair));
        assertTrue(SysCollision.collisionRectToRect(novRectPair));
    }

    @Test
    void testAddComponents() {
    }

    @Test
    void testRun() {
    }

    @Test
    void testAddComponent() {
    }

    @Test
    void isColliding() throws Exception {
        assertTrue(SysCollision.isColliding(ovCirclesPair));
        assertFalse(SysCollision.isColliding(novCirclesPair));
        assertTrue(SysCollision.isColliding(ovCrPair));
        assertFalse(SysCollision.isColliding(novCrPair));
        assertTrue(SysCollision.isColliding(ovRcPair));
        assertFalse(SysCollision.isColliding(novRcPair));
        assertTrue(SysCollision.isColliding(ovRectPair));
        assertTrue(SysCollision.isColliding(novRectPair));
    }
}