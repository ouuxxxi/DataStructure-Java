package com.ycx.binarytree1;
//572. 另一个树的子树
public class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
class Solution {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null)
        {
            return false;
        }

        if(t==null){ //子树为空
            return true;
        }

        if(s.val==t.val && isSameTree(s,t)){
            return true;//两棵树相同
        }

        return isSubtree(s.left,t)||isSubtree(s.right,t);
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q== null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        if(p.val!=q.val){
            return false;
        }
        return
                isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}


