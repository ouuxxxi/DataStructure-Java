package com.ycx.binarytree1;
//二叉树的最近公共祖先
import java.util.Stack;

public class TreeNode11 {
    int val;
    TreeNode11 left;
    TreeNode11 right;
    TreeNode11(int x) { val = x; }
}
class Solution11 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || p==null || q==null){
            return null;
        }
        //获取p和q在root中的路径
        Stack<TreeNode> pPath = new Stack<>();//用来存放q路径
        Stack<TreeNode> qPath = new Stack<>();

        getPath(root,p,pPath);
        getPath(root,q,qPath);

        int pSzie = pPath.size();//q路径长度
        int qSzie = qPath.size();

        //1、让节点个数多的路径先出，使其最后相等
        //2、依次比较栈顶元素： 相等----公共节点
        //                    不相等----继续比较
        while(pSzie!=0 && qSzie!=0){
            if(pSzie>qSzie){
                pPath.pop();
                pSzie--;
            }else if(pSzie < qSzie){
                qPath.pop();
                qSzie--;
            }else if(pPath.peek()==qPath.peek()){ //栈顶元素相同
                return pPath.peek();
            }
            else{
                pPath.pop();
                qPath.pop();
                pSzie--;
                qSzie--;
            }
        }
        return null;
    }

    private boolean getPath(TreeNode root,TreeNode node,Stack<TreeNode> sPath){
        if(root == null){
            return false;
        }
        sPath.push(root);

        if(root == node){
            return true;
        }

        //递归到root的左子树 || 递归到root的右子树
        if(getPath(root.left,node,sPath) || getPath(root.right,node,sPath)){
            return true;
        }
        sPath.pop();//如果不是以上三种情况，该元素出栈（该路径没有此元素）
        return false;
    }
}
