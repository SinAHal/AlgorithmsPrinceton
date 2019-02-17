/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Array implementation of a stack with a fixed size.
 *  s[N] is the next empty slot on the stack
 *  s[N-1] is where the next item to be popped is
 *
 *  loitering: we no longer refer to certain parts of the data structure, but
 *  old items still exist there (should reassign to null, avoid loitering)
 *
 **************************************************************************** */

public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item; // N is incremented after item inserted into s[N]
    }

    // This version of pop() will loiter.
    public String pop() {
        return s[--N]; // N decremented before returning s[N]
    }
}
