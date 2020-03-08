package com.ycx.binarytree1;
//前序（递归）
import java.util.ArrayList;
import java.util.List;

public class TreeNode1 {
    int val;
     TreeNode1 left;
     TreeNode1 right;
     TreeNode1(int x) {
         val = x;
     }
    class Solution {
        List<Integer> res = new ArrayList<Integer>();
        public List<Integer> preorderTraversal(TreeNode1 root) {
            if(root!=null){
                res.add(root.val);
                preorderTraversal(root.left);
                preorderTraversal(root.right);
            }
            return res;
        }
    }
}
