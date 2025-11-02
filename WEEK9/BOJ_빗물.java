import java.io.*;
import java.util.*;
public class BOJ_빗물 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int maxHeight = 0;
        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());

            maxHeight = Math.max(maxHeight, blocks[i]);
        }

        int leftMaxIndex = 0, rightMaxIndex = W - 1;
        for(int i = 0; i < W; i++) {
            if(blocks[leftMaxIndex] == maxHeight) break;
            leftMaxIndex++;
        }

        for(int j = W - 1; j >= 0; j--) {
            if(blocks[rightMaxIndex] == maxHeight) break;
            rightMaxIndex--;
        }

        int sum = 0;
        int leftMaxHeight = blocks[0];
        for(int i = 0; i < leftMaxIndex; i++) {
            leftMaxHeight = Math.max(leftMaxHeight, blocks[i]);
            sum += (leftMaxHeight - blocks[i]);
        }

        int rightMaxHeight = blocks[W - 1];
        for(int i = W - 1; i > rightMaxIndex; i--) {
            rightMaxHeight = Math.max(rightMaxHeight, blocks[i]);
            sum += (rightMaxHeight - blocks[i]);
        }

        for(int i = leftMaxIndex; i < rightMaxIndex; i++) {
            sum += (maxHeight - blocks[i]);
        }

        System.out.println(sum);
        br.close();
    }
}
