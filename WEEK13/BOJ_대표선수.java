import java.io.*;
import java.util.*;
public class BOJ_대표선수 {
    static class Node {
        int value;
        int row;
        int idx;

        public Node(int value, int row, int idx) {
            this.value = value;
            this.row = row;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            int v = arr[i][0];
            pq.offer(new Node(v, i, 0));
            if(v > max) max = v;
        }

        int answer = Integer.MAX_VALUE;

        while(true) {
            Node cur = pq.poll();
            int min = cur.value;

            answer = Math.min(answer, max - min);

            int r = cur.row;
            int idx = cur.idx;

            //반에서 더 뽑을 사람 없으면 중지
            if(idx == M - 1) break;

            // 해당 반에서 다음 학생 선택
            int nextIdx = idx + 1;
            int nextVal = arr[r][nextIdx];
            pq.offer(new Node(nextVal, r, nextIdx));

            if(nextVal > max) max = nextVal;
        }

        System.out.println(answer);
    }
}
