import java.util.Iterator;

/**
 * Date 3/20/18
 * Developer: Arshak Tovmasyan
 */
public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY=16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    /**
     * Create new default list
     */
    public MyArrayList(){

    }

    /**
     * Create new list
     * @param objects
     */
    public MyArrayList(E[] objects){
        for (int i = 0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + "]";
    }
    /**
     * Add element in this list of the specified index and any subsequent array shift to the right
     */
    @Override
    public void add(int index, E e) {
        ensureCapasity();
        for (int i = size-1; i>=index; i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }

    /**
     * Create new larger array
     */
    private void ensureCapasity(){
        if (size>=data.length){
            E[] newData = (E[])(new Object[size+size/2]);
            for (int i=0;i<size;i++){
                newData[i]=data[i];
            }
            data=newData;
        }
    }

    /**
     * clear the list
     */
    @Override
    public void clear() {
        data = (E[])(new Object[INITIAL_CAPACITY]);
        size=0;
    }

    /**
     * If this list contains the specific element return true else return false
     * @param e
     * @return true or false
     */
    @Override
    public boolean contains(E e) {
        for (int i = 0; i<size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * If specific index less than 0 or big than size return element of the specific index
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * check that index found in the range
     * @param index
     */
    private void checkIndex(int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("index"+index+"out of bounds");
        }
    }

    /**
     * return index of the first matching element
     * @param e
     * @return
     */
    @Override
    public int indexOf(E e) {
        for (int i=0; i<size; i++){
            if (e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * return index of the last matching element
     * @param e
     * @return
     */
    @Override
    public int lastIndexOf(E e) {
        for (int i = size-1; i>=0; i++){
            if (e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * if index in the range of size remove from list element of the index and any subsequent array shift to the left
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = data[index];
        for (int i = index; i<size; i++){
            data[i]=data[i+1];
        }
        data[size-1]=null;
        size--;
        return e;
    }

    /**
     * if index in the range of size remove from list element of the index and assign the specific element in that index
     * @param index
     * @param e
     * @return
     */
    @Override
    public Object set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index]=e;
        return old;
    }

    /**
     * Trims the capacity to current size
     */
    public void trimToSize(){
        if (size!=data.length){
            E[] newData = (E[])(new Object[size]);
            for (int i = 0; i<size; i++){
                newData[i]=data[i];
            }
            data=newData;
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements Iterator<E>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return (current<size);
        }

        @Override
        public E next() {
            return data[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(current);
        }
    }
}
