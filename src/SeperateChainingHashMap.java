public class SeperateChainingHashMap <K,V>{

    private MapNode [] map;
    private int M;
    private int size;

    public SeperateChainingHashMap(int M){
        this.M = M;
        map = new MapNode[M];
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }

    public V get(K key){
        int index = hash(key);
        for(MapNode<K,V> current = map[index]; current != null; current = current.next){
            if(key.equals(current.key)){
                return current.value;
            }
        }
        return null;
    }

    public V put(K key, V value){
        int index = hash(key);
        for(MapNode<K,V> current = map[index]; current != null; current = current.next){
            if(key.equals(current.key)){
                V old = current.value;
                current.value = value;
                return old;
            }
        }
        map[index] = new MapNode(key, value, map[index]);
        size++;
        return null;
    }

    public int size(){
        return size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++){
            for(MapNode<K,V> current = map[i]; current != null; current = current.next){
                sb.append(current + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    private static class MapNode<K,V>{
        private K key;
        private V value;
        private MapNode<K,V> next;

        MapNode(K key,V value, MapNode next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public String toString(){
            return "<" + key + "," + value + ">";
        }
    }

    public static void main(String[] args) {
        SeperateChainingHashMap<Integer, Integer> map = new SeperateChainingHashMap<>(5);
        map.put(10,10);
        map.put(12,12);
        map.put(20,20);

        System.out.println(map);


        System.out.println(map.get(12));
        System.out.println(map.get(15));

        System.out.println(map.size());

    }

}
