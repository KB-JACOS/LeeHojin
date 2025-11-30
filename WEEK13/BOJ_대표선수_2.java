import java.io.*;
import java.util.*;
public class BOJ_대표선수_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] students = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] idxs = new int[N];
        for(int i = 0; i < N; i++) {
            Arrays.sort(students[i]);
            idxs[i] = 0;
        }

        int min = Integer.MAX_VALUE;
        while(true) {
            int tempMin = students[0][idxs[0]];
            int tempMax = students[0][idxs[0]];
            int minIdx = 0;

            for(int i = 1; i < N; i++) {
                if(tempMin > students[i][idxs[i]]) {
                    tempMin = students[i][idxs[i]];
                    minIdx = i;
                }

                if(tempMax < students[i][idxs[i]]) {
                    tempMax = students[i][idxs[i]];
                }
            }
            if((tempMax - tempMin) < min) {
                min = (tempMax - tempMin);
            }

            if(++idxs[minIdx] >= M) {
                break;
            }
        }
        System.out.println(min);
    }
}
