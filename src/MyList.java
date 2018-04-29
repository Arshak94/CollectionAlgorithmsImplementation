/**
 * Date 3/20/18
 * Developer: Arshak Tovmasyan
 */
public interface MyList<E> extends Iterable<E>{

    /** Add new element at the end of list */
    public void add(E e);

    /**Add a new element at the specific index in this list*/
    public void add(int index, E e);

    /**Clear the list*/
    public void clear();

    /**Return true if this list contains this element*/
    public boolean contains(E e);

    /**Return the element from this list at the specific index*/
    public E get(int index);

    /**Return the first matching element from this list and return -1 if element does not exist*/
    public int indexOf(E e);

    /**Return true if this list does not contain any elements*/
    public boolean isEmpty();

    /**Return the index of last matching element in this list and return -1 if not matching*/
    public int lastIndexOf(E e);

    /**Remove the first occurrence of the element e from this list.
     * Shift any subsequent elements to the left and return true if the element is removed*/
    public boolean remove(E e);

    /**Remove the element at the specified position in this list
     *Shift any subsequent elements to the left and return the removed element from list*/
    public E remove(int index);

    /**Replace the element at the specified position in this list with the specified element and return the old element*/
    public Object set(int index, E e);
    /**Return the number of elements in this list*/
    public int size();

}
