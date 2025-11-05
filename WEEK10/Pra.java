import java.io.*;
import java.util.*;
public class Pra {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int L = Integer.parseInt(br.readLine());

        int[] line = new int[Integer.MAX_VALUE];
        for(int i = 0 ; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            for(int j = s; j <= t; j++) {
                line[j] = 1;
            }
        }

        int cnt = 0;

    }
}
