class LRUCache {
    class Node {
        int key;
        int value;
        Node prev, next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    HashMap<Integer, Node> map;
    Node front, last;
    int count, capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        front = new Node(-1, -1);
        last = new Node(-1, -1);
        front.next = last;
        last.prev = front;
        count = 0;
        this.capacity = capacity;
    }
    
    private void add(Node node) {  
        node.next = front.next;
        front.next.prev = node;
        node.prev = front;
        front.next = node;
    }
    
    private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(node);
        add(node);  
        return node.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            map.remove(key);
            count--;
        }
        Node node = new Node (key, value);
        add(node);
        map.put (key, node);
        count++;
        if (count>capacity){
            System.out.println(last.prev.value);
            Node removed = last.prev;
            remove(removed);
            map.remove(removed.key);
            count--;
        }
    }
}
