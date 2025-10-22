import java.io.*;
import java.util.*;

public class BOJ_쉬운최단거리 {
    private static int N, M;
    private static int[][] dxy = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    private static class Point{
        int x, y, d;
        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        int[][] distance = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Deque<Point> deque = new ArrayDeque<>();

        for(int i = 0; i < N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 2) {
                    deque.offerLast(new Point(i, j, 0));
                    distance[i][j] = 0;
                }else if(grid[i][j] == 0) distance[i][j] = 0;
                 else distance[i][j] = -1;
            }
        }



        Point start = deque.peekFirst();
        visited[start.x][start.y] = true;

        while(!deque.isEmpty()) {
            Point cur = deque.pollFirst();
            distance[cur.x][cur.y] = cur.d;

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dxy[0][i];
                int ny = cur.y + dxy[1][i];

                if(!inRange(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(grid[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                deque.offerLast(new Point(nx, ny, cur.d + 1));
            }
        }

        for(int[] d : distance) {
            for(int n : d) {
                System.out.print(n + " ");
            }
            System.out.println();
        }

        br.close();

    }
}
