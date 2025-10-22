import java.io.*;

public class BOJ_A와B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(T);
        while (sb.length() > S.length()) {
            if (sb.charAt(sb.length() - 1) == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            } else { // 'B'
                sb.deleteCharAt(sb.length() - 1);
                sb.reverse();
            }
        }
        System.out.println(sb.toString().equals(S) ? 1 : 0);
    }
}