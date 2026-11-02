import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Base index for valid length calculations
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // Push index of unmatched ')' as new boundary
                    stack.push(i);
                } else {
                    // Length = current index - index of element prior to valid substring
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }
}