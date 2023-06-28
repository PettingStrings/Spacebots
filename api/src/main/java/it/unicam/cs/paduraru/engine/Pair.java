package it.unicam.cs.paduraru.engine;

public class Pair <T,R> implements DeepCopy{
    private final T first;
    private final R second;
    public Pair(T first, R second) {
        this.first = first;
        this.second = second;
    }
    public T getFirst() {
        return first;
    }
    public R getSecond() {
        return second;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Pair<T,R> other;
        try {
            other = (Pair<T, R>) obj;
        }catch (Exception e){
            return false;
        }
        return getFirst().equals(other.getFirst()) && getSecond().equals(other.getSecond());
    }

    @Override
    public Object deepCopy() {
        if( this.first instanceof DeepCopy && this.second instanceof DeepCopy)
            return new Pair<T,R>((T)((DeepCopy) this.first).deepCopy(), (R)((DeepCopy) this.second).deepCopy());

        if(this.first instanceof DeepCopy)
            return new Pair<T,R>((T)((DeepCopy) this.first).deepCopy(), this.second);

        if(this.second instanceof DeepCopy)
            return new Pair<T,R>(this.first, (R)((DeepCopy) this.second).deepCopy());

        return new Pair<T,R>(this.first, this.second);
    }
}
