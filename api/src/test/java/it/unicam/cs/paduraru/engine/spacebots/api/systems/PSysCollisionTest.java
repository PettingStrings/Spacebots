package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PRectangle;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PSysCollisionTest {

    public class pColliderTest extends PCollider {
        public boolean onExitCalled = false, onCollidingCalled = false;

        public pColliderTest(PEntity parent, PShape shape) {
            super(parent, shape);
        }

        @Override
        public void OnColliding(PCollider second) {
            onCollidingCalled = true;
            onExitCalled = false;
        }

        @Override
        public void OnExit(PCollider second) {
            onCollidingCalled = false;
            onExitCalled = true;
        }
    }

    PRobot parent = new PRobot(new PVector(0,0));
    PCollider parentCollider = new PColliderRobot(parent, null);

    //region Overlapping circles
    PRobot ovCircle1 = new PRobot(new PVector(0,0)), ovCircle2 = new PRobot(new PVector(5,5));
    PCircle ovCircleShape1 = new PCircle(10), ovCircleShape2 = new PCircle(5);
    PColliderRobot ovCircleCollider1 = new PColliderRobot(ovCircle1,ovCircleShape1),
            ovCircleCollider2 = new PColliderRobot(ovCircle2,ovCircleShape2);
    Pair<PCollider, PCollider> ovCirclesPair = new Pair<>(ovCircleCollider1,ovCircleCollider2);
    //endregion

    //region Overlapping rects
    PRobot ovRect1 = new PRobot(new PVector(0,0)),ovRect2 = new PRobot(new PVector(5,5));
    PRectangle ovRectShape1 = new PRectangle(5,5), ovRectShape2 = new PRectangle(7,7);
    PColliderRobot ovRectCollider1 = new PColliderRobot(ovRect1,ovRectShape1),
            ovRectCollider2 = new PColliderRobot(ovRect2,ovRectShape2);
    Pair<PCollider, PCollider> ovRectPair = new Pair<>(ovRectCollider1,ovRectCollider2);
    //endregion

    //region Non overlapping circles
    PRobot novCircle1 = new PRobot(new PVector(0,0)), novCircle2 = new PRobot(new PVector(20,20));
    PCircle novCircleShape1 = new PCircle(10), novCircleShape2 = new PCircle(5);
    PColliderRobot novCircleCollider1 = new PColliderRobot(novCircle1,novCircleShape1),
            novCircleCollider2 = new PColliderRobot(novCircle2,novCircleShape2);
    Pair<PCollider, PCollider> novCirclesPair = new Pair<>(novCircleCollider1,novCircleCollider2);
    //endregion

    //region Non overlapping rects
    PRobot novRect1 = new PRobot(new PVector(0,0)),novRect2 = new PRobot(new PVector(5,5));
    PRectangle novRectShape1 = new PRectangle(5,5), novRectShape2 = new PRectangle(7,7);
    PColliderRobot novRectCollider1 = new PColliderRobot(novRect1,novRectShape1),
            novRectCollider2 = new PColliderRobot(novRect2,novRectShape2);
    Pair<PCollider, PCollider> novRectPair = new Pair<>(novRectCollider1,novRectCollider2);
    //endregion

    //region Overlapping rect-circle
    PRobot ovrcRect = new PRobot(new PVector(0,0)), ovrcCircle = new PRobot(new PVector(10,5));
    PShape ovrcRectShape = new PRectangle(10,10), ovrcCircleShape = new PCircle(5);
    PColliderRobot ovrcRectCollider = new PColliderRobot(ovrcRect,ovrcRectShape),
    ovrcCircleCollider = new PColliderRobot(ovrcCircle,ovrcCircleShape);
    Pair<PCollider, PCollider> ovRcPair = new Pair<>(ovrcRectCollider,ovrcCircleCollider);
    //endregion

    //region Non overlapping rect-circle
    PRobot novrcRect = new PRobot(new PVector(0,0)), novrcCircle = new PRobot(new PVector(50,50));
    PShape novrcRectShape = new PRectangle(10,10), novrcCircleShape = new PCircle(5);
    PColliderRobot novrcRectCollider = new PColliderRobot(novrcRect,novrcRectShape),
            novrcCircleCollider = new PColliderRobot(novrcCircle,novrcCircleShape);
    Pair<PCollider, PCollider> novRcPair = new Pair<>(novrcRectCollider,novrcCircleCollider);
    //endregion

    //[Non] Overlapping Cricle Rect
    Pair<PCollider, PCollider> ovCrPair = new Pair<>(ovrcCircleCollider,ovrcRectCollider),
            novCrPair = new Pair<>(novrcCircleCollider,novrcRectCollider);

    @Test
    void test_addComponents() {
        PSystem temp = new PSysCollision();
        temp.addComponents(List.of(new PComponent[]{parentCollider, new PCEU(parent, null)}));
        assertEquals(1, temp.getComponents().size());
    }

    @Test
    void test_addComponent() {
        //Memo: la gestione degli ID lo fa l'environment
        PSystem temp = new PSysCollision();
        temp.addComponent(new PColliderRobot(parent, null));

        assertEquals(1, temp.getComponents().size());
        assertEquals(0, temp.getComponents().get(0).getID());

        temp.addComponent(new PColliderRobot(parent, null));

        assertEquals(2, temp.getComponents().size());
        assertEquals(0, temp.getComponents().get(1).getID());
    }

    @Test
    void test_getCollisionType(){
        assertEquals(PSysCollision.getCollisionType(ovCirclesPair), PSysCollision.CollisionType.CIRCLE_CIRCLE);
        assertEquals(PSysCollision.getCollisionType(ovRectPair), PSysCollision.CollisionType.RECT_RECT);
        assertEquals(PSysCollision.getCollisionType(novCirclesPair), PSysCollision.CollisionType.CIRCLE_CIRCLE);
        assertEquals(PSysCollision.getCollisionType(novRectPair), PSysCollision.CollisionType.RECT_RECT);
        assertEquals(PSysCollision.getCollisionType(ovRcPair), PSysCollision.CollisionType.RECT_CIRCLE);
        assertEquals(PSysCollision.getCollisionType(novRcPair), PSysCollision.CollisionType.RECT_CIRCLE);
        assertEquals(PSysCollision.getCollisionType(ovCrPair), PSysCollision.CollisionType.CIRCLE_RECT);
        assertEquals(PSysCollision.getCollisionType(novCrPair), PSysCollision.CollisionType.CIRCLE_RECT);
    }

    @Test
    void test_collisionCircleToCircle() {
        assertTrue(PSysCollision.collisionCircleToCircle(ovCirclesPair));
        assertFalse(PSysCollision.collisionCircleToCircle(novCirclesPair));
    }

    @Test
    void test_collisionCircleToRect() {
        assertTrue(PSysCollision.collisionCircleToRect(ovCrPair));
        assertFalse(PSysCollision.collisionCircleToRect(novCrPair));
    }

    @Test
    void test_collisionRectToCircle() {
        assertTrue(PSysCollision.collisionRectToCircle(ovRcPair));
        assertFalse(PSysCollision.collisionRectToCircle(novRcPair));
    }

    @Test
    void test_collisionRectToRect() {
        assertTrue(PSysCollision.collisionRectToRect(ovRectPair));
        assertTrue(PSysCollision.collisionRectToRect(novRectPair));
    }
    @Test
    void test_run() {
    }

    @Test
    void test_isColliding(){
        assertTrue(PSysCollision.isColliding(ovCirclesPair));
        assertFalse(PSysCollision.isColliding(novCirclesPair));
        assertTrue(PSysCollision.isColliding(ovCrPair));
        assertFalse(PSysCollision.isColliding(novCrPair));
        assertTrue(PSysCollision.isColliding(ovRcPair));
        assertFalse(PSysCollision.isColliding(novRcPair));
        assertTrue(PSysCollision.isColliding(ovRectPair));
        assertTrue(PSysCollision.isColliding(novRectPair));

        //Additional testing
        PRobot robot1 = new PRobot(new PVector(0,0)), robot2 = new PRobot(new PVector(4,0));
        Pair<PCollider, PCollider> rectToRect = new Pair<>(new PColliderRobot(robot1,new PRectangle(5,5)),
                new PColliderRobot(robot2,new PRectangle(5,5)));
        assertTrue(PSysCollision.isColliding(rectToRect));
    }

    @Test
    void test_doesItCallEventsCorrectly(){
        PSysCollision testSystem = new PSysCollision();
        PRobot robot1 = new PRobot(new PVector(0,0)), robot2 = new PRobot(new PVector(100,100));

        robot1.setID(0);
        robot2.setID(1);

        pColliderTest coll1 = new pColliderTest(robot1, new PCircle(10)),
                coll2 = new pColliderTest(robot2, new PCircle(10));

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
    void test_checkInCircle(){
        PSysCollision sys = new PSysCollision();
        PRobot robot1 = new PRobot(new PVector(0,0)), robot2 = new PRobot(new PVector(100,100))
        ,robot3 = new PRobot(new PVector(30,30));

        robot1.setID(0);
        robot2.setID(1);
        robot3.setID(3);

        pColliderTest coll1 = new pColliderTest(robot1, new PCircle(50)),
                coll2 = new pColliderTest(robot2, new PCircle(10)),
                coll3 = new pColliderTest(robot3, new PCircle(10));

        sys.addComponent(coll1);
        sys.addComponent(coll2);
        sys.addComponent(coll3);

        List<PCollider> temp = sys.checkInCircle(robot1.getPosition(),50);

        assertEquals(2, temp.size());
    }
}