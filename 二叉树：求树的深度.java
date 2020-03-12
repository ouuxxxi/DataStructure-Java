package com.ycx.binarytree1;
//二叉树的最大深度
public class TreeNode5 {
    int val;
    TreeNode5 left;
    TreeNode5 right;
    TreeNode5(int x) { val = x; }
}
class Solution5 {
    public int maxDepth(TreeNode5 root) {
        if(root==null){
            return 0;
        }
        int a = maxDepth(root.left);
        int b = maxDepth(root.right);
        return Math.max(a,b) + 1;
    }
}

