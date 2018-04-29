import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Date 4/22/18
 * Developer: Arshak Tovmasyan
 */
public class MyHashSet<E> implements MySet<E> {

    private static int DEFAULT_INITIAL_CAPACITY = 4;

    private static int MAXIMUM_CAPACITY = 1 << 30;

    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    private int capacity;

    private float loadFactorThreshold;

    private int size = 0;

    private LinkedList<E>[] table;

    public MyHashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        this.table = new LinkedList[capacity];
    }

    @Override
    public void clear() {
        this.size = 0;
        removeElements();
    }


    @Override
    public boolean contains(E e) {
        int hashBucketIndex = hash(e.hashCode());
        if (table[hashBucketIndex] != null) {
            LinkedList<E> bucket = table[hashBucketIndex];
            for (E element : bucket) {
                if (element.equals(e))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if (contains(e))
            return false;
        if (size + 1 > capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<E>();
        }
        table[bucketIndex].add(e);
        size++;

        return true;
    }



    @Override
    public boolean remove(E e) {

        if (!contains(e)){
            return false;
        }
        int buvketIndex = hash(e.hashCode());
        if (table[buvketIndex]!=null){
            LinkedList<E> bucket = table[buvketIndex];
            for (E elements :
                    bucket) {
                if (e.equals(elements)) {
                    bucket.remove(e);
                    break;
                }
                }
        }
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new MyHashsetIterator(this);
    }

    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private int supplementalHash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        return capacity;
    }

    private void removeElements() {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    private void rehash() {
        ArrayList<E> list = setToList();
        capacity<<=1;
        table = new LinkedList[capacity];
        size = 0;
        for (E element:list){
            add(element);
        }
    }

    private ArrayList<E> setToList() {
        ArrayList<E> list = new ArrayList<>();
        for (int i = 0; i<capacity; i++){
            if (table[i]!=null){
                for (E e :
                        table[i]) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    private class MyHashsetIterator implements Iterator<E> {

        private ArrayList<E> list;

        private int current;

        private MyHashSet<E> set;

        public MyHashsetIterator(MyHashSet<E> set) {
            this.set = set;
            list = setToList();
        }

        @Override
        public boolean hasNext() {
            if (current<list.size()){
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            set.remove(list.get(current));
            list.remove(current);
        }
    }
}
