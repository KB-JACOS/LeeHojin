import java.io.*;
import java.util.*;
public class BOJ_회의실배정 {
    static class Meeting{
        int start, end;
        public Meeting(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Meeting> list = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Meeting(s, e));
        }

        Collections.sort(list, (a, b) -> {
            if(a.end != b.end) return Integer.compare(a.end, b.end);
            return Integer.compare(a.start, b.start);
        });

        int count = 0, lastEnd = 0;

        for(Meeting m : list) {
            if(m.start >= lastEnd) {
                lastEnd = m.end;
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }
}
