package com.ycx.binarytree1;
//给定两个二叉树，编写一个函数来检验它们是否相同。
public class TreeNode4 {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode4(int x) {
        val = x;
    }

    class Solution {
        public boolean isSameTree(TreeNode1 p, TreeNode1 q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {//根不相同
                return false;
            }
            return
                    isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}

