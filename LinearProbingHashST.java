public class LinearProbingHashST<Key, Value>
{
    private final int M;
    private boolean useOldHash;
    private int comparisons;
    private final Value[] vals;
    private final Key[] keys;

    public LinearProbingHashST(int M, boolean useOldHash)
    {
        this.M = M;
        this.useOldHash = useOldHash;
        comparisons = 0;
        vals = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
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

    public void put(Key key, Value val)
    {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            comparisons++;
            if (keys[i].equals(key))
                break;
        }

        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public int getComparisons(){
        return comparisons;
    }

    public boolean contains(String word){
        for (Value val : vals) {
            if (val != null && val.equals(word))
                return true;
        }
        return false;
    }
}
