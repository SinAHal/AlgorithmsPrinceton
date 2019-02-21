/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Implementation of the randomized queue data structure
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    // add the item
    public void enqueue(Item item) {
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

    // remove and return a random item
    // Worst case Needs to be CONSTANT Amortized time i.e. O(~1)
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;

        int chosen = StdRandom.uniform(size);
        if (chosen > size / 2) {
            Node found = findFromEnd(last, chosen);
            removeNode(found);
            return found.item;
        }
        else {
            Node found = findFromStart(first, chosen);
            removeNode(found);
            return found.item;
        }
    }

    // not O(1)
    private Node findFromStart(Node curr, int pos) {
        if (pos == 0) return curr;
        pos--;
        return findFromStart(curr.next, pos);
    }

    // not O(1)
    private Node findFromEnd(Node curr, int pos) {
        if (pos == size-1) return curr;
        pos++;
        return findFromStart(curr.prev, pos);
    }

    private void removeNode(Node curr) {
        if (curr.prev == null) {
            first = curr.next;
        }
        else if (curr.next == null) {
            last = curr.prev;
        }
        else {
            Node before = curr.prev;
            Node after = curr.next;
            before.next = after;
            after.prev = before;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int chosen = StdRandom.uniform(size);
        if (chosen > size / 2) {
            Node found = findFromEnd(last, chosen);
            return found.item;
        }
        else {
            Node found = findFromStart(first, chosen);
            return found.item;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    private class RandomizedQueueIterator implements Iterator<Item> {
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

    }
}