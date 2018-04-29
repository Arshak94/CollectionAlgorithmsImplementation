/**
 * Date 4/22/18
 * Developer: Arshak Tovmasyan
 */
public interface MySet<E> extends Iterable {

    public void clear();

    public boolean contains(E e);

    public boolean add(E e);

    public boolean remove(E e);

    public boolean isEmpty();

    public int size();
}
