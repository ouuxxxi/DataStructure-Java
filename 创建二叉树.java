package com.ycx.binarytree;
//创建二叉树
public class CreateBinaryTree {
    private  Node root = null;//引用二叉树的根节点
    private  int index = 0 ; //仅在创建二叉树时使用 [1,2,3,*,*,*,4,5,6,*,*]

    public CreateBinaryTree(int[] arr,int invaild){
         root = createBTree(arr,-1);
    }

    private Node createBTree(int[] arr,int invaild){
       Node newRoot = null;
       if(index<arr.length && arr[index]!= invaild){
           newRoot = new Node(arr[index]);//创建根节点

           //递归创建根节点左子树
           ++index;
           newRoot.left=createBTree(arr,-1);

           //递归创建根节点右子树
           ++index;
           newRoot.right=createBTree(arr,-1);
       }
       return  newRoot;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,-1,-1,-1,4,5,6,-1,-1};
        CreateBinaryTree binaryTree = new CreateBinaryTree(arr,-1);
    }

}
