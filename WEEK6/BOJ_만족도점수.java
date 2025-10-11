import java.io.*;
import java.util.*;
// 10:00 ->
/*
    1. 전체를 2그룹으로 나누기
    2. 나눈 그룹을 각각 2그룹으로 나누기
    3. 만족도 점수 계산
    4. 만족도 하한 최신화
 */
public class BOJ_만족도점수 {
    static final int N = 8;
    static final double COUNTER = 1e-9;
    static double bestDiff = Double.POSITIVE_INFINITY;
    static int[] people = new int[N];
    static boolean[] used = new boolean[N];
    static void dfs(int depth, int start) {
        if(depth == N / 2) {
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                if(used[i]) l1.add(i);
                else l2.add(i);
            }

            calculate(l1,l2);
            return;
        }

        for(int i = start; i < N; i++) {
            if(used[i]) continue;
            used[i] = true;
            dfs(depth + 1, i + 1);
            used[i] = false;
        }
    }

    static double courtMinAvgDiff(List<Integer> idx) {
        int i = idx.get(0), j = idx.get(1), k = idx.get(2), l = idx.get(3);

        double d1 = Math.abs(((people[i] + people[j]) / 2.0) - ((people[k] + people[l]) / 2.0)); // (i,j)-(k,l)
        double d2 = Math.abs(((people[i] + people[k]) / 2.0) - ((people[j] + people[l]) / 2.0)); // (i,k)-(j,l)
        double d3 = Math.abs(((people[i] + people[l]) / 2.0) - ((people[j] + people[k]) / 2.0)); // (i,l)-(j,k)

        return Math.min(d1, Math.min(d2, d3));
    }

    static void calculate(List<Integer> l1, List<Integer> l2) {
        double dA = courtMinAvgDiff(l1);
        double dB = courtMinAvgDiff(l2);
        double worst = Math.max(dA, dB);
        bestDiff = Math.min(bestDiff, worst);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) people[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);

        double satisfaction = 1 - bestDiff / 10;
        double val = Math.round(satisfaction * 100.0) / 100.0; // 2자리 반올림
        if (Math.abs(val * 10 - Math.round(val * 10)) < COUNTER) {
            System.out.printf("%.1f%n", val); // 0.1 단위로 딱 떨어짐
        } else {
            System.out.printf("%.2f%n", val); // 0.05 단위 등
        }
        br.close();
    }
}
