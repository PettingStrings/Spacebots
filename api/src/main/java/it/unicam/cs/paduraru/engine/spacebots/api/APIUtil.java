package it.unicam.cs.paduraru.engine.spacebots.api;

/**
 * Contiene vari metodi generici utili per l'API.
 */
public class APIUtil {
    private APIUtil(){};

    /* Unused
    public static int randInt(double min, double max){
        return (int) ((Math.random() * (max - min)) + min);
    }*/
    public static double randDouble(double min, double max){
        return (Math.random() * (max - min) + min);
    }
}
