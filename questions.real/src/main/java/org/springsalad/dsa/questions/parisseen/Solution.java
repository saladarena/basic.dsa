package org.springsalad.dsa.questions.parisseen;


import java.util.TreeSet;

/***
 *  see each other
 *
 */
public class Solution {

    public int findPairs(int [] h) {
        if (h == null || h.length <2) {
            return 0;
        }
        var highest = new TreeSet<Integer>();
        int sum = 0;

        for (int i = 1; i < h.length; i ++) {
            // System.out.println("before remove: " + highest);
            var lowerSet = highest.headSet(h[i -1], true);
            // System.out.println("lowerSet: " + lowerSet);
            highest.removeAll(lowerSet);
            // System.out.println("after remove: " + highest);
            highest.add(h[i-1]);
            sum += highest.size();
        }

        return sum;

    }
}
