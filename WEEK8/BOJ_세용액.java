import java.io.*;
import java.util.*;
public class BOJ_세용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] nums = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        long minSum = 3000000000L;
        int leftNum = 0;
        int rightNum = 0;
        int middleNum = 0;

        for(int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while(left < right) {

                long newSum = nums[i] + nums[left] + nums[right];
                if(Math.abs(newSum) < minSum) {
                    minSum = Math.abs(newSum);
                    leftNum = i; middleNum = left; rightNum = right;
                }
                if(newSum == 0) {
                    break;
                }else if(newSum < 0) left += 1;
                 else right -= 1;
            }
        }
        System.out.println(nums[leftNum] + " " + nums[middleNum] + " " + nums[rightNum]);
        br.close();

    }
}
/*
5
-97 -6 -2 6 98
----------------------------------------
7
-24 -6 -3 -2 61 98 100
----------------------------------------
11
-100 -90 -80 -70 -60 -50 -40 -30 -20 -10 10
----------------------------------------

100 000 000
 */