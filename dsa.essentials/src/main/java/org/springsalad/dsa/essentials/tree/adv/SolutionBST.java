package org.springsalad.dsa.essentials.tree.adv;

import java.util.ArrayList;
import java.util.List;

public class SolutionBST {

    class BSTNode {
        int val;
        BSTNode parent;
        BSTNode left;
        BSTNode right;
    }



    // ret null if not found, node is null
    BSTNode findNextBigger(BSTNode node) {
        // check right
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            // go up
            while (node.parent != null  && node == node.parent.right) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    // ret null if not found, node is not null
    BSTNode findNextSmaller(BSTNode node) {
        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
        } else {
            // up
            while ( node.parent != null && node == node.parent.left) {
                node = node.parent;
            }
            // parent == null, or right child
            node =  node.parent;

        }

        return node;

    }

    List<int[]> findPair(BSTNode root, int target) {
        List<int[]> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }


        BSTNode min = root;
        while (min.left != null) {
            min = min.left;
        }

        BSTNode max = root;
        while(max.right != null) {
            max = max.right;
        }

        while (min != max) {
            int sum  = min.val + max.val;
            if ( sum == target) {
                int[] pair = new int[] { min.val, max.val};
                ret.add(pair);
                // next bigger
                max = findNextSmaller(max);
                min = findNextBigger(min);
            } else if ( sum < target) {
                min = findNextBigger(min);
            } else {
                // sum > target
                max = findNextSmaller(max);
            }
        }

        return ret;
    }

}
