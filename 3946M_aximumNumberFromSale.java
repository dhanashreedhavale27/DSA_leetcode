class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] freq = new int[maxFactor + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // divisibleCount[f] = count of items whose factor is divisible by f
        int[] divisibleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) {
                continue;
            }
            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                divisibleCount[f] += freq[multiple];
            }
        }

        // 0/1 Knapsack over buying the FIRST copy of each distinct item
        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int firstCopyGain = divisibleCount[factor];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
        }

        // Spend any remaining budget on the cheapest item (+1 copy per minPrice)
        int maxItems = 0;
        for (int b = 0; b <= budget; b++) {
            int extraCopies = (budget - b) / minPrice;
            maxItems = Math.max(maxItems, dp[b] + extraCopies);
        }

        return maxItems;
    }
}