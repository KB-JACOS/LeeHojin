import java.io.*;
import java.util.*;

//11:15 -> 11:57
public class BOJ_계란으로_계란치기 {
    private static int N, maxEgg = 0;
    private static int[][] eggs;
    private static boolean[] isBroken;

    /*
    1. 가장 왼쪽 계란
    2. 손에 든 계란이 깨졌거나 깨지지 않은 계란이 없을 경우 넘어가기
     */

    private static void recursive(int depth, int cnt) {
        if(depth == N) { //만약 가장 오른쪽이면
            maxEgg = Math.max(maxEgg, cnt); //값 최신화
            return;
        }

        if(isBroken[depth] || cnt == N - 1) { //현재 계란이 깨졌거나, 나머지가 모두 깨져있으면
            recursive(depth + 1, cnt);
            return;
        }

        boolean hit = false;
        for(int i = 0; i < N; i++) {
            if(i == depth || isBroken[i]) continue;
            hit = true;

            int sL = eggs[depth][0], sR = eggs[i][0];
            int add = 0;

            eggs[depth][0] -= eggs[i][1];
            eggs[i][0] -= eggs[depth][1];

            if(eggs[depth][0] <= 0) {
                isBroken[depth] = true;
                add++;
            }
            if(eggs[i][0] <= 0){
                isBroken[i] = true;
                add++;
            }

            recursive(depth + 1, cnt + add);
            if(eggs[depth][0] <= 0) isBroken[depth] = false;
            if(eggs[i][0] <= 0) isBroken[i] = false;
            eggs[depth][0] = sL;
            eggs[i][0] = sR;

        }

        if(!hit) recursive(depth + 1, cnt);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        isBroken = new boolean[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }


        recursive(0, 0);
        System.out.println(maxEgg);
        br.close();
    }
}
