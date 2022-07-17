package org.springsalad.dsa.essentials.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeHelper {

    public static TreeNode buildTree(TreeNode[] treekeys) {

        if (treekeys.length == 0) {
            return null;
        }

        TreeNode root = treekeys[0];
        int i = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && i < treekeys.length) {
            TreeNode node = queue.poll();
            if (treekeys[i] != TreeNode.NULL_NODE) {
                node.left = treekeys[i];
                queue.offer(node.left);
            }
            if (treekeys[i + 1] != TreeNode.NULL_NODE) {
                node.right = treekeys[i + 1];
                queue.offer(node.right);
            }
            i = i + 2;
        }
        return root;

    }

    // [ 1, null, null];
    public static TreeNode[] parseKeys(String arrays) {

        int begin = arrays.indexOf('[');
        int end = arrays.indexOf(']');
        String values = arrays.substring(begin + 1, end);
        String[] elements = values.split(",");

        List<TreeNode> list = new ArrayList<>();

        for (String el : elements) {
            if (el.contains("null")) {
                list.add(TreeNode.NULL_NODE);
            } else {
                TreeNode node = new TreeNode(Integer.valueOf(el.trim()));
                list.add(node);
            }
        }

        return list.toArray(new TreeNode[0]);

    }

    public static String printTree(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            TreeNode node = queue.poll();
            if (node == TreeNode.NULL_NODE) {

                sb.append("null");
            } else {
                sb.append(String.valueOf(node.key));

                if (node.left != null) {
                    queue.offer(node.left);
                } else {
                    queue.offer(TreeNode.NULL_NODE);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                } else {
                    queue.offer(TreeNode.NULL_NODE);
                }

            }


        }
        sb.append("]");
        return sb.toString();
    }

    public static  boolean compareTree (TreeNode expectRoot, TreeNode root) {

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
