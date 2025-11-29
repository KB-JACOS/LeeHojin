import java.io.*;
import java.util.*;
public class BOJ_헌내기는친구가필요해 {
    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static boolean inRange(int x, int y, int N , int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Point start = new Point(0, 0);
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                if(s.charAt(j) == 'O') grid[i][j] = 0;
                else if(s.charAt(j) == 'X') grid[i][j] = 1;
                else if(s.charAt(j) == 'I') {
                    grid[i][j] = 0;
                    start = new Point(i, j);
                }
                else grid[i][j] = 2;
            }
        }

        Deque<Point> deque = new ArrayDeque<>();
        deque.offerLast(start);
        int cnt = 0;

        while(!deque.isEmpty()) {
            Point curr = deque.pollFirst();

            for(int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if(!inRange(nx, ny, N, M)) continue;
                if(visited[nx][ny]) continue;
                if(grid[nx][ny] == 1) continue;
                if(grid[nx][ny] == 2) cnt++;
                deque.offerLast(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        if(cnt == 0) System.out.println("TT");
        else System.out.println(cnt);
        br.close();
    }
}
