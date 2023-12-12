package bt300.MaxDepthBinaryTree;

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int kq = Math.max(left, right) + 1;
        return kq;
    }
}