/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  A Resizing Array implementation of a queue
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int first, last = 0;

    public ResizingArrayQueue() {
        arr = (Item[]) new Object[1];
    }

    public boolean isEmpty() { return arr[first] == null; }

    public int size() { return last - first; }

    // if space in front of arr => shrink, else if full double and copy
    public void enqueue(Item item) {
        int s = size();
        if (s > 0 && s == arr.length / 4)
            resize((arr.length / 2) + 1);
        else if (s > 0 && last == arr.length)
            resize(2 * arr.length);

        arr[last++] = item;
    }

    public Item dequeue() {
        Item item = arr[first];
        arr[first++] = null;
        int s = size();
        if (s > 0 && s == arr.length / 4)
            resize(arr.length / 2);
        return item;
    }

    // first = 0, last = capacity-1 if capacity < last
    private void resize(int capacity) {
        Item[] resized = (Item[]) new Object[capacity];
        for (int i = first, j = 0; i < last; i++, j++)
            resized[j] = arr[i];
        arr = resized;
        last = size();
        first = 0;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new QueueIterator(); }

    private class QueueIterator implements Iterator<Item> {
        private int current = first;

        public boolean hasNext() { return current < last; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = arr[current++];
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        ResizingArrayQueue<Integer> ds = new ResizingArrayQueue<>();
        for (int i=1; i<=10; i++) {
            ds.enqueue(i);
        }
        for(int x : ds) StdOut.print(x+" ");

        StdOut.println();
        StdOut.println();
        for (int i=1; i<=8; i++)
            ds.dequeue();
        for(int x : ds) StdOut.print(x+" ");
    }
}