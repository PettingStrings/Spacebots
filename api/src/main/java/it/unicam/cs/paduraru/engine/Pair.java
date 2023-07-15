package it.unicam.cs.paduraru.engine;

/**
 * Rappresenta una coppia di classi generiche.
 * @param first Primo elemento.
 * @param second Secondo elemento.
 * @param <T> Tipo del primo elemento.
 * @param <R> Tipo del secondo elemento.
 */
//records java 14+
public record Pair<T, R>(T first, R second) implements PDeepCopy {
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if (obj == null) return false;
        if(!this.getClass().isInstance(obj)) return false;

        Pair<T, R> other = (Pair<T, R>) obj;

        return first().equals(other.first()) && second().equals(other.second());
    }

    /**
     * Crea una nuova istanza di Pair.
     * Se gli elementi contenuti non implementano l'interfaccia {@link PDeepCopy}
     * vengono usate le stesse reference.
     * @return Nuova istanza di Pair.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy() {
        if (this.first instanceof PDeepCopy && this.second instanceof PDeepCopy)
            return new Pair<>((T) ((PDeepCopy) this.first).deepCopy(), (R) ((PDeepCopy) this.second).deepCopy());

        if (this.first instanceof PDeepCopy)
            return new Pair<>((T) ((PDeepCopy) this.first).deepCopy(), this.second);

        if (this.second instanceof PDeepCopy)
            return new Pair<>(this.first, (R) ((PDeepCopy) this.second).deepCopy());

        return new Pair<>(this.first, this.second);
    }
}
