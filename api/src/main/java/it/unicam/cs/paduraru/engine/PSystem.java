package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Da il modello da cui partire per la creazione di un sistema utilizzabile in un environment
 */
public abstract class PSystem implements DeepCopy{
    private List<PComponent> components;
    protected PSystem(){
        components = new ArrayList<>();
    }
    public void setComponents(List<PComponent> components) {
        this.components = components;
    }
    public abstract void addComponents(List<PComponent> components);

    /**
     * Esegue un dato numero di step per simulazione.
     * @param steps Numero di step da eseguire.
     */
    public void run(long steps){
        for (int i = 0; i < steps; i++) {
            run();
        }
    }
    /**
     * Esegue uno step di simulazione.
     */
    public abstract void run();
    public abstract void addComponent(PComponent comp);
    public List<PComponent> getComponents() {
        return components;
    }
    public void clear() {
        components.clear();
    }
}
