import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build adjacency list for the initial tree
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Pass 1: Find the closest matching ancestor for every node
        findNewParents(0, adj, s, lastSeen, newParent);

        // Build adjacency list for the modified tree
        List<Integer>[] finalAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalAdj[newParent[i]].add(i);
        }

        // Pass 2: Compute subtree sizes in the final tree
        int[] answer = new int[n];
        computeSubtreeSizes(0, finalAdj, answer);

        return answer;
    }

    private void findNewParents(int u, List<Integer>[] adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj[u]) {
            findNewParents(v, adj, s, lastSeen, newParent);
        }
        // Backtrack so sibling branches don't see u as an ancestor
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSubtreeSizes(int u, List<Integer>[] finalAdj, int[] answer) {
        int size = 1;
        for (int v : finalAdj[u]) {
            size += computeSubtreeSizes(v, finalAdj, answer);
        }
        answer[u] = size;
        return size;
    }
}