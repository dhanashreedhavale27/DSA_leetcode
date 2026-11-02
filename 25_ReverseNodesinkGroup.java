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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // Find the k-th node from groupPrev
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // Fewer than k nodes remaining, stop
            }

            ListNode groupNext = kth.next;
            
            // Pointer pointers for reversing the current k-group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            // Reverse current k-group
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Save the tail of the newly reversed group before re-linking
            ListNode newGroupPrev = groupPrev.next;
            
            // Connect previous section to the new head of reversed group
            groupPrev.next = kth;
            groupPrev = newGroupPrev;
        }

        return dummy.next;
    }

    // Helper method to advance k nodes forward
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}