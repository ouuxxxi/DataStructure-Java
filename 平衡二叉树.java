package com.ycx.binarytree1;
//平衡二叉树
public class TreeNode6 {
    int val;
    TreeNode6 left;
    TreeNode6 right;
    TreeNode6(int x) { val = x; }
}
class Solution6 {
    public boolean isBalanced(TreeNode6 root) {
        if(root == null){
            return true;
        }
        int a = maxDepth(root.left);//左子树的高度
        int b = maxDepth(root.right);//右子树的高度
        if(Math.abs(a-b)>1){  /*平衡二叉树:左子树和右子树差值不能大于1*/
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxDepth(TreeNode6 root) {
        if(root==null){
            return 0;
        }
        int a = maxDepth(root.left);//左子树的高度
        int b = maxDepth(root.right);//右子树的高度
        return Math.max(a,b) + 1;
    }
}
