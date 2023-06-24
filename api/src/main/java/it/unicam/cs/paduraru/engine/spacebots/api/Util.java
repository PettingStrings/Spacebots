package it.unicam.cs.paduraru.engine.spacebots.api;

public class Util {
    private Util(){};

    public static int RandInt(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
