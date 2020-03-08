package com.ycx.binarytree1;
//中序
import java.util.ArrayList;
import java.util.List;

public class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;

    TreeNode2(int x) {
        val = x;
    }

    class Solution {
        List<Integer> res = new ArrayList<Integer>();

        public List<Integer> inorderTraversal(TreeNode2 root) {
            if (root != null) {
                inorderTraversal(root.left);
                res.add(root.val);
                inorderTraversal(root.right);
            }
            return res;
        }
    }
}
