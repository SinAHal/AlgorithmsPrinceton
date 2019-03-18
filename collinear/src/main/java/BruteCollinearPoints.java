import java.util.LinkedList;

public class BruteCollinearPoints {

    private LinkedList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        validatePoints(points);
        findSegments(points, 4);
    }

    private void validatePoints(Point[] points) {
        if(points == null)
            throw new IllegalArgumentException();

        if(points[points.length-1]==null)
            throw new IllegalArgumentException();

        for(int i=0; i<points.length-1; i++) {
            if(points[i]==null)
                throw new IllegalArgumentException();
            // should be no repeated points
            if(points[i].compareTo(points[i+1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // Returns each line segment containing 4 points exactly once
    public LineSegment[] segments() {
        return (LineSegment[]) segments.toArray();
    }

    private boolean isCollinear(Point[] points) {
        double slope = points[0].slopeTo(points[1]);
        for(int i=1; i<points.length-1; i++) {
            if(points[i].slopeTo(points[i+1]) != slope)
                return false;
        }
        return true;
    }

    private void findSegments(Point[] combo, int cdx, int ndx, Point[] elems) {
        if(ndx == elems.length && cdx == combo.length && isCollinear(combo)) {
            LineSegment seg = new LineSegment(combo[0], combo[cdx-1]);
            segments.add(seg);
        }

        if(ndx < elems.length) {
            if(cdx < combo.length) {
                combo[cdx] = elems[ndx];
                if(cdx>0 && combo[cdx-1].compareTo(combo[cdx])>0)
                    findSegments(combo, cdx + 1, ndx + 1, elems);
            }

            findSegments(combo, cdx, ndx + 1, elems);
        }
    }

    private void findSegments(Point[] points, int r) {
        Point[] combination = new Point[r];
        findSegments(combination, 0, 0, points);
    }
}