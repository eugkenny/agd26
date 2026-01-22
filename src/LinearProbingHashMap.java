public class LinearProbingHashMap<K,V> {

    private int capacity;
    private int size;
    private MapEntry<K,V> [] table;

    public LinearProbingHashMap(int initCapacity){
        capacity = initCapacity;
        table = (MapEntry<K,V> []) new MapEntry[capacity];
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % table.length;
    }

    public V get(K key){
        int index;
        int i = 1;
        int initIndex = hash(key);
        for(index = initIndex; table[index] != null; i++){
            if((table[index].key).equals(key)){
                return table[index].value;
            }
            index = (initIndex + i) % capacity;
        }
        return null;
    }

    public V put(K key, V value){

        if (size >= capacity){
            resize(2 * capacity);
        }

        V oldValue = null;
        int index;
        int i = 1;
        int initIndex = hash(key);
        for(index = initIndex; table[index] != null; i++){
            if((table[index].key).equals(key)){
                oldValue = table[index].value;
                table[index].value = value;
                return oldValue;
            }
            index = (initIndex + i) % capacity;
        }
        table[index] = new MapEntry<K,V>(key, value);
        size++;

        return oldValue;
    }

    public int size(){
        return size;
    }

    protected void resize(int newCapacity){
        MapEntry<K, V>[] temp = table;

        table = (MapEntry<K, V>[]) new MapEntry[newCapacity];
        size = 0;
        capacity = newCapacity;

        for(MapEntry<K, V> entry : temp){
            if(entry != null){
                this.put(entry.key, entry.value);
            }
        }
    }

    protected static class MapEntry<K, V> {
        protected K key;
        protected V value;
        protected boolean removed;

        public MapEntry(K key, V value){
            this.key = key;
            this.value = value;
            removed = false;
        }

        public String toString(){
            return "<" + key.toString() + "," + value.toString() + ">";
        }
    }
}
