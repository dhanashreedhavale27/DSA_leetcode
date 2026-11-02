class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindromes (single character center)
            int len1 = expandAroundCenter(s, i, i);

            // Case 2: Even-length palindromes (two character center)
            int len2 = expandAroundCenter(s, i, i + 1);

            // Take the maximum length found from both cases
            int len = Math.max(len1, len2);

            // If we found a longer palindrome, update start and maxLen
            if (len > maxLen) {
                maxLen = len;
                // Calculate starting index of the palindrome
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    // Helper method to expand outward from left and right indices
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}