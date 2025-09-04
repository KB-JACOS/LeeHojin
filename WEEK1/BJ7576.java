import java.io.*;
import java.util.*;
public class BJ7576 {
    private static int N, M;
    private static int[][] grid;
    private static boolean[][] visited;
    private static int minDay = 0;
    private static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    private static class Tomato{
        int x, y, days;
        public Tomato(int x, int y, int days) {
            this.x = x;
            this.y = y;
            this.days = days;
        }
    }

    private static boolean allRipen() {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(grid[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[M][N];
        visited = new boolean[M][N];

        Deque<Tomato> deque = new ArrayDeque<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) {
                    deque.offerLast(new Tomato(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        //이미 다 익어있으면 0일
        if(allRipen()) {
            System.out.println(0);
            return;
        }

        //
        while(!deque.isEmpty()) {
            Tomato curr = deque.pollFirst();
            minDay = Math.max(curr.days, minDay);
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    grid[nx][ny] = 1;
                    deque.offerLast(new Tomato(nx, ny, curr.days + 1));
                }
            }
        }

        System.out.println(allRipen() ? minDay : -1);
        br.close();

    }
}
