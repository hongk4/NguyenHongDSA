package bt300.MaxDepthBinaryTree;

public class main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Tính toán chiều sâu tối đa của cây
        Solution solution = new Solution();
        int maxDepth = solution.maxDepth(root);

        // In kết quả
        System.out.println("Chiều sâu tối đa của cây là: " + maxDepth);
    }
}
