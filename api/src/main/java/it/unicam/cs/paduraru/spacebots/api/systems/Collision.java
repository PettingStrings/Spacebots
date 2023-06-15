package it.unicam.cs.paduraru.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.Component;
import it.unicam.cs.paduraru.engine.System;
import it.unicam.cs.paduraru.spacebots.api.components.Collider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collision extends System{
    @Override
    public void Run() {

    }

    @Override
    public void add(List<Component> componentsToAdd) {
        this.components = componentsToAdd.stream().filter( component -> component instanceof Collider).collect(Collectors.toList());
    }

    @Override
    public void step() {

    }
}
