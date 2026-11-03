class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, matchIdx = 0;

        while (sIdx < s.length()) {
            // 1. Characters match or '?' wildcards a single character
            if (pIdx < p.length() && (p.charAt(pIdx) == '?' || s.charAt(sIdx) == p.charAt(pIdx))) {
                sIdx++;
                pIdx++;
            }
            // 2. '*' wildcard encountered: record positions and assume '*' matches empty sequence
            else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            }
            // 3. Mismatch occurs, but we had a previous '*': backtrack to let '*' match more characters
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            }
            // 4. Mismatch occurs with no previous '*'
            else {
                return false;
            }
        }

        // Consume remaining '*' in pattern
        while (pIdx < p.length() && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == p.length();
    }
}