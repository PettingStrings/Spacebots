package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

abstract public class ASystem {
    protected AtomicInteger lastID = new AtomicInteger(0);
    protected List<PComponent> components;
    protected ASystem(){
        components = new ArrayList<>();
    }
    public abstract void addComponents(List<PComponent> components);

    public abstract void run() throws Exception;

    public abstract void addComponent(PComponent comp);

    public List<PComponent> getComponents() {
        return components;
    }
}
