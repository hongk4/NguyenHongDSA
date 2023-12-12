package bt300.LinkedListCycle;

public class main {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        Solution solution = new Solution();
        solution.Print(listNode);
    }
}
