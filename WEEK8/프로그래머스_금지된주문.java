import java.util.*;

public class 프로그래머스_금지된주문 {
    private static final long[] P = new long[12];
    private static final long[] PREF = new long[12];

    static {
        P[0] = 1;
        for (int i = 1; i <= 11; i++) P[i] = P[i - 1] * 26L;
        long acc = 0;
        for (int i = 1; i <= 11; i++) {
            acc += P[i];
            PREF[i] = acc;
        }
    }

    public String solution(long n, String[] bans) {
        // 1) 금지어를 전체 랭크로 변환해 정렬
        long[] banRanks = new long[bans.length];
        for (int i = 0; i < bans.length; i++) banRanks[i] = rankOf(bans[i]);
        Arrays.sort(banRanks);

        long TOTAL = PREF[11];

        // 2) 이분 탐색: valid(≤x) = x - cntBan(≤x) >= n 인 최소 x
        long lo = 1, hi = TOTAL;
        while (lo < hi) {
            long mid = lo + ((hi - lo) >>> 1);
            long valid = mid - upperBound(banRanks, mid); // 금지어 개수 빼기
            if (valid >= n) hi = mid;
            else lo = mid + 1;
        }
        // lo 가 답의 전체 랭크
        return strOfRank(lo);
    }

    // bans에서 mid 이하의 개수
    private static int upperBound(long[] a, long key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= key) l = m + 1;
            else r = m;
        }
        return l;
    }

    // 문자열 s의 전체 랭크(1-based)
    private static long rankOf(String s) {
        int L = s.length();
        long base = PREF[L - 1]; // 길이 < L 인 모든 수
        long val = 0;
        for (int i = 0; i < L; i++) {
            val = val * 26 + (s.charAt(i) - 'a');
        }
        return base + (val + 1);
    }

    // 전체 랭크(1-based) → 문자열
    private static String strOfRank(long r) {
        // 길이 L 찾기: PREF[L-1] < r ≤ PREF[L]
        int L = 1;
        while (PREF[L] < r) L++;
        long offset = r - PREF[L - 1] - 1; // 0-based
        char[] chars = new char[L];
        for (int i = L - 1; i >= 0; i--) {
            int d = (int)(offset % 26);
            chars[i] = (char)('a' + d);
            offset /= 26;
        }
        return new String(chars);
    }
}