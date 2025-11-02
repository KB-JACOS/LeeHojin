import java.io.*;
import java.util.*;

//10: 53 -> 11:10
public class BOJ_카드정렬하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> cards = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            cards.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        while(cards.size() > 1) {
            int A = cards.poll();
            int B = cards.poll();
            answer +=  (A + B);


            cards.offer(A + B);
        }

        System.out.println(answer);
        br.close();
    }
}
