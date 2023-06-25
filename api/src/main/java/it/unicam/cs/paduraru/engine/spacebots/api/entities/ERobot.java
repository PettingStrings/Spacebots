package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.Entity;

import java.util.ArrayList;
import java.util.List;

public class ERobot extends Entity {

    private List<Label> currentLabels;
    private List<Label> signaledLabels;
    private Label followingLabel;
    private Point direction;//Per colpa del move random devo memorizzare la velocità qui
    private int velocity;

    public ERobot(Point origin) {
        super();
        SetPosition(origin);
        currentLabels = new ArrayList<>();
        signaledLabels = new ArrayList<>();
        velocity = 0;
    }

    public void addLabel(Label label) {
        getCurrentLabels().add(label);
    }

    public void removeLabel(Label label) {
        getCurrentLabels().remove(label);
    }

    public List<Label> getCurrentLabels() {
        return currentLabels;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point currentVelocity) {
        this.direction = currentVelocity;
    }

    public void move() {
        setPosition(getPosition().add(
                new Point(direction.getX()*velocity, direction.getY()*velocity)));
    }

    public void setCurrentLabels(List<Label> currentLabels) {
        this.currentLabels = currentLabels;
    }

    public List<Label> getSignaledLabels() {
        return signaledLabels;
    }

    public void setSignaledLabels(List<Label> signaledLabels) {
        this.signaledLabels = signaledLabels;
    }

    public Label getFollowingLabel() {
        return followingLabel;
    }

    public void setFollowingLabel(Label followingLabel) {
        this.followingLabel = followingLabel;
    }

    public void signal(Label label) {
        if(signaledLabels.contains(label))
            return;
        signaledLabels.add(label);
    }

    public void unsignal(Label label) {
        signaledLabels.remove(label);
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
