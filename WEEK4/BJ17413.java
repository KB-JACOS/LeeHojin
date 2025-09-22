import java.io.*;
import java.util.*;

// 10:00 시작 -> 10:40 종료
public class BJ17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine().trim();
        List<String> output = new ArrayList<>();

        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c == ' ') {
                if(stack.peekFirst() == '<') {
                    stack.offerLast(c);
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty()) {
                    sb.append(stack.pollLast());
                }
                sb.append(' ');
                output.add(sb.toString());
            } else if(c == '>') {
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty()) {
                    sb.append(stack.pollFirst());
                }
                sb.append('>');
                output.add(sb.toString());
            } else if (c == '<') {
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty()) {
                    sb.append(stack.pollLast());
                }
                output.add(sb.toString());
                stack.offerLast('<');
            } else stack.offerLast(c);
        }

        if(!stack.isEmpty()) {
            StringBuilder rest = new StringBuilder();
            while(!stack.isEmpty()) {
                rest.append(stack.pollLast());
            }
            output.add(rest.toString());
        }


        StringBuilder answer = new StringBuilder();
        for(String str : output) {
            answer.append(str);
        }

        System.out.println(answer);
        br.close();
    }
}
