import java.util.Arrays;

public class BruteCollinearPoints {

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }

    private static void recursiveCombinations(Object[] combo, int cdx, int ndx, Object[] elems) {
        if(ndx == elems.length && cdx == combo.length)
            System.out.println("** "+ Arrays.toString(combo));

        if(ndx < elems.length) {
            if(cdx < combo.length) {
                combo[cdx] = elems[ndx];
                recursiveCombinations(combo, cdx + 1, ndx + 1, elems);
            }

            recursiveCombinations(combo, cdx, ndx + 1, elems);
        }
    }

    public static void recursiveCombinations(Integer[] points, int r) {
        Object[] combination = new Object[r];
        recursiveCombinations(combination, 0, 0, points);
    }
}