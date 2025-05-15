/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public void dfsInvertNode(TreeNode currNode) {
        if (currNode.left != null) {
            dfsInvertNode(currNode.left);
        }
        if (currNode.right != null) {
            dfsInvertNode(currNode.right);
        }
        TreeNode tempNode = currNode.left;
        currNode.left = currNode.right;
        currNode.right = tempNode;
    }

    public TreeNode invertTree(TreeNode root) {
        // perform post-order traversal and rotate the child nodes on non-leaf nodes
        if (root != null) {
            dfsInvertNode(root);
        }
        return root;
    }
}