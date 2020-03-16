package com.ycx.binarytree1;

//从前序与中序遍历序列构造二叉树

public class TreeNode8 {
    int val;
    TreeNode8 left;
    TreeNode8 right;
    TreeNode8(int x) { val = x; }
}
/*
 1.从前序遍历结果中找根
 2.在中序遍历结果中确认根节点的位置(rootindex)
   左子树中所有的节点[left,rootindex);
   右子树中的节点[rootindex+1,right);
 */
class Solution8 {
    // preorder:前序遍历结果
    // inorder：中序遍历结果
    // [left,right)：标记在中序遍历中节点的位置范围

    int index = 0;
    private TreeNode reBuildTree(int[] preorder, int[] inorder, int left, int right){
        if(index >= preorder.length || left>=right){
            return null;
        }
        //前序找根节点 preorder[index]

        //在中序遍历中找根的位置
        //确认左右子树的区间
        int rootindex = left;
        while(rootindex<right){
            if(inorder[rootindex]==preorder[index]){
                break;  //如果找到了，直接退出
            }
            rootindex++; //如果没找到，往后继续走
        }

        //创建根节点
        TreeNode root = new TreeNode(preorder[index]);

        ++index;//根的位置还包括左子树/右子树的根 所以需要移动

        //递归调用左子树[left,rootindex):
        root.left = reBuildTree(preorder,inorder,left,rootindex);
        //递归调用右子树[rootindex+1,right):
        root.right = reBuildTree(preorder,inorder,rootindex+1,right);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return reBuildTree(preorder, inorder,0,inorder.length);
    }
}