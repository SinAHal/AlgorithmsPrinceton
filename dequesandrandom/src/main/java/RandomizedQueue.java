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
    private Item[] arr; // underlying array
    private int first, last = 0; // state of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return last == 0; }

    // return the number of items on the randomized queue
    public int size() { return last; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (last > 0 && last == arr.length)
            resize(2 * arr.length);

        arr[last++] = item;
    }

    // remove and return a random item
    // Worst case Needs to be CONSTANT Amortized time i.e. O(~1)
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int chosen = StdRandom.uniform(last);
        Item picked = pickRandomItem(chosen);
        removeItem(chosen);
        return picked;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int chosen = StdRandom.uniform(last);
        Item picked = pickRandomItem(chosen);
        return picked;
    }

    // return random item
    private Item pickRandomItem(int ind) {
        Item item = arr[ind];
        if (last > 0 && last == arr.length / 4)
            resize(arr.length / 2);
        return item;
    }

    // remove the item at index
    private void removeItem(int ind) {
        arr[ind] = arr[last-1];
        arr[last-1] = null;
        last--;
    }

    // first = 0, last = capacity-1 if capacity < last
    private void resize(int capacity) {
        Item[] resized = (Item[]) new Object[capacity];
        for (int i = first, j = 0; i < last; i++, j++)
            resized[j] = arr[i];
        arr = resized;
        first = 0;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private RandomizedQueue<Item> qCopy; // copy of the queue

        // initialize copy of the queue
        public RandomizedQueueIterator() {
            qCopy = new RandomizedQueue<Item>();
            for (int i = 0; i < last; i++)
                qCopy.enqueue(arr[i]);
        }

        public boolean hasNext() { return qCopy.last > 0; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = qCopy.dequeue();
            return item;
        }
    }
}