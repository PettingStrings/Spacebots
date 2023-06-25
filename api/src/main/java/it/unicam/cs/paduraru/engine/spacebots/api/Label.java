package it.unicam.cs.paduraru.engine.spacebots.api;

import java.util.Objects;

public class Label {
    String name;

    public Label(String name) throws Exception {
        if(!name.endsWith("_")) throw new Exception("Label must end with '_'");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label label)) return false;
        return name.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
