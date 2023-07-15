package it.unicam.cs.paduraru.engine.spacebots.api;

import java.util.Random;

/**
 * Contiene vari metodi generici utili per l'API.
 */
public class SpaceBotsUtil {
    private SpaceBotsUtil(){}

    static Random random = new Random();

    /* Unused
    public static int randInt(double min, double max){
        return (int) ((Math.random() * (max - min)) + min);
    }*/
    public static double randDouble(double min, double max){
        return (random.nextDouble() * (max - min) + min);
    }
}
