package org.springsalad.dsa.essentials.tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTBasicTest {

    @Test
    public void BSTConstructTest() {
        int [] input = new int[] {3, 4, 7, 2, 8};

        TreeNode expect = TreeHelper.buildTree(TreeHelper.parseKeys("[3, 2, 4, null, null, null, 7, null, 8]"));

        BSTBasic bst = new BSTBasic();
        TreeNode actual = bst.constructBST(input);

        System.out.println("expect: " + TreeHelper.printTree(expect) );
        System.out.println("actual: " + TreeHelper.printTree(actual) );

        boolean isSame = TreeHelper.compareTree(expect, actual);
        assertTrue(isSame);
    }
}
