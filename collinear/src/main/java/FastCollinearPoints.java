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

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validatePoints(points);
        segments = new LinkedList<LineSegment>();
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

    private LineSegment gatherSegment(Point[] points) {
        Point[] pointsAccumulator = new Point[points.length];
        pointsAccumulator[0] = points[0];
        pointsAccumulator[1] = points[1];
        int accumulatorSize = 2;

        double headSlope = points[0].slopeTo(points[1]);
        for (int i = 2; i < points.length; i++) {
            double nextSlope = points[0].slopeTo(points[i]);
            if (headSlope == nextSlope) {
               pointsAccumulator[i] = points[i];
               accumulatorSize++;
            }
            else {
                break;
            }
        }

        if (accumulatorSize > 3)
            return new LineSegment(pointsAccumulator[0], pointsAccumulator[accumulatorSize-1]);
        else
            return null;
    }

    private void findSegments(Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        // insert line segments into segments
        for (Point point : points) {
            Comparator<Point> newOrder = point.slopeOrder();
            Arrays.sort(pointsCopy,newOrder);
            LineSegment segment = gatherSegment(pointsCopy);
            if(segment != null)
                segments.add(segment);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}