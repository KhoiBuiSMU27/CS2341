import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return size; }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {}

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

    }
}
