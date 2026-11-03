class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";

        String result = "1";

        // Generate term by term from 2 up to n
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int j = 0; j < result.length(); j++) {
                // If the next character matches the current one, increment count
                if (j + 1 < result.length() && result.charAt(j) == result.charAt(j + 1)) {
                    count++;
                } else {
                    // Append count followed by the digit character
                    sb.append(count).append(result.charAt(j));
                    count = 1; // Reset count for the next digit group
                }
            }

            result = sb.toString();
        }

        return result;
    }
}