class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        long common = Math.min(n1, n2);
        long rem1 = n1 - common;
        long rem2 = n2 - common;

        long totalCost = 0;

        // Step 1: Handle overlapping requirements
        totalCost += common * Math.min(cb, c1 + c2);

        // Step 2: Handle remaining type 1 requirement
        totalCost += rem1 * Math.min(c1, cb);

        // Step 3: Handle remaining type 2 requirement
        totalCost += rem2 * Math.min(c2, cb);

        return totalCost;
    }
}