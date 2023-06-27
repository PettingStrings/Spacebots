package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.ASystem;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;

import java.util.List;

public class SysCEU extends ASystem {
    @Override
    public void addComponents(List<PComponent> components) {
        components.stream().forEach(comp -> addComponent(comp));
    }

    @Override
    public void run(){
        components.stream().map(component -> (cCEU)component).forEach(cCEU::executeNextLine);
    }

    @Override
    public void addComponent(PComponent comp) {
        if(!(comp instanceof cCEU)) return;
        components.add(comp);
    }
}
