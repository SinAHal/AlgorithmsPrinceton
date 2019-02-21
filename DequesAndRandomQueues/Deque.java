/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  An implementation of the double-ended queue data structure.
 *  Uses a doubly linked list
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (oldfirst != null) oldfirst.prev = first;
        if (last == null) last = first;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;

        Item item = first.item;
        first = first.next;
        first.prev = null;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;

        Item item = last.item;
        if (last.prev == null) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> ds = new Deque<>();
        for(int i=1; i<=10; i++) {
            ds.addLast(i);
        }
        StdOut.println(ds.removeLast());
        StdOut.println(ds.removeLast());
        StdOut.println(ds.removeLast());
        StdOut.println(ds.removeFirst());
        StdOut.println(ds.removeFirst());

        for(int x : ds) StdOut.print(x+" ");
    }
}
