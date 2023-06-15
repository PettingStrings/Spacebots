package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    List<Entity> entities;
    List<System> systems;
    List<Component> components;

    public void Initialize() {
        if(entities != null && systems != null && components != null)
            return;

        entities = new ArrayList<Entity>();
        systems = new ArrayList<System>();
        components = new ArrayList<Component>();

        for(int i = 0; i < systems.stream().count(); i++){
            systems.get(i).add(components);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    protected void AddEntity(Entity toAdd) {
        entities.add(toAdd);
    }

    public void AddSystem(System toAdd) {
        systems.add(toAdd);
    }

    public void AddComponent(Component toAdd) {
        components.add(toAdd);
    }
}
