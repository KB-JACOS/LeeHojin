import java.io.*;
import java.util.*;
public class BOJ_친구네트워크 {
    static final int MAX = 200000;

    static Map<String, Integer> map;

    static int[] parents;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            parents = new int[MAX + 1];
            map = new HashMap<>();
            dp = new int[MAX + 1];

//            Arrays.fill(dp, 1);

            int cnt = 0;
            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                if(!map.containsKey(name1)) {
                    map.put(name1, cnt);
                    parents[cnt] = cnt;
                    dp[cnt] = 1;
                    cnt++;
                }
                if(!map.containsKey(name2)) {
                    map.put(name2, cnt);
                    parents[cnt] = cnt;
                    dp[cnt] = 1;
                    cnt++;
                }

                sb.append((union(map.get(name1), map.get(name2)))).append("\n");
            }
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static int find(int x) {
        if(x == parents[x]) return x;
        else return find(parents[x]);
    }

    private static int union(int x, int y) {
        x = find(x); y = find(y);

        if(x == y) return dp[x];
        else {
            parents[y] = x;
            dp[x] += dp[y];
        }

        return dp[x];
    }
}

/*
1 2
3 4
2 3
 */
