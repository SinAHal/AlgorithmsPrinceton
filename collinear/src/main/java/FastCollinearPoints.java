/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class FastCollinearPoints {

    private LinkedList<LineSegment> segments;
    private LinkedList<TempSegment> tempSegs;

    public FastCollinearPoints(Point[] points) {
        validatePoints(points);
        segments = new LinkedList<LineSegment>();
        tempSegs = new LinkedList<TempSegment>();
        findSegments(points);
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

    private void gatherSegment(Point[] points) {
        LinkedList<Point> pointsAccumulator = new LinkedList<Point>();
        pointsAccumulator.add(points[0]);

        for (int i = 1; i < points.length; i++) {
            pointsAccumulator.add(points[i]);
            double thisSlope = points[0].slopeTo(points[i]);
            double nextSlope = i < points.length-1 ? points[0].slopeTo(points[i+1]) : thisSlope;

            if(nextSlope != thisSlope) {
                if (pointsAccumulator.size() >= 3) {
                    LineSegment ls = new LineSegment(pointsAccumulator.getFirst(), pointsAccumulator.getLast());
                    TempSegment tempSegment = new TempSegment(ls, pointsAccumulator.size(), thisSlope);
                    addSegment(tempSegment);
                }

                pointsAccumulator = new LinkedList<Point>();
                pointsAccumulator.add(points[0]);
            }
            else {
                pointsAccumulator.add(points[i]);
            }
        }
    }

    private void addSegment(TempSegment tempSegment) {
        for (TempSegment ts : tempSegs) {
            if(ts.slope == tempSegment.slope && ts.size <= tempSegment.size) {
                tempSegs.remove(ts);
            }
        }
        tempSegs.add(tempSegment);
    }

    private void findSegments(Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        // insert line segments into segments
        for (Point point : points) {
            Comparator<Point> newOrder = point.slopeOrder();
            Arrays.sort(pointsCopy,newOrder);
            pointsAndSlopes(pointsCopy);
            gatherSegment(pointsCopy);
        }

        for(TempSegment t : tempSegs)
            segments.add(t.segment);
    }

    private void pointsAndSlopes(Point[] points) {
        Point head = points[0];
        System.out.println("Head: "+head);
        for(int i=1; i<points.length; i++) {
            System.out.println(points[i]+" "+head.slopeTo(points[i]));
        }
        System.out.println();
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private class TempSegment {
        private LineSegment segment;
        public int size;
        public double slope;

        public TempSegment(LineSegment segment, int size, double slope) {
            this.segment = segment;
            this.size = size;
            this.slope = slope;
        }
    }
}