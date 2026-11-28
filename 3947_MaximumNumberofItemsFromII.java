import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // divisibleCount[f] = total items whose factor is divisible by f
        int[] divisibleCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) {
                continue;
            }
            for (int multiple = f; multiple <= n; multiple += f) {
                divisibleCount[f] += freq[multiple];
            }
        }

        // Sort items by price ascending to prioritize cheapest value-2 marginal copies
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remBudget = budget;
        long totalCopies = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            long boostedCopies = divisibleCount[factor] - 1; // Each gives +2 total items

            if (boostedCopies <= 0) {
                continue;
            }

            if (remBudget >= boostedCopies * price) {
                remBudget -= boostedCopies * price;
                totalCopies += boostedCopies * 2;
            } else {
                long affordable = remBudget / price;
                totalCopies += affordable * 2;
                remBudget %= price;
                break; // No more items with price <= remaining budget can be afforded
            }
        }

        // Spend any leftover budget on the cheapest item (+1 total item per copy)
        totalCopies += remBudget / minPrice;

        return (int) totalCopies;
    }
}