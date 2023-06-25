package it.unicam.cs.paduraru.engine;

public class Pair <T,R>{
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
}
