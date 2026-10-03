class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Base cases: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // 1. Compute the length of the list and find the tail node
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // 2. Connect tail to head to form a circular list
        tail.next = head;
        
        // 3. Find the actual number of rotations needed
        k = k % length;
        int stepsToNewTail = length - k;
        
        // 4. Go to the new tail node
        ListNode newTail = tail;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        // 5. Set the new head and break the circular connection
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}
