package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;

abstract public class System {
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
