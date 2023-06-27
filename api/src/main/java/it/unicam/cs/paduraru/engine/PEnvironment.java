package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;

import java.util.ArrayList;
import java.util.List;

public class PEnvironment {

    List<PEntity> entities;
    List<PComponent> components;
    List<ASystem> systems;

    public PEnvironment(){
        entities = new ArrayList<PEntity>();
        systems = new ArrayList<ASystem>();
        components = new ArrayList<PComponent>();
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

    public List<PEntity> getEntities() {
        return entities;
    }

    public void addEntity(PEntity toAdd) {
        toAdd.setID(entities.stream().count());
        entities.add(toAdd);
    }

    public void addSystem(ASystem toAdd) {
        systems.add(toAdd);
    }

    public void addComponent(PComponent toAdd) {
        toAdd.setID(components.stream().count());
        components.add(toAdd);
    }

    public List<ASystem> getSystems() {
        return systems;
    }

    public void run() throws Exception {
        for (ASystem system : systems) {
            system.run();
        }
    }

    public void addComponents(List<PComponent> components) {
        for (PComponent comp: components)
            addComponent(comp);
    }

    public List<PComponent> getComponentOf(PEntity pEntity, Class<cColliderRobot> cColliderRobotClass) {
        return components.stream()
                .filter(pComponent -> pComponent instanceof cColliderRobot && pComponent.parent.equals(pEntity))
                .toList();
    }
}
