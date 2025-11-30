import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static int[][] dp;
    static int[] sales;

    public int solution(int[] salesInput, int[][] links) {
        int n = salesInput.length;
        sales = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sales[i + 1] = salesInput[i];
        }

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] link : links) {
            int parent = link[0];
            int child = link[1];
            tree[parent].add(child);
        }

        dp = new int[n + 1][2];

        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int u) {
        dp[u][1] = sales[u];
        dp[u][0] = 0;

        if (tree[u].isEmpty()) {
            return;
        }

        int sumIfChildrenFree = 0;
        int extraMin = Integer.MAX_VALUE;

        for (int v : tree[u]) {
            dfs(v);

            int childMin = Math.min(dp[v][0], dp[v][1]);
            sumIfChildrenFree += childMin;

            dp[u][1] += childMin;


            int extra = dp[v][1] - childMin;
            if (extra < extraMin) {
                extraMin = extra;
            }
        }

        dp[u][0] = sumIfChildrenFree + extraMin;
    }
}