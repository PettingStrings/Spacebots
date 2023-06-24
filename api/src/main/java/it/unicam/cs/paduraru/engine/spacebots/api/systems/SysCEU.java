package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.Component;
import it.unicam.cs.paduraru.engine.System;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;

import java.util.List;

public class SysCEU extends System {
    @Override
    public void addComponents(List<Component> components) {
        components.stream().forEach(comp -> addComponent(comp));
    }

    @Override
    public void run() throws Exception {
        components.stream().map(component -> (cCEU)component).forEach(cCEU::executeNextLine);
    }

    @Override
    public void addComponent(Component comp) {
        if(!(comp instanceof cCEU)) return;
        components.add(comp);
    }
}
