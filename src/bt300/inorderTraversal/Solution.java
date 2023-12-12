package bt300.inorderTraversal;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> li = new ArrayList<>();
        inorderHelper(root, li);
        return li;
    }

    private void inorderHelper(TreeNode root, List<Integer> li) {
        if (root != null) {
            // Duyệt qua cây con bên trái
            if (root.left != null) {
                inorderHelper(root.left, li);
            }

            // Thêm giá trị của nút gốc vào danh sách
            li.add(root.val);

            // Duyệt qua cây con bên phải
            if (root.right != null) {
                inorderHelper(root.right, li);
            }
        }
    }
}
