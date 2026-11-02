import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        // 'right' expands the sliding window
        for (int right = 0; right < s.length(); right++) {
            // If duplicate character is found, shrink window from left
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the set
            set.add(s.charAt(right));

            // Update the maximum window length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}