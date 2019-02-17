/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Array implementation of a stack that also resizes underlying
 *  array when stack is full. Pop also shrinks to save space.
 *
 **************************************************************************** */

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    // if underlying array is full, resize it to double current size
    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    // shrink underlying array when it is one-quarter full
    public String pop() {
        String item = s[--N];
        s[N] = null; // avoid loitering
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    // in order to resize underlying array we must copy over all current items
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) copy[i] = s[i];
        s = copy;
    }
}
