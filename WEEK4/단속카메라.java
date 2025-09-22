import java.util.*;
/*
-20 -19 -18 -17 -16 -15 -14 -13 -12 -11 -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0
 ---------------------
          --------------------
                          -------------------------------
                                                         -------
*/
class 단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;
        int cam = Integer.MIN_VALUE; //현재 설치된 카메라 위치
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1])); //끝점 기준 오름차순 정렬 -> O(N log(N))

        // for(int[] r : routes) {
        //     System.out.println(r[0] + " , " + r[1]);
        // }
        for(int i = 0; i < routes.length; i++) {
            int start = routes[i][0];
            int end = routes[i][1];

            if(start > cam) {
                answer++;
                cam = end;
            }
        }
        return answer;
    }
}