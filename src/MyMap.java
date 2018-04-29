import java.util.Set;

/**
 * Date 4/21/18
 * Developer: Arshak Tovmasyan
 */
public interface MyMap<K,V> {
    public void clear();

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public Set<Entry<K,V>> entrySet();

    public V get(K key);

    public boolean isEmpty();

    public java.util.Set<K> keySet();

    public V put(K key, V value);

    public void remove(K key);

    public int size();

    public Set<V> values();

    public static class Entry<K,V>{
        K key;

        V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
