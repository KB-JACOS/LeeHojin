import java.util.*;

class 경주로건설 {
    static class Node {
        int x, y, dir, cost;
        Node(int x, int y, int dir, int cost) {
            this.x = x; this.y = y; this.dir = dir; this.cost = cost;
        }
    }

    static int N;
    static final int INF = Integer.MAX_VALUE;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public int solution(int[][] board) {
        N = board.length;

        int[][][] cost = new int[N][N][4];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) Arrays.fill(cost[i][j], INF);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        for (int d : new int[]{1, 2}) {
            cost[0][0][d] = 0;
            pq.offer(new Node(0, 0, d, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > cost[cur.x][cur.y][cur.dir]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i], ny = cur.y + dy[i];
                if (!inRange(nx, ny)) continue; //범위 밖이거나
                if (board[nx][ny] == 1) continue; //벽이 있으면 패스

                int add = (cur.dir == i) ? 100 : 600;
                int newCost = cur.cost + add;

                if (newCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = newCost;
                    pq.offer(new Node(nx, ny, i, newCost));
                }
            }
        }

        int answer = INF;
        for (int d = 0; d < 4; d++) answer = Math.min(answer, cost[N - 1][N - 1][d]);
        return answer;
    }
}

