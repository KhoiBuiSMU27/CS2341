import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first; // first node in list

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
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