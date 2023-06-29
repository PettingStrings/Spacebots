package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PEnvironment implements DeepCopy{

    AtomicLong lastID;
    List<PEntity> entities;
    List<PComponent> components;
    List<PSystem> systems;

    public PEnvironment(){
        lastID = new AtomicLong(0);
        entities = new ArrayList<PEntity>();
        systems = new ArrayList<PSystem>();
        components = new ArrayList<PComponent>();
    }

    public Object deepCopy() {
        PEnvironment environment = new PEnvironment();
        environment.lastID = new AtomicLong(this.lastID.get());
        environment.entities = this.entities.stream().map(entity -> (PEntity)entity.deepCopy()).collect(Collectors.toList());
        environment.components = this.components.stream().map(comp -> (PComponent)comp.deepCopy()).collect(Collectors.toList());
        //copiare i nuovi parent dentro le nuove entità per avere i riferimenti corretti
        environment.components.forEach(
                comp -> comp.setParent(environment.entities.get(
                        environment.entities.indexOf(comp.getParent()))));

        for (var s: this.systems) {
            environment.systems.add((PSystem) s.deepCopy());
        }
        environment.systems = environment.systems.stream().map(sys -> {sys.clear(); return sys;})
                .map(sys -> {sys.addComponents(environment.components); return sys;})
                .collect(Collectors.toList());

        return environment;
    }

    public  void clear() {
        entities.clear();
        systems.clear();
        components.clear();
        lastID.set(0);
    }

    public List<PEntity> getEntities() {
        return entities;
    }

    public void addEntity(PEntity toAdd) {
        toAdd.setID(lastID.getAndIncrement());
        entities.add(toAdd);
    }

    public void addSystem(PSystem toAdd) {
        systems.add(toAdd);
    }

    public void addComponent(PComponent toAdd) {
        toAdd.setID(lastID.getAndIncrement());
        for (PSystem sys: systems) {
            sys.addComponent(toAdd);
        }
        components.add(toAdd);
    }

    public List<PSystem> getSystems() {
        return systems;
    }

    public void run(){
        for (PSystem system : systems) {
            system.run();
        }
    }

    public List<PComponent> getComponentOf(PEntity pEntity, Class<?> type) {
        return components.stream()
                .filter(pComponent -> type.isInstance(pComponent) && pComponent.getParent().equals(pEntity))
                .toList();
    }
}
