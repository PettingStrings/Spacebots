package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.PDeepCopy;
import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sistema che si occupa di eseguire il codice dei componenti {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU}.
 */
public class PSysCEU extends PSystem implements PDeepCopy {
    /**
     * Aggiunge solo elementi di una lista appartenenti a {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU}.
     * @param components Lista contenente gli elementi da aggiungere.
     */
    @Override
    public void addComponents(List<PComponent> components) {
        getComponents().addAll(components.stream()
                .filter(component -> component instanceof PCEU)
                .toList());
    }
    @Override
    public void run(){
        getComponents().stream().map(component -> (PCEU)component).forEach(PCEU::executeNextLine);
    }

    /**
     * Aggiunge solo elementi appartenenti a {@link it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU}.
     * @param comp Elemento da aggiungere.
     */
    @Override
    public void addComponent(PComponent comp) {
        if(!(comp instanceof PCEU)) return;
        getComponents().add(comp);
    }
    @Override
    public Object deepCopy() {
        PSysCEU sys = new PSysCEU();

        sys.setComponents(getComponents().stream()
                .map(com -> (PComponent)com.deepCopy()).collect(Collectors.toList()));

        return sys;
    }

    @Override
    public String convertToString() {
        return "PSysCEU Not Implemented";
    }
}
