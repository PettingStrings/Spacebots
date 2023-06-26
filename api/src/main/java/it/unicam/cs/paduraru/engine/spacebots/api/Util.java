package it.unicam.cs.paduraru.engine.spacebots.api;

public class Util {
    private Util(){};

    public static int randInt(double min, double max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}