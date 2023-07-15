package it.unicam.cs.paduraru.engine.spacebots.api;

import it.unicam.cs.paduraru.engine.PDeepCopy;

/**
 * Rappresenta una Label nella simulazione dei Robot.
 * Viene automaticamente aggiunto '_' alla fine della Label sel caso in cui esso non sia presente.
 */
public class PLabel implements PDeepCopy {
    String name;

    public PLabel(String name) {
        if(!name.endsWith("_")) name += "_";
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PLabel)) return false;
        return name.equals(((PLabel)o).name);
    }

    @Override
    public Object deepCopy() {
        return new PLabel(this.name);
    }

    @Override
    public String convertToString() {
        return null;
    }
}
