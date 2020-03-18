package com.ycx.binarytree1;
//中序与后序遍历序列构造二叉树
public class TreeNode9 {
    int val;
    TreeNode9 left;
    TreeNode9 right;
    TreeNode9(int x) { val = x; }
}
class Solution9 {
    int index = 0 ;
    private TreeNode buildTree(int[] inorder, int[] postorder,int left, int right) {
        if(index<0 || left>=right){
            return null;
        }
        int rootIndex = left;
        while(rootIndex<right){
            if(inorder[rootIndex]==postorder[index]){
                break;
            }
            rootIndex++;//没找到 继续往后找
        }
        TreeNode root = new TreeNode(postorder[index]);
        --index;//后序遍历根节点在最后 所以需要向前

        //递归右子树
        root.right=buildTree(inorder,postorder,rootIndex+1,right);

        //递归左子树
        root.left=buildTree(inorder,postorder,left,rootIndex);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index=postorder.length-1;
        return buildTree(inorder,postorder,0,postorder.length);
    }
}
