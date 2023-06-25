package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    List<Entity> entities;
    List<System> systems;
    List<Component> components;

    public Environment(){
        entities = new ArrayList<Entity>();
        systems = new ArrayList<System>();
        components = new ArrayList<Component>();
    }

    public  void clear() {
        entities.clear();
        systems.clear();
        components.clear();
    }

    public void initialize() {
        for(int i = 0; i < systems.stream().count(); i++){
            systems.get(i).addComponents(components);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity toAdd) {
        toAdd.setID(entities.stream().count());
        entities.add(toAdd);
    }

    public void addSystem(System toAdd) {
        systems.add(toAdd);
    }

    public void addComponent(Component toAdd) {
        toAdd.setID(components.stream().count());
        components.add(toAdd);
    }

    public List<System> getSystems() {
        return systems;
    }

    public void run() throws Exception {
        for (System system : systems) {
            system.run();
        }
    }
}
