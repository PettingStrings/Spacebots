package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

abstract public class PSystem implements DeepCopy{

    protected List<PComponent> components;
    protected PSystem(){
        components = new ArrayList<>();
    }
    public abstract void addComponents(List<PComponent> components);

    public abstract void run() throws Exception;

    public abstract void addComponent(PComponent comp);
    public List<PComponent> getComponents() {
        return components;
    }

    public void clear() {
        components.clear();
    }
}