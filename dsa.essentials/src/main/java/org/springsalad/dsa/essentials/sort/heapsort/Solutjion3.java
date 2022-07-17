package org.springsalad.dsa.essentials.sort.heapsort;

import java.util.PriorityQueue;

public class Solutjion3 {


    public static void main(String ...args) {
        PriorityQueue<Number> pq = new PriorityQueue<>();

        pq.offer(3);

        pq.offer(5);
        pq.offer(6);
        pq.offer(4);
        pq.offer(2);
        pq.remove(3);

        System.out.println("pq is" + pq);

    }

}
