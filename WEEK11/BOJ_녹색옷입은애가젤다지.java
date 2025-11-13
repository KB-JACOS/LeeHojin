import java.io.*;
import java.util.*;
public class BOJ_녹색옷입은애가젤다지 {
    private static final int INF = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static class Point{
        int x, y, c;
        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    private static boolean inRange(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static int dijkstra(int[][] maze, int N) {
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                return Integer.compare(p1.c, p2.c);
            }
        });

        int[][] dist = new int[N][N];
        for(int[] d : dist) Arrays.fill(d, INF);

        dist[0][0] = maze[0][0];
        pq.offer(new Point(0, 0, maze[0][0]));

        while(!pq.isEmpty()) {
            Point curr = pq.poll();
            int x = curr.x, y = curr.y;

            if(curr.c > dist[x][y]) continue;

            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];

                if(!inRange(nx, ny, N)) continue;

                int newCost = dist[x][y] + maze[nx][ny];

                if(newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.offer(new Point(nx, ny, newCost));
                }
            }
        }

         return dist[N-1][N-1];

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            int[][] grid = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("Problem " + cnt++ + ": " + dijkstra(grid, N));
        }

        br.close();
    }
}
