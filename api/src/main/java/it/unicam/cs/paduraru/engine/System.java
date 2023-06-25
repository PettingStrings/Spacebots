package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

abstract public class System {
    protected AtomicInteger lastID = new AtomicInteger(0);
    protected List<Component> components;
    protected System(){
        components = new ArrayList<>();
    }
    public abstract void addComponents(List<Component> components);

    public abstract void run() throws Exception;

    public abstract void addComponent(Component comp);

    public List<Component> getComponents() {
        return components;
    }
}
