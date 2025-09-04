import java.io.*;
import java.util.*;
// 10:32 시작 -> 11:30 종료 => 최단거리 조건 못봄
public class BJ17265 {
    private static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
    private static char[][] grid;
    private static boolean[][] visited;
    private static int[] dx = {1, 0}, dy = {0, 1};

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void dfs(int sum, Point curr) {
        if(curr.x == N - 1 && curr.y == N - 1) {
            maxNum = Math.max(sum, maxNum);
            minNum = Math.min(sum, minNum);
            return;
        }

        for(int d1 = 0; d1 < 2; d1++) { //연산자 찾기
            int nx1 = curr.x + dx[d1], ny1 = curr.y + dy[d1];
            if(inRange(nx1, ny1) && !visited[nx1][ny1]) {
                visited[nx1][ny1] = true;
                char oper = grid[nx1][ny1];
                for(int d2 = 0; d2 < 2; d2++) { //숫자 찾기
                    int nx2 = nx1 + dx[d2], ny2 = ny1 + dy[d2];
                    if(inRange(nx2, ny2) && !visited[nx2][ny2]) {
                        visited[nx2][ny2] = true;
                        dfs(calculate(sum, grid[nx2][ny2] - '0', oper), new Point(nx2, ny2));
                        visited[nx2][ny2] = false;
                    }
                }
                visited[nx1][ny1] = false;
            }
        }
    }
    private static int calculate(int x, int y, int operator) {
        switch (operator) {
            case('+') : {
                return x + y;
            }
            case('-') : {
                return x - y;
            }
            case('*') : {
                return x * y;
            }
            default:
                return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        grid = new char[N][N];
        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                grid[i][j] = str[j].charAt(0);
            }
        }

        visited = new boolean[N][N];
        visited[0][0] = true;
        dfs(grid[0][0] - '0', new Point(0, 0));

        System.out.println(maxNum + " " + minNum);
        br.close();
    }
}
