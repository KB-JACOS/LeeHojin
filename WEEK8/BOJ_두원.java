import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_두원 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x1 = Double.parseDouble(st.nextToken());
        double y1 = Double.parseDouble(st.nextToken());
        double r1 = Double.parseDouble(st.nextToken());

        double x2 = Double.parseDouble(st.nextToken());
        double y2 = Double.parseDouble(st.nextToken());
        double r2 = Double.parseDouble(st.nextToken());

        // 원의 중심 거리
        double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        double result = 0.0;

        // 1. 전혀 닿지 않는 경우 또는 한쪽이 완전히 포함되어도 접점조차 없는 경우
        if (d >= r1 + r2) {
            result = 0.0;
        }
        // 2. 한 원이 다른 원 내부에 완전히 포함 (접하지 않음)
        else if (d <= Math.abs(r1 - r2)) {
            double rMin = Math.min(r1, r2);
            result = Math.PI * rMin * rMin;
        }
        // 3. 그 외, 서로 겹치는 일반적인 경우 (렌즈 모양)
        else {
            double angle1 = 2 * Math.acos((r1 * r1 + d * d - r2 * r2) / (2 * r1 * d));
            double angle2 = 2 * Math.acos((r2 * r2 + d * d - r1 * r1) / (2 * r2 * d));

            double area1 = 0.5 * r1 * r1 * (angle1 - Math.sin(angle1));
            double area2 = 0.5 * r2 * r2 * (angle2 - Math.sin(angle2));

            result = area1 + area2;
        }

        System.out.printf("%.3f\n", result);
    }
}