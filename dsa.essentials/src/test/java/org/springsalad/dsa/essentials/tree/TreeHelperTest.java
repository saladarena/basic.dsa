package org.springsalad.dsa.essentials.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TreeHelperTest {


    @Test
    public void testParseArrayNormal() {
        String arrays = " [ 1, 5, null, null, 3 ]";
        TreeNode expectRoot = new TreeNode(1);
        TreeNode nodeA = new TreeNode(5);
        expectRoot.left = nodeA;
        TreeNode nodeB = new TreeNode(3);
        nodeA.right = nodeB;

        TreeNode [] expects = new TreeNode[5];
        expects[0] = expectRoot;
        expects[1] = nodeA;
        expects[2] = TreeNode.NULL_NODE;
        expects[3] = TreeNode.NULL_NODE;
        expects[4] = nodeB;



        TreeNode[] nodes = TreeHelper.parseKeys(arrays);
        int comparing = Arrays.compare(nodes, expects, Comparator.comparingInt( node -> node.key));
        assertEquals(0, comparing);

        TreeNode root = TreeHelper.buildTree(nodes);
        boolean isIdentical = compareTree(expectRoot, root);
        assertTrue(isIdentical);

        System.out.println(TreeHelper.printTree(root));



    }

    private boolean compareTree (TreeNode expectRoot, TreeNode root) {

        if (root != null && expectRoot != null) {
            return (root.key == expectRoot.key) && compareTree(root.left, expectRoot.left)
                    && compareTree(root.right, expectRoot.right);
        } else if (root == null && expectRoot == null) {
            return true;
        } else {
            return false;
        }
    }



}
