import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Free servers priority queue: sorted by (weight, index)
        // Stores int[] {weight, index}
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Used servers priority queue: sorted by (availableTime, weight, index)
        // Stores long[] {availableTime, weight, index}
        PriorityQueue<long[]> usedServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long currTime = 0;

        for (int j = 0; j < m; j++) {
            // A task cannot start before second j
            currTime = Math.max(currTime, j);

            // Release all servers that have finished their tasks by currTime
            while (!usedServers.isEmpty() && usedServers.peek()[0] <= currTime) {
                long[] server = usedServers.poll();
                freeServers.offer(new int[]{(int) server[1], (int) server[2]});
            }

            // If no free servers are available, jump time to when the first busy server becomes free
            if (freeServers.isEmpty()) {
                currTime = usedServers.peek()[0];
                while (!usedServers.isEmpty() && usedServers.peek()[0] <= currTime) {
                    long[] server = usedServers.poll();
                    freeServers.offer(new int[]{(int) server[1], (int) server[2]});
                }
            }

            // Pick the best available server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int serverIdx = bestServer[1];

            ans[j] = serverIdx;

            // Push to used servers with updated available time
            usedServers.offer(new long[]{currTime + tasks[j], weight, serverIdx});
        }

        return ans;
    }
}