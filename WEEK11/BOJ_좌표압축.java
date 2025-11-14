import java.io.*;
import java.util.*;
//10:00 -> 10:30
public class BOJ_좌표압축 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] original = new int[N];
        List<Integer> sorted = new ArrayList<>();

        for(int i = 0; i < N; i++) { //1000000
            int num = Integer.parseInt(st.nextToken());
            original[i] = num;
            sorted.add(num);
        }

        List<Integer> n3 = new ArrayList<>(new HashSet<>(sorted));

        Collections.sort(n3); //10000000log1000000

        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < n3.size(); i++) { //1000000
            countMap.put(n3.get(i), i);
        }

        for(int i = 0; i < N; i++) { //1000000
            sb.append(countMap.get(original[i])).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
