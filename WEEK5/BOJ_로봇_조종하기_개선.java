import java.io.*;
import java.util.*;
/*
    DP로 풀어야겠다 -> 시간초과 안나려면
    1. 좌에서 오는경우
    2. 우에서 오는경우
    3. 위에서 내려오는경우

    위 3가지 경우 중 최댓값을 계속 업데이트 해야함
 */
public class BOJ_로봇_조종하기_개선 {
    static final int INF = Integer.MIN_VALUE;
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {map[i][j] = Integer.parseInt(st.nextToken());}
        }

        int[] dpUp = new int[M]; //바로 위에서 내려오는 최댓값

        //첫 행은 오른쪽으로 쭉 가는 것만 가능
        dpUp[0] = map[0][0];
        for(int c = 1; c < M; c++) {
            dpUp[c] = dpUp[c - 1] + map[0][c];
        }

        for(int i = 1; i < N; i++) {
            int[] up = new int[M]; //위에서 내려올 수 있는 최대
            for(int j = 0; j < M; j++) up[j] = dpUp[j] + map[i][j];

            int[] left = new int[M]; //왼쪽에서부터 오는 최대
            int[] right = new int[M]; //오른쪽에서부터 오는 쵀대

            left[0] = up[0];
            for(int j = 1; j < M; j++) {
                left[j] = Math.max(left[j - 1] + map[i][j], up[j]);
            }

            right[M - 1] = up[M - 1];
            for(int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] + map[i][j], up[j]);
            }

            int[] dpAll = new int[M];
            for(int j = 0; j < M; j++) {
                dpAll[j] = Math.max(left[j], right[j]);
            }

            dpUp = dpAll;
        }

        System.out.println(dpUp[M - 1]);
        br.close();
    }
}