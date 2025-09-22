import java.io.*;
import java.util.*;

// 10:20 시작 -> 11:20 종료
/*
* 각 스킬들을 사용하는 빈도를 모두 구하고 역순정렬후 최댓값이 답이겠다 -> 꼭 최댓값이 정답은 아님
* 모든 조합에서 구해보기
1. 2n 중 n 개 선택 (조합)
 */
public class BJ16457 {
    private static int N, M, K, maxCnt = 0;
    //    private static List<Set> candidates = new ArrayList<>(); // 2N 중 N 선택 가능한 모든 조합
    private static List<int[]> skills = new ArrayList<>(); // 사용해야 하는 스킬의 수
    private static void combination(int depth, int start, int[] picked) {
        if(depth == N) {
            int cnt = 0;
            Set<Integer> set = new HashSet<>();
            for(int p : picked) {
                set.add(p);
            }

            for(int[] skill : skills) { //주어진 스킬들
                boolean containAll = true;
                for(int s : skill) {
                    if(!set.contains(s)) {
                        containAll = false;
                        break;
                    }
                }
                if(containAll) cnt++;
            }

            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        for(int i = start; i <= 2 * N; i++) {
            picked[depth] = i;
            combination(depth + 1, i + 1, picked);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            int[] skill = new int[K];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) skill[j] = Integer.parseInt(st.nextToken());
            skills.add(skill);
        }

        combination(0, 1, new int[N]);
        System.out.println(maxCnt);
        br.close();
    }
}
