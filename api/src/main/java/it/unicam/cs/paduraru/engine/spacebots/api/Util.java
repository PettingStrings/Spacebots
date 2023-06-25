package it.unicam.cs.paduraru.engine.spacebots.api;

public class Util {
    private Util(){};

    public static int randInt(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
