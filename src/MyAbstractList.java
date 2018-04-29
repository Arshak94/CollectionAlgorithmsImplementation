/**
 * Date 3/20/18
 * Developer: Arshak Tovmasyan
 */
public abstract class MyAbstractList<E> implements MyList<E> {

    protected int size = 0;

    /**
     * Create a default list
     */
    protected MyAbstractList() {

    }

    /**
     * Create new list from an array of objects
     */
    protected MyAbstractList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    /**Add a new element at the end of the list*/
    @Override
    public void add(E e) {
        add(size, e);
    }

    /**return true if this list does not contains any elements*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * If this list contains the element remove the element from list and shift any
     * subsequent elements to the left return true if the element is removed
     */
    @Override
    public boolean remove(E e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else
            return false;
    }

    /**Return the number of elements in this list*/
    @Override
    public int size() {
        return size;
    }
}
