import java.io.*;
import java.util.*;
public class BOJ_나머지합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        int[] pref = new int[N + 1];
        for(int i = 0; i < N; i++) pref[i + 1] = pref[i] + nums[i];




    }
}
