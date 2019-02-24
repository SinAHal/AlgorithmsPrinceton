/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Test class for RandomizedQueue
 *
 **************************************************************************** */

import junit.framework.TestCase;

import java.util.ArrayList;

public class TestDeque extends TestCase {

    public void testAddFirstAndRemoveLast() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Deque<Integer> dequeInts = new Deque<Integer>();
        int dequeSize = 13;
        for (int i=0; i < dequeSize; i++) {
            expected.add(i);
            dequeInts.addFirst(i);
        }
        for (int i=0; i < dequeSize; i++) {
            result.add(dequeInts.removeLast());
        }
        validateResult(expected, result);
    }

    public void testAddLastAndRemoveFirst() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Deque<Integer> dequeInts = new Deque<Integer>();
        int dequeSize = 13;
        for (int i=0; i < dequeSize; i++) {
            expected.add(i);
            dequeInts.addLast(i);
        }
        for (int i=0; i < dequeSize; i++) {
            result.add(dequeInts.removeFirst());
        }
        validateResult(expected, result);
    }

    public void testAddFirstAndLast() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        Deque<Integer> dequeInts = new Deque<Integer>();
        int dequeSize = 13;
        for (int i=7; i>=0; i--) expected.add(i);
        for (int i=8; i < dequeSize; i++) expected.add(i);

        for (int i=0; i <= 7; i++) {
            dequeInts.addFirst(i);
        }
        for (int i=8; i < dequeSize; i++) {
            dequeInts.addLast(i);
        }
        for (int i=0; i < dequeSize; i++) {
            result.add(dequeInts.removeFirst());
        }
        validateResult(expected, result);
    }

    private void validateResult(ArrayList<Integer> expected, ArrayList<Integer> result) {
        System.out.println(expected);
        System.out.println(result);
        assertEquals(expected.size(), result.size());
        for (int i=0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

}
