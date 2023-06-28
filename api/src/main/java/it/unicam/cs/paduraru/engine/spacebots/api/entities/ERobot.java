package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.PEntity;

import java.util.ArrayList;
import java.util.List;

public class ERobot extends PEntity {

    private List<PLabel> currentLabels;
    private List<PLabel> signaledLabels;
    private PLabel followingLabel;
    private PVector direction;//Per colpa del move random devo memorizzare la velocità qui
    private double velocity;

    public ERobot(PVector origin) {
        super();
        SetPosition(origin);
        currentLabels = new ArrayList<>();
        signaledLabels = new ArrayList<>();
        velocity = 0;
        direction = new PVector(0,0);
    }

    protected ERobot(PEntity o) {
        super(o);
        currentLabels = new ArrayList<>();
        signaledLabels = new ArrayList<>();
        velocity = 0;
        direction = new PVector(0,0);
    }

    public void addLabel(PLabel label) {
        getCurrentLabels().add(label);
    }

    public void removeLabel(PLabel label) {
        getCurrentLabels().remove(label);
    }

    public List<PLabel> getCurrentLabels() {
        return currentLabels;
    }

    public PVector getDirection() {
        return direction;
    }

    public void setDirection(PVector newDir) {
        this.direction = newDir.normalize();
    }

    public void move() {
        setPosition(getPosition().add(direction.mulScalar(velocity)));
    }

    public void setCurrentLabels(List<PLabel> currentLabels) {
        this.currentLabels = currentLabels;
    }

    public List<PLabel> getSignaledLabels() {
        return signaledLabels;
    }

    public void setSignaledLabels(List<PLabel> signaledLabels) {
        this.signaledLabels = signaledLabels;
    }

    public PLabel getFollowingLabel() {
        return followingLabel;
    }

    public void setFollowingLabel(PLabel followingLabel) {
        this.followingLabel = followingLabel;
    }

    public void signal(PLabel label) {
        if(signaledLabels.contains(label))
            return;
        signaledLabels.add(label);
    }

    public void unsignal(PLabel label) {
        signaledLabels.remove(label);
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public Object deepCopy() {
        ERobot robot = new ERobot((PEntity)(super.deepCopy()));

        return robot;
    }
}
