import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();
        int N = genres.length;
        Map<String, Integer> genreCount = new HashMap<>();
        Map<Integer, Integer> playCount = new HashMap<>();

        //입력
        for(int i = 0; i < N; i++) {
            String genre = genres[i];
            int play = plays[i];

            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + play);
        }

        //genre 역순으로 정렬
        List<Map.Entry<String, Integer>> list = new ArrayList<>(genreCount.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> sortedByValue = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry : list) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }

        for(String genre : sortedByValue.keySet()) {
            Map<Integer, Integer> temp = new HashMap<>();

            for(int i = 0; i < N; i++) {
                if(genre.equals(genres[i])) {
                    temp.put(i, plays[i]);
                }
            }

            List<Map.Entry<Integer, Integer>> tempList = new ArrayList<>(temp.entrySet());
            tempList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            int cnt = 0;
            for(int i = 0; i < tempList.size(); i++) {
                if(cnt == 2) break;
                result.add(tempList.get(i).getKey());
                cnt++;
            }
        }

        int[] answer = new int[result.size()];
        for(int i= 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}