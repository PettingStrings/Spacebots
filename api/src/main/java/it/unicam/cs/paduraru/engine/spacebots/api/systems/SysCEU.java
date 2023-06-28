package it.unicam.cs.paduraru.engine.spacebots.api.systems;

import it.unicam.cs.paduraru.engine.DeepCopy;
import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PSystem;
import it.unicam.cs.paduraru.engine.Pair;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCEU;
import it.unicam.cs.paduraru.engine.spacebots.api.components.cCollider;

import java.util.List;
import java.util.stream.Collectors;

public class SysCEU extends PSystem implements DeepCopy {
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

    @Override
    public Object deepCopy() {
        SysCEU sys = new SysCEU();

        sys.components = this.components.stream()
                .map(com -> (PComponent)com.deepCopy()).collect(Collectors.toList());

        return sys;
    }
}
