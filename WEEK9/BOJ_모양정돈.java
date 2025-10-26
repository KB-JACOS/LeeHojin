import java.io.*;
import java.util.*;

public class BOJ_모양정돈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        //각 숫자의 갯수 구하기 -> 3 / 2 / 3
        int c1=0,c2=0,c3=0;
        for (int i=0;i<N;i++){
            a[i]=Integer.parseInt(st.nextToken());
            if(a[i]==1) c1++;
            else if(a[i]==2) c2++;
            else c3++;
        }

        //가능한 모든 경우의 수
        int[][] orders = {
                {1,2,3},{1,3,2},{2,1,3},
                {2,3,1},{3,1,2},{3,2,1}
        };

        int ans = Integer.MAX_VALUE;

        for (int[] ord : orders) {
            int A = ord[0], B = ord[1], C = ord[2];

            // 각 구간 길이 -> 미쳤다리
            int lenA = (A == 1 ? c1:(A == 2 ? c2 : c3));
            int lenB = (B == 1 ? c1:(B == 2 ? c2 : c3));
            int lenC = N - lenA - lenB;

            // 구간 경계
            int sA = 0, eA = lenA;
            int sB = eA, eB = eA + lenB;
            int sC = eB, eC = N;

            // 0/1/2 매핑
            // A->0, B->1, C->2
            int[][] M = new int[3][3];

            for (int i = 0; i < N; i++) {
                int t = a[i]; // 1/2/3
                int ti;
                if (t == A) ti = 0; // A 구역
                else if (t == B) ti = 1; // B 구역
                else ti = 2; // C 구역

                int si;
                if (i < eA) si = 0;
                else if (i < eB) si = 1;
                else si = 2;

                M[ti][si]++;
            }

            int off = M[0][1] + M[0][2] + M[1][0] + M[1][2] + M[2][0] + M[2][1];
            int pair = Math.min(M[0][1],M[1][0]) + Math.min(M[0][2],M[2][0]) + Math.min(M[1][2],M[2][1]);
            int remain = off - 2*pair;
            int moves = pair + 2*(remain/3);

            ans = Math.min(ans, moves);
        }

        System.out.println(ans);
    }
}