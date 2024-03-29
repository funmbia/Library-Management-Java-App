package iterator;

public interface Iterator<T> {
    Object recommendation = null;
	T first();
    T next();
    T currItem();
    boolean hasNext();
	T remove();
}
