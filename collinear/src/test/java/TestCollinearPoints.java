/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import junit.framework.TestCase;

public class TestCollinearPoints extends TestCase {

    private Point[] readPoints(String filename) {
        // read the n points from a file
        In in = new In(filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    private void outputPoints(Point[] points) {
        for (Point point : points) {
            System.out.print(point);
        }
        System.out.println();
    }

    public void testBrute() {
        Point[] points = readPoints("input8.txt");

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        LineSegment[] segs = collinear.segments();
        assertEquals("(10000, 0) -> (0, 10000)",segs[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)",segs[1].toString());
        for (LineSegment segment : segs) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public void testFastInput8() {
        Point[] points = readPoints("input8.txt");

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] segs = collinear.segments();
        assertEquals(2, segs.length);
        assertEquals("(3000, 4000) -> (20000, 21000)",segs[0].toString());
        assertEquals("(0, 10000) -> (10000, 0)",segs[1].toString());
        for (LineSegment segment : segs) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public void testFastInput6() {
        Point[] points = readPoints("input6.txt");

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] segs = collinear.segments();
        assertEquals(1, segs.length);
        assertEquals("(14000, 10000) -> (32000, 10000)",segs[0].toString());
        for (LineSegment segment : segs) {
            System.out.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
