package bt300;

public class InvertBinaryTree {
    public static TreeNode invertTree(TreeNode root) {
        invertTreeRec(root);
        return root;
    }

    private static void invertTreeRec(TreeNode root) {
        if (root == null)
            return;
        TreeNode holder = root.right;
        root.right = root.left;
        root.left = holder;
        invertTreeRec(root.left);
        invertTreeRec(root.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // Tạo cây nhị phân ví dụ
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println("Cây nhị phân trước khi đảo ngược:");
        printTree(root);

        // Đảo ngược cây
        invertTree(root);
        System.out.println();

        System.out.println("Cây nhị phân sau khi đảo ngược:");
        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val+" ");
        printTree(root.left);
        printTree(root.right);
    }
}
