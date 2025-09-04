import java.io.*;
import java.util.*;

public class BJ13144 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100000 + 1];

        long cnt = 0;
        int r = 0;
        for (int l = 0; l < N; l++) {
            while (r < N && !visited[nums[r]]) {
                visited[nums[r]] = true;
                r++;
            }
            cnt += (r - l);

            visited[nums[l]] = false;
        }

        System.out.println(cnt);
    }
}

/*

1 2 3 2 2 1
 */