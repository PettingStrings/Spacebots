package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.GameController;
import it.unicam.cs.paduraru.engine.Point;
import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

import java.util.List;
/*
FOLLOW label dist s: si muove muove alla velocità s espressa in m/s una direzione che è la media delle posizioni
dei robot che segnalano la condizione label e che di trovano ad una distanza minore o uguale a dist.
Se non esistono robot viene scelta una direzione a caso nell’intervallo [-dist, dist]x[-dist, dist].
*/
public class Follow extends BotCommand{
    Label label;
    int dist;
    int velocity;
    public Follow(Label label, int dist, int velocity){
        this.label = label;
        this.dist = dist;
        this.velocity = velocity;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {

        List<cCollider> foundTargets;

        try {
             foundTargets = GameController.checkInCircle(target.getPosition(), dist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(foundTargets.size() == 0){
            MoveRandom tmp = new MoveRandom(
            target.getPosition().getX() - dist,
            target.getPosition().getX() + dist,
            target.getPosition().getY() - dist,
            target.getPosition().getY() + dist,
                  velocity
            );

            return tmp.execute(target, instructionPointer);
        }

        foundTargets.remove(target);
        List<Point> suitableRobotsPositions = foundTargets.stream().filter(collider -> collider.getParent() instanceof ERobot)
                .map(collider -> (ERobot)collider.getParent()).filter(robot -> robot.getSignaledLabels().contains(label))
                .map(robot -> robot.getPosition())
                .toList();

        Move tmp = new Move(getAvgDirection(suitableRobotsPositions), velocity);

        return tmp.execute(target, instructionPointer);
    }

    private Point getAvgDirection(List<ERobot> suitableRobots) {
        Point avg = new Point(0,0);
        int i = 0;
        for (; i < suitableRobots.size(); i++) {
            avg = avg.add(suitableRobots.get(i).getPosition());
        }
        return avg.divScalar(i);
    }

    @Override
    public void initialize() {

    }
}
