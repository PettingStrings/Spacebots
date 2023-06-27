package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.ASystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SysCollisionTest {

    public class cColliderTest extends cCollider{
        public boolean onExitCalled = false, onCollidingCalled = false;

        public cColliderTest(PEntity parent, PShape shape) {
            super(parent, shape);
        }

        @Override
        public void OnColliding(cCollider second) {
            onCollidingCalled = true;
            onExitCalled = false;
        }

        @Override
        public void OnExit(cCollider second) {
            onCollidingCalled = false;
            onExitCalled = true;
        }
    }

    ERobot parent = new ERobot(new PVector(0,0));
    cCollider parentCollider = new cColliderRobot(parent, null);

    //region Overlapping circles
    ERobot ovCircle1 = new ERobot(new PVector(0,0)), ovCircle2 = new ERobot(new PVector(5,5));
    PCircle ovCircleShape1 = new PCircle(10), ovCircleShape2 = new PCircle(5);
    cColliderRobot ovCircleCollider1 = new cColliderRobot(ovCircle1,ovCircleShape1),
            ovCircleCollider2 = new cColliderRobot(ovCircle2,ovCircleShape2);
    Pair<cCollider,cCollider> ovCirclesPair = new Pair<>(ovCircleCollider1,ovCircleCollider2);
    //endregion

    //region Overlapping rects
    ERobot ovRect1 = new ERobot(new PVector(0,0)),ovRect2 = new ERobot(new PVector(5,5));
    PRectangle ovRectShape1 = new PRectangle(5,5), ovRectShape2 = new PRectangle(7,7);
    cColliderRobot ovRectCollider1 = new cColliderRobot(ovRect1,ovRectShape1),
            ovRectCollider2 = new cColliderRobot(ovRect2,ovRectShape2);
    Pair<cCollider,cCollider> ovRectPair = new Pair<>(ovRectCollider1,ovRectCollider2);
    //endregion

    //region Non overlapping circles
    ERobot novCircle1 = new ERobot(new PVector(0,0)), novCircle2 = new ERobot(new PVector(20,20));
    PCircle novCircleShape1 = new PCircle(10), novCircleShape2 = new PCircle(5);
    cColliderRobot novCircleCollider1 = new cColliderRobot(novCircle1,novCircleShape1),
            novCircleCollider2 = new cColliderRobot(novCircle2,novCircleShape2);
    Pair<cCollider,cCollider> novCirclesPair = new Pair<>(novCircleCollider1,novCircleCollider2);
    //endregion

    //region Non overlapping rects
    ERobot novRect1 = new ERobot(new PVector(0,0)),novRect2 = new ERobot(new PVector(5,5));
    PRectangle novRectShape1 = new PRectangle(5,5), novRectShape2 = new PRectangle(7,7);
    cColliderRobot novRectCollider1 = new cColliderRobot(novRect1,novRectShape1),
            novRectCollider2 = new cColliderRobot(novRect2,novRectShape2);
    Pair<cCollider,cCollider> novRectPair = new Pair<>(novRectCollider1,novRectCollider2);
    //endregion

    //region Overlapping rect-circle
    ERobot ovrcRect = new ERobot(new PVector(0,0)), ovrcCircle = new ERobot(new PVector(5,5));
    PShape ovrcRectShape = new PRectangle(10,10), ovrcCircleShape = new PCircle(5);
    cColliderRobot ovrcRectCollider = new cColliderRobot(ovrcRect,ovrcRectShape),
    ovrcCircleCollider = new cColliderRobot(ovrcCircle,ovrcCircleShape);
    Pair<cCollider,cCollider> ovRcPair = new Pair<>(ovrcRectCollider,ovrcCircleCollider);
    //endregion

    //region Non overlapping rect-circle
    ERobot novrcRect = new ERobot(new PVector(0,0)), novrcCircle = new ERobot(new PVector(50,50));
    PShape novrcRectShape = new PRectangle(10,10), novrcCircleShape = new PCircle(5);
    cColliderRobot novrcRectCollider = new cColliderRobot(novrcRect,novrcRectShape),
            novrcCircleCollider = new cColliderRobot(novrcCircle,novrcCircleShape);
    Pair<cCollider,cCollider> novRcPair = new Pair<>(novrcRectCollider,novrcCircleCollider);
    //endregion

    //[Non] Overlapping Cricle Rect
    Pair<cCollider,cCollider> ovCrPair = new Pair<>(ovrcCircleCollider,ovrcRectCollider),
            novCrPair = new Pair<>(novrcCircleCollider,novrcRectCollider);

    @Test
    void test_addComponents() {
        ASystem temp = new SysCollision();
        temp.addComponents(List.of(new PComponent[]{parentCollider, new cCEU(parent, null)}));
        assertEquals(1, temp.getComponents().size());
    }

    @Test
    void test_addComponent() {
        ASystem temp = new SysCollision();
        temp.addComponent(new cColliderRobot(parent, null));

        assertEquals(1, temp.getComponents().size());
        assertEquals(0, temp.getComponents().get(0).getID());

        temp.addComponent(new cColliderRobot(parent, null));

        assertEquals(2, temp.getComponents().size());
        assertEquals(1, temp.getComponents().get(1).getID());
    }

    @Test
    void test_getCollisionType() throws Exception {
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
    void test_collisionCircleToCircle() {
        assertTrue(SysCollision.collisionCircleToCircle(ovCirclesPair));
        assertFalse(SysCollision.collisionCircleToCircle(novCirclesPair));
    }

    @Test
    void test_collisionCircleToRect() {
        assertTrue(SysCollision.collisionCircleToRect(ovCrPair));
        assertFalse(SysCollision.collisionCircleToRect(novCrPair));
    }

    @Test
    void test_collisionRectToCircle() {
        assertTrue(SysCollision.collisionRectToCircle(ovRcPair));
        assertFalse(SysCollision.collisionRectToCircle(novRcPair));
    }

    @Test
    void test_collisionRectToRect() {
        assertTrue(SysCollision.collisionRectToRect(ovRectPair));
        assertTrue(SysCollision.collisionRectToRect(novRectPair));
    }
    @Test
    void test_run() {
    }

    @Test
    void test_isColliding() throws Exception {
        assertTrue(SysCollision.isColliding(ovCirclesPair));
        assertFalse(SysCollision.isColliding(novCirclesPair));
        assertTrue(SysCollision.isColliding(ovCrPair));
        assertFalse(SysCollision.isColliding(novCrPair));
        assertTrue(SysCollision.isColliding(ovRcPair));
        assertFalse(SysCollision.isColliding(novRcPair));
        assertTrue(SysCollision.isColliding(ovRectPair));
        assertTrue(SysCollision.isColliding(novRectPair));
    }

    @Test
    void test_doesItCallEventsCorrectly() throws Exception {
        SysCollision testSystem = new SysCollision();
        ERobot robot1 = new ERobot(new PVector(0,0)), robot2 = new ERobot(new PVector(100,100));

        robot1.setID(0);
        robot2.setID(1);

        cColliderTest coll1 = new cColliderTest(robot1, new PCircle(10)),
                coll2 = new cColliderTest(robot2, new PCircle(10));

        coll1.setID(0);
        coll2.setID(1);

        testSystem.addComponent(coll1);
        testSystem.addComponent(coll2);
        //system not ran
        assertFalse(coll1.onCollidingCalled || coll1.onExitCalled
        || coll2.onCollidingCalled || coll2.onExitCalled);

        testSystem.run();
        //system after first run everything should be the same
        assertFalse(coll1.onCollidingCalled || coll1.onExitCalled
                || coll2.onCollidingCalled || coll2.onExitCalled);
        //now we make them collide
        robot1.setPosition(new PVector(100,100));

        testSystem.run();

        assertTrue(coll1.onCollidingCalled && coll2.onCollidingCalled &&
                !coll1.onExitCalled && !coll2.onExitCalled);
        //we run again to be sure
        testSystem.run();

        assertTrue(coll1.onCollidingCalled && coll2.onCollidingCalled &&
                !coll1.onExitCalled && !coll2.onExitCalled);
        //now we separate and OnExt should be called
        robot1.setPosition(new PVector(0,0));

        testSystem.run();

        assertTrue(!coll1.onCollidingCalled && !coll2.onCollidingCalled &&
                coll1.onExitCalled && coll2.onExitCalled);

        testSystem.run();

        assertTrue(!coll1.onCollidingCalled && !coll2.onCollidingCalled &&
                coll1.onExitCalled && coll2.onExitCalled);

    }
    @Test
    void test_checkInCircle() throws Exception {
        SysCollision sys = new SysCollision();
        ERobot robot1 = new ERobot(new PVector(0,0)), robot2 = new ERobot(new PVector(100,100))
        ,robot3 = new ERobot(new PVector(30,30));

        robot1.setID(0);
        robot2.setID(1);
        robot3.setID(3);

        cColliderTest coll1 = new cColliderTest(robot1, new PCircle(50)),
                coll2 = new cColliderTest(robot2, new PCircle(10)),
                coll3 = new cColliderTest(robot3, new PCircle(10));

        sys.addComponent(coll1);
        sys.addComponent(coll2);
        sys.addComponent(coll3);

        List<cCollider> temp = sys.checkInCircle(robot1.getPosition(),50);

        assertEquals(2, temp.size());
    }
}