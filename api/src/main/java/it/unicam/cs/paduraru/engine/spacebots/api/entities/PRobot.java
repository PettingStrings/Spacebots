package it.unicam.cs.paduraru.engine.spacebots.api.entities;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.PEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta un robot.
 */
public class PRobot extends PEntity {

    /**
     * Label con cui è attualmente in contatto.
     */
    private List<PLabel> currentLabels;
    /**
     * Label che sta attualmente segnalando. Un robot può segnalare più label.
     */
    private List<PLabel> signaledLabels;
    /**
     * La Label che sta attualemnte seguendo. Se non ne sta seguendo nessuna il valore è null.
     */
    private PLabel followingLabel;
    /**
     * Direzione in cui sta viaggiando. Ha un range da -1 a 1 per ogni componente.
     */
    private PVector direction;
    private double velocity;

    public PRobot(PVector origin) {
        super();
        setPosition(origin);
        currentLabels = new ArrayList<>();
        signaledLabels = new ArrayList<>();
        velocity = 0;
        direction = new PVector(0,0);
    }

    protected PRobot(PEntity other) {
        super(other);
        currentLabels = new ArrayList<>();
        signaledLabels = new ArrayList<>();
        velocity = 0;
        direction = new PVector(0,0);
    }

    public void addLabel(PLabel label) {
        if(getCurrentLabels().contains(label)) return;
        getCurrentLabels().add(label);
    }

    public void removeLabel(PLabel label) {
        getCurrentLabels().remove(label);
    }

    public List<PLabel> getCurrentLabels() {
        return currentLabels;
    }

    public void setDirection(PVector newDir) {
        this.direction = newDir.normalize();
    }

    /**
     * Calcola la prossima posizione in base alla direzione e velocità attuale.
     */
    public void move() {
        setPosition(getPosition().add(direction.mulScalar(velocity)));
    }

    public List<PLabel> getSignaledLabels() {
        return signaledLabels;
    }

    /**
     * Segnala una label.
     * Può segnalare una label con cui non è entrato in contatto.
     * @param label Label da segnalare.
     */
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
        PRobot robot = new PRobot((PEntity)(super.deepCopy()));
        robot.currentLabels = currentLabels.stream().map(lab -> (PLabel)lab.deepCopy()).collect(Collectors.toList());
        robot.signaledLabels = signaledLabels.stream().map(lab -> (PLabel)lab.deepCopy()).collect(Collectors.toList());
        if(followingLabel != null)
            robot.followingLabel = (PLabel) followingLabel.deepCopy();
        robot.direction = (PVector) direction.deepCopy();
        robot.velocity = velocity;
        return robot;
    }
}
