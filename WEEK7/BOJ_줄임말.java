import java.io.*;
import java.util.*;
/*
1. 완전탐색 -> O(S x T) => 불가
2. 이분탐색 -> O(T + Slog(T)) => 가능
 */
class 줄임말 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine(); //찾아야할 줄임말 문자열
        String T = br.readLine(); //반복될 문자열 -> T^n의 형태로 늘어남

        // 각 문자가 T안의 몇번째 순서인지 -> 위치 인덱스 저장
        List<List<Integer>> pos = new ArrayList<>(26);
        for(int i = 0; i < 26; i++) pos.add(new ArrayList<>());

        for(int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            pos.get(c - 'a').add(i);
        }

        //T에 없는 문자가 S에 있으면 불가능
        for(int i = 0; i < S.length(); i++) {
            if (pos.get(S.charAt(i) - 'a').isEmpty()) {
                System.out.println(-1);
                return;
            }
        }

        int repeats = 1; // T 반복 횟수
        int curr = -1; //현재 블록에서 소비한 마지막 인덱스

        for(int i = 0; i < S.length(); i++) {
            //찾아야 하는 알파벳이 들어있는 위치 인덱스
            List<Integer> list = pos.get(S.charAt(i) - 'a');

            int left = 0, right = list.size();

            while(left < right) {
                int mid = (left + right) / 2;
                if(list.get(mid) <= curr) left = mid + 1;
                else right = mid;
            }

            if(left == list.size()) { //현재 블록에 더 없으면 -> 다음 블록으로 넘어가서 첨 위치부터 다시
                repeats++;
                curr = list.get(0);
            }
            else {
                //같은 블록에서 계속 탐색
                curr = list.get(left);
            }
        }
        System.out.println(repeats);
    }
}
