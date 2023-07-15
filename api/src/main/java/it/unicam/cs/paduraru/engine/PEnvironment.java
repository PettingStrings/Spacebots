package it.unicam.cs.paduraru.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Si occupa della gestione di una simulazione.
 * Esso si occupa di aggiungere/rimuovere entità, compnenti, sistemi e di eseguire la simulazione.
 */
public class PEnvironment implements PDeepCopy {
    /**
     * Memorizza l'ultimo ID assegnato.
     * L'assegnazione degli ID è incrementale e locale all'environent,
     * cioè l'univocità dell'ID vale solo per l'environment cho lo ha assegnato.
     */
    AtomicLong lastID;
    List<PEntity> entities;
    List<PComponent> components;
    List<PSystem> systems;

    public PEnvironment(){
        lastID = new AtomicLong(0);
        entities = new ArrayList<>();
        systems = new ArrayList<>();
        components = new ArrayList<>();
    }

    /**
     * Azzera tutto l'enviroment.
     * Elimina tutte le entità, componeneti, systemi e resetta il lastID a 0.
     */
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

    /**
     * Esegue un step di simulazione.
     */
    public void run(){
        for (PSystem system : systems) {
            system.run();
        }
    }

    /**
     * Ritorna tutti i componenti di un certo tipo di una entità.
     * @param pEntity Entità di cui cercare i componenti.
     * @param type Tipo dei componenti da cercare.
     * @return Lista dei componenti appartenenti all'entita. Se non sono stati trovati componenti la lista sarà vuota.
     */
    public List<PComponent> getComponentOf(PEntity pEntity, Class<?> type) {
        return components.stream()
                .filter(pComponent -> type.isInstance(pComponent) && pComponent.getParent().equals(pEntity))
                .toList();
    }
    public Object deepCopy() {
        PEnvironment newEnvironment = new PEnvironment();
        newEnvironment.lastID = new AtomicLong(this.lastID.get());
        newEnvironment.entities = this.entities.stream().map(entity -> (PEntity)entity.deepCopy()).collect(Collectors.toList());
        newEnvironment.components = this.components.stream().map(comp -> (PComponent)comp.deepCopy()).collect(Collectors.toList());
        //copiare i nuovi parent dentro le nuove entità per avere i riferimenti corretti
        newEnvironment.components.forEach(
                comp -> comp.setParent(newEnvironment.entities.get(
                        newEnvironment.entities.indexOf(comp.getParent()))));

        for (var s: this.systems) {
            newEnvironment.systems.add((PSystem) s.deepCopy());
        }

        newEnvironment.systems = newEnvironment.systems.stream()
                .peek(PSystem::clear)
                .peek(sys -> sys.addComponents(newEnvironment.components))
                .collect(Collectors.toList());

        return newEnvironment;
    }

    @Override
    public String convertToString() {
        return "PEnvironent Not Implemented";
    }
}
