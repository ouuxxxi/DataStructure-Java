package com.ycx.binarytree1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//分层遍历
public class TreeNode7 {
    int val;
    TreeNode7 left;
    TreeNode7 right;
    TreeNode7(int x) { val = x; }
}
class Solution7 {
    /*
    while(非空)
     用ArrayList保存每一层的所有节点（一次性将该层节点遍历完）
     List<Integer> level = new ArrayList<>();
     int size = queue.size();
     for(int i=0;i<size;i++){ //将本层所有节点遍历完，并将下一层所有节点保存到队列中
         取队头元素 遍历 level.add(cur.val)；
         如果该节点有左孩子 将左孩子加入队列；
         如果该节点有左孩子 将右孩子加入队列；
     }

    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root==null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            // 用ArrayList保存每一层的所有节点（一次性将该层节点遍历完）
            int size= queue.size();

            for(int i=0;i<size;i++){//将本层所有节点遍历完，并将下一层所有节点保存到队列中
                TreeNode cur = queue.poll();
                level.add(cur.val);

                if(cur.left!=null){
                    queue.offer(cur.left);
                }
                if(cur.right!=null){
                    queue.offer(cur.right);
                }
            }
            list.add(level);
        }
        return list;
    }
}