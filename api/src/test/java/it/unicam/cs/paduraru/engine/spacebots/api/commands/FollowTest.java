package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PCircle;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.PSysCollision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FollowTest {

    @Test
    public void test_followsOne() throws Exception {
        PRobot follower = new PRobot(new PVector(0,0));
        PRobot signaler1 = new PRobot(new PVector(10,0));

        PLabel label = new PLabel("0010110_");

        signaler1.signal(label);

        PCircle circle = new PCircle(10);

        PColliderRobot followerCollider = new PColliderRobot(follower, circle),
        signaler1Collider = new PColliderRobot(signaler1, circle);

        PEnvironment env = new PEnvironment();

        env.addEntity(follower);
        env.addEntity(signaler1);

        env.addComponent(followerCollider);
        env.addComponent(signaler1Collider);

        env.addSystem(new PSysCollision());

        GameController.addEnvironment(env);

        Follow cmdFollow = new Follow(label, 10,5);
        cmdFollow.execute(follower, 0);

        assertTrue(follower.getPosition().equals(new PVector(5,0)));
    }

    @Test
    public void test_followsTwo() throws Exception {
        PRobot follower = new PRobot(new PVector(0,0));
        PRobot signaler1 = new PRobot(new PVector(10,0)),
                signaler2 = new PRobot(new PVector(0,10));

        PLabel label = new PLabel("0010110_");

        signaler1.signal(label);

        PCircle circle = new PCircle(10);

        PColliderRobot followerCollider = new PColliderRobot(follower, circle),
                signaler1Collider = new PColliderRobot(signaler1, circle),
                signaler2Collider = new PColliderRobot(signaler2, circle);

        PEnvironment env = new PEnvironment();

        env.addEntity(follower);
        env.addEntity(signaler1);
        env.addEntity(signaler2);

        env.addComponent(followerCollider);
        env.addComponent(signaler1Collider);
        env.addComponent(signaler2Collider);

        env.addSystem(new PSysCollision());

        GameController.addEnvironment(env);

        Follow cmdFollow = new Follow(label, 10,5);
        cmdFollow.execute(follower, 0);

        //assertTrue(follower.getPosition().equals(new Vector(5,0)));
    }

}