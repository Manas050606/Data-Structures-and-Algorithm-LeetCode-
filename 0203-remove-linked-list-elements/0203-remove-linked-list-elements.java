/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Create a dummy node acting as a temporary head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;
        
        while (curr != null) {
            if (curr.val == val) {
                // Skip the current node by linking prev directly to curr.next
                prev.next = curr.next;
            } else {
                // Move prev forward only if we didn't remove the current node
                prev = curr;
            }
            // Advance the current pointer
            curr = curr.next;
        }
        
        return dummy.next;
    }
}
