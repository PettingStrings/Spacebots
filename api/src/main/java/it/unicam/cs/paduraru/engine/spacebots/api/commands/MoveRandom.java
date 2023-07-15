package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;

/**
 * Muove il robot verso una posizione scelta casualmente nell' intervallo dato.
 */
//MOVE RANDOM x1 x2 y1 y2 s: si muove alla velocità s espressa in m/s verso una posizione (x,y)
//scelta casualmente nell’intervallo [x1, x2] e [y1, y2];
public class MoveRandom implements BotCommand{
    Pair<Double,Double> rangeX, rangeY;
    double velocity;
    PVector dir;

    public MoveRandom(double minX, double maxX, double minY, double maxY, double velocity){
        this(new Pair<>(minX,maxX),new Pair<>(minY,maxY),velocity);
    }

    public MoveRandom(Pair<Double,Double> rangeX, Pair<Double,Double> rangeY, double velocity){
        this.velocity = velocity;
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.RandomizeDirection();
    }

    /**
     * @param target             Entità sulla quale il comando agirà.
     * @param instructionPointer Numero di riga in cui viene eseguita l' istruzione.
     * @return Numero della prossima riga.
     */
    @Override
    public int execute(PRobot target, int instructionPointer) {
        target.setDirection(dir);
        target.setVelocity(velocity);
        target.move();
        return instructionPointer+1;
    }

    public void RandomizeDirection(){
        this.dir = PVector.random(rangeX.first(), rangeX.second(), rangeY.first(), rangeY.second());
    }
    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy() {
        return new MoveRandom((Pair<Double, Double>) rangeX.deepCopy(),
                (Pair<Double, Double>) rangeY.deepCopy(), velocity);
    }
}
