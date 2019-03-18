import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class BruteCollinearPoints {

    private LinkedList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        segments = new LinkedList<LineSegment>();
        findSegments(points, 4);
    }

    private void validatePoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        if (points[points.length-1] == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length-1; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            // should be no repeated points
            if (points[i].compareTo(points[i+1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // Returns each line segment containing 4 points exactly once
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private boolean isCollinear(Point[] points) {
        double slope = points[0].slopeTo(points[1]);
        for (int i = 1; i < points.length-1; i++) {
            if (points[i].slopeTo(points[i+1]) != slope)
                return false;
        }
        return true;
    }

    private void findSegments(Point[] combo, int cdx, int ndx, Point[] elems) {
        if (ndx == elems.length && cdx == combo.length && isCollinear(combo)) {
            // TODO: points might be out-of-order in segment, FIX ORDERING
            LineSegment seg = new LineSegment(combo[0], combo[cdx-1]);
            segments.add(seg);
        }

        if (ndx < elems.length) {
            if (cdx < combo.length) {
                combo[cdx] = elems[ndx];
                // TODO: introduce for efficiency
                // if (cdx > 0 && combo[cdx-1].compareTo(combo[cdx]) > 0)
                    findSegments(combo, cdx + 1, ndx + 1, elems);
            }

            findSegments(combo, cdx, ndx + 1, elems);
        }
    }

    private void findSegments(Point[] points, int r) {
        Point[] combination = new Point[r];
        findSegments(combination, 0, 0, points);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

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
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}