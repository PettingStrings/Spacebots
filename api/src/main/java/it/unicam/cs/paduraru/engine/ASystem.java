package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

abstract public class ASystem {
    protected AtomicInteger lastID = new AtomicInteger(0);
    protected List<Component> components;
    protected ASystem(){
        components = new ArrayList<>();
    }
    public abstract void addComponents(List<Component> components);

    public abstract void run() throws Exception;

    public abstract void addComponent(Component comp);

    public List<Component> getComponents() {
        return components;
    }
}
