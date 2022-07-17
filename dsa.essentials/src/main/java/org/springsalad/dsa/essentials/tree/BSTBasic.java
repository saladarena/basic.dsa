package org.springsalad.dsa.essentials.tree;

public class BSTBasic {

    // given a num array, construct the binary search tree

    public TreeNode constructBST(int nums[]) {
        if (nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode( nums[0]);

        for (int i = 1; i < nums.length; i ++ ) {
            insertKey(root, nums[i]);
        }
        return root;
    }

    // insert are sure finally a leaf
    private void insertKey(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = root;
        boolean isLeftChild = false;
        while (cur != null) {
            pre = cur;
            if ( key <  cur.key ) {
                cur = cur.left;
                isLeftChild = true;
            } else {
                // cur.key  < key
                cur = cur.right;
                isLeftChild = false;
            }
        }

        TreeNode node = new TreeNode(key);
        if (isLeftChild) {
            pre.left = node;
        } else {
            pre.right = node;
        }
    }
}
