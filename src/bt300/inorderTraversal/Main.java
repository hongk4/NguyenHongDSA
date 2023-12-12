package bt300.inorderTraversal;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Tạo một cây nhị phân để kiểm tra
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Tạo một đối tượng Solution và gọi hàm inorderTraversal
        Solution solution = new Solution();
        List<Integer> result = solution.inorderTraversal(root);

        // In kết quả
        System.out.println(result);
    }
}
