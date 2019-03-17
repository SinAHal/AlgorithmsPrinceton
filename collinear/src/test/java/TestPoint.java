/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import junit.framework.TestCase;

public class TestPoint extends TestCase {
    public void testYDifference() {
        Point p1 = new Point(0,4);
        Point p2 = new Point(0,8);

        assertEquals(1, p2.compareTo(p1));
        assertEquals(-1, p1.compareTo(p2));
    }

    public void testXDifference() {
        Point p1 = new Point(2,0);
        Point p2 = new Point(7,0);

        assertEquals(1, p2.compareTo(p1));
        assertEquals(-1, p1.compareTo(p2));
    }

    public void testPointEquality() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        assertEquals(0, p2.compareTo(p1));
        assertEquals(0, p1.compareTo(p2));
    }

    public void testSlopePositive() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(4,5);

        assertEquals(1.0, p1.slopeTo(p2));
        assertEquals(1.0, p2.slopeTo(p1));
    }

    public void testSlopeNegative() {
        Point p1 = new Point(2,5);
        Point p2 = new Point(4,3);

        assertEquals(-1.0, p1.slopeTo(p2));
        assertEquals(-1.0, p2.slopeTo(p1));
    }

    public void testSlopeHorizontal() {
        Point p1 = new Point(2,0);
        Point p2 = new Point(4,0);

        assertEquals(0.0, p1.slopeTo(p2));
        assertEquals(0.0, p2.slopeTo(p1));
    }

    public void testSlopeVertical() {
        Point p1 = new Point(0,2);
        Point p2 = new Point(0,4);

        assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2));
        assertEquals(Double.POSITIVE_INFINITY, p2.slopeTo(p1));
    }

    public void testSlopeEqual() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);

        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2));
        assertEquals(Double.NEGATIVE_INFINITY, p2.slopeTo(p1));
    }

    public void testSlopeOrderGreater() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(2,5);
        Point p3 = new Point(2,3);

        assertEquals(1, p1.slopeOrder().compare(p2,p3));
    }

    public void testSlopeOrderLesser() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(2,3);
        Point p3 = new Point(2,5);

        assertEquals(-1, p1.slopeOrder().compare(p2,p3));
    }

    public void testSlopeOrderEqual() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(2,3);
        Point p3 = new Point(2,3);

        assertEquals(0, p1.slopeOrder().compare(p2,p3));
    }
}
