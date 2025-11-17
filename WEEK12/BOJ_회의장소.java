import java.io.*;
import java.util.*;

public class BOJ_회의장소 {

    // L 이상이 처음 나오는 인덱스
    static int lowerBound(int target, int[] a) {
        int s = 0, e = a.length;
        while (s < e) {
            int m = (s + e) / 2;
            if (a[m] >= target) e = m;
            else s = m + 1;
        }
        return s;
    }

    // R 초과가 처음 나오는 인덱스
    static int upperBound(int target, int[] a) {
        int s = 0, e = a.length;
        while (s < e) {
            int m = (s + e) / 2;
            if (a[m] > target) e = m;
            else s = m + 1;
        }
        return s;
    }

    static long getStresses(int l, int r, int m, int[] sets, long[] pref) {
        long x = sets[m];

        // 왼쪽 사람들: i ~ k-1
        long left = 0;
        if (m > l) {
            long sumLeft = pref[m] - pref[l];
            long cntLeft = m - l;
            left = cntLeft * x - sumLeft;
        }

        // 오른쪽 사람들: k+1 ~ j
        long right = 0;
        if (m < r) {
            long sumRight = pref[r + 1] - pref[m + 1];
            long cntRight = r - m;
            right = sumRight - cntRight * x;
        }

        return left + right;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] sets = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sets[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sets);

        long[] pref = new long[N + 1];
        for (int i = 0; i < N; i++) {
            pref[i + 1] = pref[i] + sets[i];
        }

        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            int left = lowerBound(L, sets);
            int right = upperBound(R, sets) - 1;

            // 구간에 사람 0명 또는 1명 이하 → 차이 0
            if (left >= right) {
                sb.append(0).append('\n');
                continue;
            }

            int mid = (left + right) / 2;

            long stressLeft = getStresses(left, right, left, sets, pref);
            long stressRight = getStresses(left, right, right, sets, pref);
            long stressMid = getStresses(left, right, mid, sets, pref);

            long answer = Math.max(stressLeft, stressRight) - stressMid;
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}