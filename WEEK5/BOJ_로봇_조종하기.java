import java.io.*;
import java.util.*;
// 16:00 ->
/*
    가능한 모든 경우를 탐색
    이전단계까지의 모든 경로를 저장하고, 이를 다음 단계에서 확인 -> 갹 겅우마다 모든경로 HashSet으로 깊은복사해서 메모리 초과
    한 포인트에서라도 이전까지의 경로가 다르기 때문에 새로 비교 필요
 */
public class BOJ_로봇_조종하기 {
    static int N, M;
    static final int[][] dxy = {{0, 0, 1}, {-1, 1, 0}};
    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point{
        int x, y, cost;
        Set<String> paths;
        public Point(int x, int y, int cost, Set<String> paths) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.paths = paths;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //입력
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];
        for(int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //3 ^ (1000 x 1000) -> 펑
        Deque<Point> deque = new ArrayDeque<>();
        Set<String> s = new HashSet<>();
        s.add(0 + " " + 0);
        deque.offerLast(new Point(0, 0, map[0][0], s));
        while(!deque.isEmpty()) {
            Point cur = deque.pollFirst();
            if(cur.x == N - 1 && cur.y == M - 1) continue;

            for(int i = 0; i < 3; i++) {
                int nx = cur.x + dxy[0][i], ny = cur.y + dxy[1][i];
                if(!inRange(nx, ny)) continue;
                if(cur.paths.contains(nx + " " + ny)) continue;

                dp[nx][ny] = Math.max(cur.cost + map[nx][ny], dp[nx][ny]);
                Set<String> ns = new HashSet<>(cur.paths);
                ns.add(nx + " " + ny);
                deque.offerLast(new Point(nx, ny, cur.cost + map[nx][ny], ns));
            }
        }

        System.out.println(dp[N - 1][M - 1]);
        br.close();
    }
}
