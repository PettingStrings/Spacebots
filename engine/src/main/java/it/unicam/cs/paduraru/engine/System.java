package it.unicam.cs.paduraru.engine;

import java.util.List;

abstract public class System {
    protected List<Component> components;

    public abstract void Run();

    public void Add() {

    }
    public abstract void add(List<Component> components);

    public abstract void step();
}
