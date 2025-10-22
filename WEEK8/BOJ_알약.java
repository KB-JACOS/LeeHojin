import java.io.*;

// 20:30 시작 ->
public class BOJ_알약 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int MAX = 30;
        //dp[w][h] =>
        long[][] dp = new long[MAX + 1][MAX + 1];

        for(int h = 0; h <= MAX; h++) dp[0][h] = 1;

        for(int w = 1; w <= MAX; w++) {
            for(int h = 0; h <= MAX; h++) {
                long ways = 0;
                if(w - 1 >= 0 && h + 1 <= MAX) ways += dp[w - 1][h + 1];
                if(h - 1 >= 0) ways += dp[w][h - 1];
                dp[w][h] = ways;
            }
        }

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            System.out.println(dp[N][0]);
        }
        br.close();
    }
}
/*
    카탈란 수 : H의 수가 W의 수보다 많으면 안된다.
    점화식 : dp[w][h]  = dp[w-1][h+1] + dp[w][h-1]
 */