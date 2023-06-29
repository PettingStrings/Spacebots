package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class PSystem implements DeepCopy{
    private List<PComponent> components;
    protected PSystem(){
        components = new ArrayList<>();
    }
    public void setComponents(List<PComponent> components) {
        this.components = components;
    }
    public abstract void addComponents(List<PComponent> components);
    public abstract void run();
    public abstract void addComponent(PComponent comp);
    public List<PComponent> getComponents() {
        return components;
    }
    public void clear() {
        components.clear();
    }
}
