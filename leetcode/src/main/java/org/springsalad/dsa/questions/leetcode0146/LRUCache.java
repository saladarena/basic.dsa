package org.springsalad.dsa.questions.leetcode0146;

import java.util.HashMap;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;
    ListNode pre;
    ListNode (int val) {
        this.val = val;
    }

    void reset() {
        next = null;
        pre = null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Node:");
        sb.append(val);

        return sb.toString();


    }
}

class LinkedListQueue {
    ListNode head;
    ListNode tail;

    // reture the existing removedNode
    public  ListNode remove(ListNode node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        } else {
            this.head = node.next;
        }

        if (node.next != null) {
            node.next.pre = node.pre;
        } else {
            this.tail = node.pre;
        }
        return node;
    }

    public void offer(ListNode node) {

        node.reset();

        if (this.tail != null) {
            node.pre = tail;
            tail.next = node;
            this.tail = node;
        } else {
            this.head = node;
            this.tail = node;

        }
    }

    public ListNode poll() {
        if (head == null) {
            return null;
        } else {
            return remove(head);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            sb.append(',');
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();

    }
}

public class LRUCache {

    int capacity;
    int size = 0;



    // key - value
    Map<Integer, Integer> pairs = new HashMap<>();

    // key - Node
    Map<Integer, ListNode> indexes= new HashMap<>();

    LinkedListQueue q = new LinkedListQueue();

    public LRUCache(int capacity) {
        this.capacity  = capacity;

    }

    public int get(int key) {

        // System.out.println("get:" + "key: " + key );

        ListNode  node = indexes.get(key);
        if (node == null) {
            return -1;
        } else {
            Integer value = pairs.get(key);

            node = q.remove(node);
            q.offer(node);
            // System.out.println("get after offer queue:" + this.q);

            return value;
        }

    }

    public void put(int key, int value) {

        // System.out.println("put:" + "key: " + key + " value: " + value);
        // System.out.println("queue:" + this.q);


        ListNode  node = indexes.get(key);

        // key  does not exist
        if (node == null) {
            if (size >= capacity) {
                // remove the LRU one
                ListNode LRUNode = q.poll();

                pairs.remove(LRUNode.val);
                indexes.remove(LRUNode.val);


            }
            size ++;
            node = new ListNode(key);

        }// if key exist
        else {
            q.remove(node);
        }
        // what ever, put the key value
        pairs.put(key, value);
        //System.out.println("offer node:" + node);
        q.offer(node);
        //System.out.println("after offer queue:" + this.q);
        indexes.put(key, node);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */