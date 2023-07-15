package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.SpaceBotsUtil;

/**
 * Rappresenta un vettore.
 */
public class PVector implements PDeepCopy {
    double x,y;

    public PVector(){
        x = y = 0;
    }
    public PVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    /**
     * Genera un vettore con coordinate generate casualmente nel range definito nei parametri
     * @param minX La coordinata X minore possibile che può essere generata
     * @param maxX La coordinata X maggiore possibile che può essere generata
     * @param minY La coordinata Y minore possibile che può essere generata
     * @param maxY La coordinata Y maggiore possibile che può essere generata
     * @return Un nuovo vettore con coordinate casuali comprese nel range definito
     */
    public static PVector random(double minX, double maxX, double minY, double maxY) {
        return new PVector(SpaceBotsUtil.randDouble(minX, maxX), SpaceBotsUtil.randDouble(minY, maxY));
    }

    /**
     * Esegue la somma vettoriale tra se stesso e un altro vettore.
     * Questo metodo non modifica il vettore.
     * @param other Il vettore da sommare
     * @return Il risultato della somma tra i due vettori.
     */
    public PVector add(PVector other) {
        return new PVector(x + other.getX(), y + other.getY());
    }

    /**
     * Esegue la divisione con uno scalare.
     * Questo metodo non modifica il vettore.
     * @param i Il numero scalare con cui dividere il vettore.
     * @return Un nuovo vettore risulatante dalla divisione.
     */
    public PVector divScalar(double i) {
        return new PVector(divide(x,i), divide(y,i));
    }
    private double divide(double dividend, double divisor){
        if(dividend == 0 || divisor == 0) return 0;
        return dividend/divisor;
    }

    /**
     * Esegue la moltiplicazione con uno scalare.
     * Questo metodo non modifica il vettore.
     * @param i Il numero scalare con cui eseguire la divisione.
     * @return Un nuovo vettore risultante dalla moltiplicazione
     */
    public PVector mulScalar(double i){
        return new PVector(x*i, y*i);
    }

    /**
     * Calcola la lunghezza del vettore.
     * @return Lunghezza del vettore
     */
    public double magnitude(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    /**
     * Normalizza il vettore.
     * Questo metodo non modifica il vettore.
     * @return Un nuovo vettore normalizzato
     */
    public PVector normalize(){
        return divScalar(magnitude());
    }

    /**
     * Calcola il vettore distanza tra questo vettore e un altro.
     * @param other Vettore da cui calcolare la distanza
     * @return Un nuovo vettore con le componenti della distanza
     */
    public PVector distance(PVector other){
        return new PVector(other.getX() - getX(), other.getY() - getY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PVector vector)) return false;
        return x == vector.x && y == vector.y;
    }

    @Override
    public Object deepCopy() {
        return  new PVector(x,y);
    }
}
