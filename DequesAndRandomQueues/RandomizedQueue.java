/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Implementation of the randomized queue data structure
 *
 **************************************************************************** */

// TODO: might be best to use resizing array
// enqueue -> if queue full && arr[0] == null, shift items to left by copying over to same size array
// else if full && arr[0] != null, double size of arr and copy over

// dequeue -> shrinking algorithm is trickier, both sides need trimming
// need to keep track of head and tail index
// (tail - head) == s.length / 4 => halve array and copy over items
public class RandomizedQueue {
    public static void main(String[] args) {

    }
}
