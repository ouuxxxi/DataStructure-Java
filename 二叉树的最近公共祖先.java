package com.ycx.binarytree1;
//二叉树的最近公共祖先
public class TreeNode10 {
    int val;
    TreeNode10 left;
    TreeNode10 right;
    TreeNode10(int x) { val = x; }
}
class Solution10 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root==null || q==null || p==null){
            return null;
        }
        //如果有一个节点在根的位置，最近公共祖先一定是根节点
        if(p==root || q==root){
            return root;
        }

        //检测p和q在roor子树中的情况
        boolean isPInLeft = false;
        boolean isPInRight = false;
        boolean isQInLeft = false;
        boolean isQInRight = false;

        if(isNodeInTree(root.left,p)){ //节点p在左子树中
            isPInLeft=true;
            isPInRight=false;
        }else{
            isPInLeft=false;
            isPInRight=true;
        }

        if(isNodeInTree(root.left,q)){ //节点q在左子树中
            isQInLeft=true;
            isQInRight=false;
        }else{
            isQInLeft=false;
            isQInRight=true;
        }

        //进行判断

        // p、q分别在root的左右子树中
        if(isPInLeft && isQInRight || isPInRight&&isQInLeft){
            return root;
        }
        //p、q在root的左子树中------递归到根节点的左子树中查找
        else if(isPInLeft && isQInLeft){
            return lowestCommonAncestor(root.left,p,q);
        }
        // p、q在root的右子树中------递归到根节点的右子树中查找
        else{
            return lowestCommonAncestor(root.right,p,q);
        }
    }

    //检测一个节点是否在二叉树中
    private boolean isNodeInTree(TreeNode root,TreeNode node){
        if(root==null){
            return false;
        }
        if(root==node){
            return true;
        }
        if(isNodeInTree(root.left,node)||isNodeInTree(root.right,node)){
            return true;
        }
        return false;
    }
}
