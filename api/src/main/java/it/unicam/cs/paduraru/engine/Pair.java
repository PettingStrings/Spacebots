package it.unicam.cs.paduraru.engine;
//records java 14+
public record Pair<T, R>(T first, R second) implements DeepCopy {
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if (obj == null) return false;
        if(!this.getClass().isInstance(obj)) return false;

        Pair<T, R> other = (Pair<T, R>) obj;

        return first().equals(other.first()) && second().equals(other.second());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deepCopy() {
        if (this.first instanceof DeepCopy && this.second instanceof DeepCopy)
            return new Pair<>((T) ((DeepCopy) this.first).deepCopy(), (R) ((DeepCopy) this.second).deepCopy());

        if (this.first instanceof DeepCopy)
            return new Pair<>((T) ((DeepCopy) this.first).deepCopy(), this.second);

        if (this.second instanceof DeepCopy)
            return new Pair<>(this.first, (R) ((DeepCopy) this.second).deepCopy());

        return new Pair<>(this.first, this.second);
    }
}
