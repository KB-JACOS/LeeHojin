import java.util.*;
class 프로그래머스_등굣길 {
    static int M, N;
    static final long divisor = 1000000007;
    static boolean isPuddle(int x, int y, int[][] puddles) {
        for(int[] puddle : puddles) {
            if(puddle[0] - 1 == y && puddle[1] - 1 == x) return true;
        }
        return false;
    }
    public long solution(int m, int n, int[][] puddles) {
        M = m;
        N = n;

        long[][] dp = new long[n][m];
        for(int i = 0; i < m; i++) {
            if(isPuddle(0, i, puddles)) break;
            dp[0][i] = 1;
        }

        for(int i = 0; i < n; i++) {
            if(isPuddle(i, 0, puddles)) break;
            dp[i][0] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(isPuddle(i, j, puddles)) {
                    continue;
                }
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % divisor;
            }
        }



        return (dp[n - 1][m - 1]) % divisor;
    }
}