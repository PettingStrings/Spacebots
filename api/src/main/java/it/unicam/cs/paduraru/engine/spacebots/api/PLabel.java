package it.unicam.cs.paduraru.engine.spacebots.api;

import it.unicam.cs.paduraru.engine.DeepCopy;

import java.util.Objects;

public class PLabel implements DeepCopy {
    String name;

    public PLabel(String name) {
        //if(!name.endsWith("_")) throw new Exception("Label must end with '_'");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PLabel label)) return false;
        return name.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Object deepCopy() {
        return new PLabel(this.name);
    }
}
