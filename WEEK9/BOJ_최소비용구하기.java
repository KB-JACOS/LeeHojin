import java.io.*;
import java.util.*;
public class BOJ_최소비용구하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        final int INF = Integer.MAX_VALUE;
        int[][] cities = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(cities[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to   = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            cities[from][to] = Math.min(cities[from][to], cost);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end   = Integer.parseInt(st.nextToken()) - 1;

        boolean[] visited = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for(int i = 0; i < N; i++) {
            int minDist = INF;
            int minNode = -1;

            for(int j = 0; j < N; j++) {
                if(!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minNode = j;
                }
            }

            if(minNode == -1) break;
            if(minNode == end) break;

            visited[minNode] = true;

            for(int j = 0; j < N; j++) {
                if(cities[minNode][j] != INF && !visited[j]) {
                    if(dist[j] > dist[minNode] + cities[minNode][j]) {
                        dist[j] = dist[minNode] + cities[minNode][j];
                    }
                }
            }
        }
        System.out.println(dist[end]);
    }
}
