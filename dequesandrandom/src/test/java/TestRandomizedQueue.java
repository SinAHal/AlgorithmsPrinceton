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

public class TestRandomizedQueue extends TestCase {

    private static final int QUEUE_SIZE = 10;
    private RandomizedQueue<Integer> randomInts;

    protected void setUp() {
        randomInts = new RandomizedQueue<Integer>();
        for (int i=0; i<QUEUE_SIZE; i++) {
            randomInts.enqueue(i);
        }
    }

    public void testDequeing() {
        System.out.println("testing dequeue");
        ArrayList<Integer> added = new ArrayList<Integer>();
        for (int i=0; i<QUEUE_SIZE; i++) {
            assertFalse(randomInts.isEmpty());
            int dqd = randomInts.dequeue();
            assertFalse(added.contains(dqd));
            added.add(dqd);
            System.out.print(dqd+" ");
        }
        System.out.println();
        assertTrue(randomInts.isEmpty());
        assertEquals(0, randomInts.size());
    }

    public void testSampling() {
        System.out.println("testing sampling");
        for (int i=0; i<QUEUE_SIZE; i++) {
            int dqd = randomInts.sample();
            System.out.print(dqd+" ");
        }
        System.out.println();
        assertEquals(QUEUE_SIZE, randomInts.size());
    }

    public void testIterator() {
        System.out.println("first iteration");
        ArrayList<Integer> it1 = new ArrayList<Integer>();
        for(Integer item : randomInts) {
            it1.add(item);
            System.out.print(item+" ");
        }
        System.out.println();
        System.out.println("second iteration");
        ArrayList<Integer> it2 = new ArrayList<Integer>();
        for(Integer item : randomInts) {
            it2.add(item);
            System.out.print(item+" ");
        }
        System.out.println();

        boolean equal = true;
        for (int i=0; i<it1.size(); i++)
            if( !it1.get(i).equals(it2.get(i)) ) {
                equal = false;
                break;
            }

        assertFalse(randomInts.isEmpty());
        assertEquals(QUEUE_SIZE, randomInts.size());
        assertFalse(equal);
    }

}
