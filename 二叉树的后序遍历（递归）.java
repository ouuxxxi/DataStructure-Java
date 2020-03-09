package com.ycx.binarytree1;
//后序
import java.util.ArrayList;
import java.util.List;

public class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;

    TreeNode3(int x) {
        val = x;
    }

    class Solution {
        List<Integer> res = new ArrayList<Integer>();

        public List<Integer> postorderTraversal(TreeNode3 root) {
            if (root != null) {
                postorderTraversal(root.left);
                postorderTraversal(root.right);
                res.add(root.val);
            }
            return res;
        }
    }
}