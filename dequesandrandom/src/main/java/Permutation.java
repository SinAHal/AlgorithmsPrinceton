/* *****************************************************************************
 *  Name:    Sinan
 *  NetID:   shal
 *  Precept: P00
 *
 *  Description:  Client program that takes an integer k as a command;
 *  reads in a sequence of strings from standard input using StdIn.readString();
 *  and prints exactly k of them, uniformly at random.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomStrings = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            randomStrings.enqueue(StdIn.readString());
        }

        for (String item : randomStrings)
            StdOut.println(item);
    }
}
