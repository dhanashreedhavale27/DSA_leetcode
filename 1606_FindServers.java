import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track requests handled by each server
        int[] requestCount = new int[k];
        
        // TreeSet to store indices of available servers in sorted order
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-Heap to store busy servers: int[] {freeTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int duration = load[i];

            // Release all servers that become free at or before arrivalTime
            while (!busy.isEmpty() && busy.peek()[0] <= arrivalTime) {
                int[] freedServer = busy.poll();
                available.add(freedServer[1]);
            }

            // If no servers are available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Find the target server index >= (i % k)
            Integer targetServer = available.ceiling(i % k);
            
            // If no server >= (i % k) is found, wrap around to the first available server
            if (targetServer == null) {
                targetServer = available.first();
            }

            // Assign task to targetServer
            requestCount[targetServer]++;
            available.remove(targetServer);
            busy.offer(new int[]{arrivalTime + duration, targetServer});
        }

        // Find the maximum requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }

        // Gather all servers with the maximum request count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}