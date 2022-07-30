package org.springsalad.dsa.questions.leetcode0146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {

    class LRUQueueNode {
        Integer value;
        Integer key;
        LRUQueueNode next;
        LRUQueueNode parent;
        LRUQueueNode(Integer key, Integer value) {
            this.value = value;
            this.key = key;
        }
        LRUQueueNode() {

        }

    }

    LRUQueueNode head = new LRUQueueNode();
    LRUQueueNode tail = new LRUQueueNode();

    Map<Integer, LRUQueueNode> cache = new HashMap<>();
    int size ;
    int capacity;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head.next = tail;
        tail.parent = head;
    }

    public int get(int key) {

        LRUQueueNode n =  cache.get(key);
        if (n == null) {
            return -1;
        } else {
            n.parent.next = n.next;
            n.next.parent = n.parent;

            n.next = head.next;
            n.next.parent = n;
            head.next = n;
            n.parent = head;
            return n.value;
        }





    }

    public void put(int key, int value) {
        LRUQueueNode n =  cache.get(key);

        if (n != null) {
            n.parent.next = n.next;
            n.next.parent = n.parent;
            n.value = value;
        } else {
            size ++;
            if (size > capacity) {
                LRUQueueNode lastkey = tail.parent;
                lastkey.parent.next = tail;
                tail.parent = lastkey.parent;
                cache.remove(lastkey.key);
                size --;
            }

            n = new LRUQueueNode(key, value);

        }


        n.next = head.next;
        n.next.parent = n;
        head.next = n;
        n.parent = head;
        cache.put(key, n);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */