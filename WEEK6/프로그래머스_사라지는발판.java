import java.util.*;

class 프로그래머스_사라지는발판 {
    static int n, m;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        int res = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], true);
        return Math.abs(res);
    }

    // 반환값 규칙
    // > 0 : 현재 차례가 이김 (값 = 최소 이동 수)
    // < 0 : 현재 차례가 짐   (절댓값 = 최대 이동 수)
    // = 0 : 현재 칸이 이미 사라져 즉시 패배(이동 0)
    static int dfs(int[][] board, int ax, int ay, int bx, int by, boolean aTurn) {
        int cx = aTurn ? ax : bx;
        int cy = aTurn ? ay : by;

        // 현재 칸이 없으면 해당 턴 플레이어 즉시 패배
        if (board[cx][cy] == 0) return 0;

        // 현재 칸을 떠나며 발판 제거
        board[cx][cy] = 0;

        boolean canWin = false;
        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;

        for (int d = 0; d < 4; d++) {
            int nx = cx + dx[d], ny = cy + dy[d];
            if (!inRange(nx, ny) || board[nx][ny] == 0) continue;

            int next;
            if (aTurn) {
                next = dfs(board, nx, ny, bx, by, false);
            } else {
                next = dfs(board, ax, ay, nx, ny, true);
            }

            if (next <= 0) {
                // 상대가 지는 분기 → 현재는 이김 (최대한 빨리)
                canWin = true;
                minWin = Math.min(minWin, Math.abs(next) + 1);
            } else {
                // 상대가 이기는 분기 → 현재는 짐 (최대한 오래)
                maxLose = Math.max(maxLose, next + 1);
            }
        }

        // 발판 복원
        board[cx][cy] = 1;

        if (canWin) return minWin; // 이길 수 있으면 최단 승리
        if (maxLose == 0) return 0; // 한 칸도 못 움직였으면 즉시 패배
        return -maxLose; // 질 수밖에 없으면 최장 버팀
    }
}