package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.*;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.Circle;
import it.unicam.cs.paduraru.engine.spacebots.api.systems.SysCollision;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FollowTest {

    @Test
    public void test_followsOne() throws Exception {
        ERobot follower = new ERobot(new Vector(0,0));
        ERobot signaler1 = new ERobot(new Vector(10,0));

        Label label = new Label("0010110_");

        signaler1.signal(label);

        Circle circle = new Circle(10);

        cColliderRobot followerCollider = new cColliderRobot(follower, circle),
        signaler1Collider = new cColliderRobot(signaler1, circle);

        Environment env = new Environment();

        env.addEntity(follower);
        env.addEntity(signaler1);

        env.addComponent(followerCollider);
        env.addComponent(signaler1Collider);

        env.addSystem(new SysCollision());

        env.initialize();

        GameController.addEnvironment(env);

        Follow cmdFollow = new Follow(label, 10,5);
        cmdFollow.execute(follower, 0);

        assertTrue(follower.getPosition().equals(new Vector(5,0)));
    }

    @Test
    public void test_followsTwo() throws Exception {
        ERobot follower = new ERobot(new Vector(0,0));
        ERobot signaler1 = new ERobot(new Vector(10,0)),
                signaler2 = new ERobot(new Vector(0,10));

        Label label = new Label("0010110_");

        signaler1.signal(label);

        Circle circle = new Circle(10);

        cColliderRobot followerCollider = new cColliderRobot(follower, circle),
                signaler1Collider = new cColliderRobot(signaler1, circle),
                signaler2Collider = new cColliderRobot(signaler2, circle);

        Environment env = new Environment();

        env.addEntity(follower);
        env.addEntity(signaler1);
        env.addEntity(signaler2);

        env.addComponent(followerCollider);
        env.addComponent(signaler1Collider);
        env.addComponent(signaler2Collider);

        env.addSystem(new SysCollision());

        env.initialize();

        GameController.addEnvironment(env);

        Follow cmdFollow = new Follow(label, 10,5);
        cmdFollow.execute(follower, 0);

        //assertTrue(follower.getPosition().equals(new Vector(5,0)));
    }

}