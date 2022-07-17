package org.springsalad.dsa.essentials.tree;



public  class TreeNode {

    public static final TreeNode NULL_NODE = new TreeNode();

    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }

    public TreeNode() {

    }
}
