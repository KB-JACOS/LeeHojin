import java.io.*;
import java.util.*;
/*
1. List 입력 후 Collections.sort() -> O(n^2 * log(n))
2. PriorityQueue에 입력 후 비교 -> O(n * log(n))
 */
public class BOJ_가운데를말해요 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(left.size() == right.size()) {
                left.offer(n);
            } else {
                right.offer(n);
            }

            if(!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
                int a = left.poll();
                int b = right.poll();

                left.offer(b);
                right.offer(a);
            }
            sb.append(left.peek()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
