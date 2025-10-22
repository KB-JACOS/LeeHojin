import java.util.*;
// 11:50 -> 12:15
class 프로그래머스_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;

        int[] dp = triangle[0];
        for(int h = 1; h < N; h++) {
            int[] prev = dp;
            int[] newDp = new int[triangle[h].length];

            newDp[0] = prev[0] + triangle[h][0];
            newDp[newDp.length - 1] = prev[prev.length - 1] + triangle[h][triangle[h].length - 1];
            for(int r = 1; r < newDp.length - 1; r++) {
                newDp[r] = Math.max(prev[r - 1] + triangle[h][r], prev[r] + triangle[h][r]);
            }

            dp = newDp;

        }

        for(int i = 0; i < N; i++) {
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}