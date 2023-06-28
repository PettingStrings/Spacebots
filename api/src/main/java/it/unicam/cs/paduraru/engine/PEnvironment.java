package it.unicam.cs.paduraru.engine;

import it.unicam.cs.paduraru.engine.spacebots.api.components.cColliderRobot;

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

        environment.components.stream().forEach(
                comp -> comp.parent = environment.entities.get(environment.entities.indexOf(comp.parent)));

        //DA MODIFICARE I SYSTEM VANNO RICREATI DA 0
        //environment.systems = this.systems.stream().map(sys -> (PSystem)sys.deepCopy()).collect(Collectors.toList());
        for (var s: this.systems) {
            environment.systems.add((PSystem) s.deepCopy());
        }
        environment.systems = environment.systems.stream()        .map(sys -> {sys.clear(); return sys;})
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

    public void initialize() {
        /*for(int i = 0; i < systems.stream().count(); i++){
            systems.get(i).addComponents(components);
        }*/
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

    public void run() throws Exception {
        for (PSystem system : systems) {
            system.run();
        }
    }

    public List<PComponent> getComponentOf(PEntity pEntity, Class<?> cColliderRobotClass) {
        return components.stream()
                .filter(pComponent -> pComponent instanceof cColliderRobot && pComponent.parent.equals(pEntity))
                .toList();
    }
}
