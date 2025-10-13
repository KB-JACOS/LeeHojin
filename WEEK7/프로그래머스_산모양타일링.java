import java.util.*;
class 프로그래머스_산모양타일링 {
    public int solution(int n, int[] tops) {
        final int divisor = 10007;
        int answer = 0;

        int[][] dp = new int[n + 1][2];
        if(tops[0] == 0) {
            dp[1][0] = 2;
            dp[1][1] = 1;
        } else {
            dp[1][0] = 3;
            dp[1][1] = 1;
        }


        for(int i = 1; i < n; i++) {
            int top = tops[i];
            if(top == 0) {
                dp[i + 1][0] = (dp[i][0] * 2 + dp[i][1]) % divisor ;
                dp[i + 1][1] = (dp[i][0] + dp[i][1]) % divisor;
            } else {
                dp[i + 1][0] = (dp[i][0] * 3 + dp[i][1] * 2) % divisor;
                dp[i + 1][1] = (dp[i][0] + dp[i][1]) % divisor;
            }
        }

        answer = (dp[n][0] + dp[n][1]) % divisor;

        return answer;
    }
}