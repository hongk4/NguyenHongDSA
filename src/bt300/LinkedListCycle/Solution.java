package bt300.LinkedListCycle;

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }

        return false;
    }
    public static void Print(ListNode head){
        if(head == null){
            System.out.println("null");
        }else{
            ListNode temp = head;
            while(temp != null){
                System.out.print(temp.val+" ");
                temp = temp.next;
            }
        }
    }
}
