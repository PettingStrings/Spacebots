package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.DeepCopy;
import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.PCollider;

import java.util.List;
import java.util.stream.Collectors;

public class SysCEU extends PSystem implements DeepCopy {
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
    @Override
    public void addComponent(PComponent comp) {
        if(!(comp instanceof PCEU)) return;
        getComponents().add(comp);
    }
    @Override
    public Object deepCopy() {
        SysCEU sys = new SysCEU();

        sys.setComponents(getComponents().stream()
                .map(com -> (PComponent)com.deepCopy()).collect(Collectors.toList()));

        return sys;
    }
}
