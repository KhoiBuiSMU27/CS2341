public class SeparateChainingHashST<Key, Value>
{
    private int M; // hash table size
    private Node[] st;// array of linked-list symbol tables
    private boolean useOldHash; // flag to switch between hash functions
    private int comparisons; // count the cost of the search

    private static class Node
    {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // create separate chaining hash table with specified size
    public SeparateChainingHashST(int M, boolean useOldHash)
    {
        this.M = M;
        this.useOldHash = useOldHash;
        st = new Node[M];
        comparisons = 0;
    }

    private int hash(Key key)
    {
        if (useOldHash) {
            return (oldHashCode(Integer.toString((Integer) key)) & 0x7fffffff) % M;
        } else {
            return (newHashCode(Integer.toString((Integer) key)) & 0x7fffffff) % M;
        }
    }

    // old hash function implementation
    private int oldHashCode(String str)
    {
        int hash = 0;
        int skip = Math.max(1, str.length() / 8);
        for (int i = 0; i < str.length(); i += skip) {
            hash = (hash * 37) + str.charAt(i);
        }
        return hash;
    }

    // new hash function implementation
    private int newHashCode(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * 31) + str.charAt(i);
        }
        return hash;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val)
    {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            comparisons++;
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
    }

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }

    public int getComparisons(){
        return comparisons;
    }
}