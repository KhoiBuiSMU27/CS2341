public class MinPQ {

    private Job[] pq;
    private int n;

    // Constructor to initialize the priority queue
    public MinPQ(int capacity) {
        pq = new Job[capacity + 1]; // pq[0] is unused
        n = 0;
    }

    // Insert a job into the priority queue
    public void insert(Job job) {
        pq[++n] = job;
        swim(n);
    }

    // Delete and return the minimum job
    public Job delMin() {
        Job min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return min;
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return n == 0;
    }

    // Size of the priority queue
    public int size() {
        return n;
    }

    // Swim to restore heap order by moving the element up
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    // Sink to restore heap order by moving the element down
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    // Compare the processing times of two jobs
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // Exchange two elements in the heap
    private void exch(int i, int j) {
        Job swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
}
