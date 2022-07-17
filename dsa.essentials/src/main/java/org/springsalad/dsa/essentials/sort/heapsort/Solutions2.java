package org.springsalad.dsa.essentials.sort.heapsort;// This is a sandbox environment that allows you to experiment with CoderPad's execution capabilities.
// It's a temporary throw-away session only visible to you so you can test out the programming environment.
// Once you select a language, to execute your code simply hit the 'Run' button which will be located in the
// top left hand of your screen.
//
// To see more information about the language you have selected, hit the 'Info' button beside the language dropdown.
// You'll find what version of the language is running and the packages available for the given language.

/*
 * Click `Run` to execute the snippet below!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solutions2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        //Helper.testOneBST();
        Helper.testTwo();
    }

    public static void findAllWays(List<TreeNode> cList, List<TreeNode> currentWay, List<List<TreeNode>> allCompletedWays) {


        cList.forEach(node -> {
            List<TreeNode> newCurrentWay = new ArrayList<TreeNode>(currentWay);
            newCurrentWay.add(node);
            List<TreeNode> nextList = new ArrayList<TreeNode>(cList);
            nextList.remove(node);

            if (node.left == null && node.right == null) {
                if (nextList.size() == 0 ) {
                    // we got a completed way
                    allCompletedWays.add(newCurrentWay);
                    return;
                }
            } else {
                if (node.left != null ) {
                    nextList.add(node.left);
                }
                if (node.right != null) {
                    nextList.add(node.right);
                }
            }
            findAllWays(nextList, newCurrentWay, allCompletedWays);
        });

    }

    public static void startFindAllWays(TreeNode root) {
        List<TreeNode> cList = new ArrayList<>();
        cList.add(root);
        List<TreeNode> cWay = new ArrayList<>();

        List<List<TreeNode>> allCompletedWays = new ArrayList<List<TreeNode>>();

        findAllWays(cList, cWay, allCompletedWays);

        Helper.printAllWays(allCompletedWays);

    }
}

class TreeNode {
    public  int value;
    public  TreeNode left;
    public TreeNode right;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        sb.append(" " + value + ",");
        sb.append(left);
        sb.append(",");
        sb.append(right);
        sb.append(")");
        return sb.toString();
    }
}

class Helper {

    public static void printWay(List<TreeNode> way) {

        way.forEach (node -> {
            System.out.print(node.value + ",");
        });
    }

    public static void printAllWays(List<List<TreeNode>> AllWays) {

        AllWays.forEach (way -> {
            printWay(way);
            System.out.println();
        });
    }

    // just for test, quickly build up a bst tree using heap-like array
    public static TreeNode buildTestBST(int [] nums ) {

        TreeNode [] tempNodes = new TreeNode[nums.length];
        for (int i =0; i < nums.length; i++) {
            tempNodes[i] = new TreeNode();
            tempNodes[i].value = nums[i];

        }

        for (int i =0; i < nums.length; i++) {

            int hi = i+1;
            int left = hi * 2 -1;
            int right = hi* 2;

            if (left < nums.length ) {
                tempNodes[i].left = tempNodes[left];
            }
            if (right < nums.length) {
                tempNodes[i].right = tempNodes[right];
            }


        }

        return tempNodes[0];
    }

    public static void testOneBST() {
        int [] treeNumbers = {15, 7, 25, 4, 9, 12, 28, 2 };
        TreeNode root = buildTestBST(treeNumbers);
        //debug
        System.out.println("testOneBST: " + root);

        Solutions2.startFindAllWays(root);
    }

    public static void testTwo() {
        PriorityQueue<Number> pq = new PriorityQueue<>();

        pq.offer(3);
        pq.offer(4);
        pq.offer(5);
        pq.offer(6);

        System.out.println("pq is" + pq);

    }

} // Helper end


