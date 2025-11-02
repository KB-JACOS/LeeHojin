import java.io.*;
import java.util.*;
public class BOJ_최소비용구하기_Refactor {
    private static class Edge{
        int node, cost;
        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Map<Integer, Integer>> graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new HashMap<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            Map<Integer, Integer> edges = graph.get(from);
            edges.put(to, Math.min(edges.getOrDefault(to, Integer.MAX_VALUE), cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        boolean[] visited = new boolean[N];

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return Integer.compare(e1.cost, e2.cost);
            }
        });

        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(visited[cur.node]) continue;
            visited[cur.node] = true;

            if(cur.node == end) break;

            for(Map.Entry<Integer, Integer> entry : graph.get(cur.node).entrySet()) {
                int next = entry.getKey();
                int w = entry.getValue();

                if(!visited[next] && dist[next] > cur.cost + w) {
                    dist[next] = cur.cost + w;
                    pq.offer(new Edge(next, dist[next]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
