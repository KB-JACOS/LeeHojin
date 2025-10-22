import java.io.*;
import java.util.*;

public class BOJ_평범한배낭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1]; //무게 w에서 얻을 수 있는 최대 가치 -> 총 K개의 칸 확인

        //총 1억
        for(int i = 0; i < N; i++) { //100
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            for(int cap = K; cap >= w; cap--) { //1_000_000
                dp[cap] = Math.max(dp[cap], dp[cap - w] + v);
            }
        }

        System.out.println(dp[K]);
        br.close();
    }
}
