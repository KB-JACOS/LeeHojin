import java.io.*;
import java.util.*;
// 10:30 -> 11:50
public class BOJ_K물류창고 {
    private static class Container{
        int p, w;
        public Container(int p, int w) {
            this.p = p;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] count = new int[M + 1]; //우선순위별 컨테이너 갯수

        Deque<Container> rail = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            count[p]++;
            rail.offerLast(new Container(p,w));
        }


        int cost = 0;
        List<Container> space = new ArrayList<>();

        while(!rail.isEmpty()) {
            Container top = rail.pollFirst();

            boolean check = true;
            for(int i = top.p + 1; i <= M; i++) {
                if(count[i] != 0) {
                    check = false;
                    break;
                }
            }

            if(!check) { //낮은 우선순위 컨테이너들이 모두 적재되지 않았을 경우
                cost += top.w;
                rail.offerLast(top);
            }
            else { //현재 우선순위의 컨테이너들의 적재 차례인 경우
                if(space.isEmpty()) { //컨테이너 비어있으면 무조건 삽입
                    cost += top.w;
                    count[top.p]--;
                    space.add(top);
                } else { //컨테이너가 비어있지 않을 경우
                    if(top.w <= space.get(space.size() - 1).w) { //바로 올라갈 수 있으면
                        cost += top.w;
                        count[top.p]--;
                        space.add(top);
                    } else { //바로 올라갈 수 없으면
                        cost += top.w;
                        count[top.p]--;
                        int index = 0;
                        for(int i = space.size() - 1; i >= 0; i--) {
                            int w2 = space.get(i).w;
                            if(w2 >= top.w) {
                                index = i + 1;
                                break;
                            }
                            cost += w2 * 2;
                        }
                        space.add(index, top);
                    }
                }
            }

            if(count[top.p] == 0) space = new ArrayList<>();
        }

        System.out.println(cost);
        br.close();

    }
}
