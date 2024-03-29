package assignment6;

import Tool.Tool;

import java.io.IOException;
import java.util.*;

public class SAMain {

    private static final Map<Integer, Map<Integer, Integer>> tspMap = new HashMap<>() {{
        put(1, new HashMap<>() {{
            put(2, 200);
            put(3, 68);
            put(4, 99);
            put(5, 85);
            put(6, 62);
        }});
        put(2, new HashMap<>() {{
            put(1, 200);
            put(3, 230);
            put(4, 75);
            put(5, 70);
            put(6, 165);
        }});
        put(3, new HashMap<>() {{
            put(1, 68);
            put(2, 230);
            put(4, 88);
            put(5, 55);
            put(6, 120);
        }});
        put(4, new HashMap<>() {{
            put(1, 99);
            put(2, 75);
            put(3, 88);
            put(5, 175);
            put(6, 59);
        }});
        put(5, new HashMap<>() {{
            put(1, 85);
            put(2, 70);
            put(3, 55);
            put(4, 175);
            put(6, 110);
        }});
        put(6, new HashMap<>() {{
            put(1, 62);
            put(2, 165);
            put(3, 120);
            put(4, 59);
            put(5, 110);
        }});
    }};

    public static <string> void main(String[] args) throws IOException {

        Double DELTA = 0.98;
        Double T = 1000.0;
        Integer outSearchTime = 200;
        Integer inSearchTime = 30;

        /**
         * default
         */
        ArrayList<Integer> bestPath = new ArrayList<>();
        ArrayList<Integer> bestList = new ArrayList<>();
        ArrayList<Integer> acceptList = new ArrayList<>();

        bestPath.add(1);
        bestPath.add(2);
        bestPath.add(3);
        bestPath.add(4);
        bestPath.add(5);
        bestPath.add(6);
        Collections.shuffle(bestPath);

        Integer bestScore = score(bestPath);
        bestList.add(bestScore);
        acceptList.add(bestScore);

        /**
         * out search
         */
        for (int out=0; out<outSearchTime; out++) {
            Random r = new Random(2);

            Integer currentBestScore = bestScore;
            ArrayList<Integer> currentBestPath = bestPath;

            /**
             * in search
             */
            for (int i=0; i<inSearchTime; i++) {

                //new path
                ArrayList<Integer> currentPath = newPath(bestPath);
                Integer currentScore = score(currentPath);

                /**
                 * new path better, use
                 * or random use
                 */
                if(currentScore < bestScore) {
                    System.out.println(i + "lower bestScore = " + bestScore);
                    currentBestPath = currentPath;
                    currentBestScore = currentScore;
                    System.out.println("best");

                    break;

                } else {
                    double rd = r.nextDouble(0.0, 1.0);
                    double p = Math.exp(-Math.abs(currentBestScore - currentScore) / T);
                    if(p > rd) {
                        System.out.println(i + ": " + p + "： "+T);

                        currentBestPath = currentPath;
                        currentBestScore = currentScore;
                    }
                }
            }

            acceptList.add(currentBestScore);

            if(currentBestScore < bestScore) {
                bestScore = currentBestScore;
                bestPath = currentBestPath;
            }

            bestList.add(bestScore);

            T = T * DELTA;
        }

        Tool.array2CSV(acceptList, "sa_search.csv");
        Tool.array2CSV(bestList, "sa_best.csv");

        System.out.println("END:  current score: " + bestScore + "best path: " + bestPath.toString());
    }

    private static Integer score(ArrayList<Integer> arrayList) {
        int sum = 0;

        for(int i=0; i<arrayList.size(); i++) {
            Integer point1 = arrayList.get(i);
            Integer point2;
            if(i == arrayList.size()-1) {
                point2 = arrayList.get(0);
            } else {
                point2 = arrayList.get(i+1);
            }

            sum += tspMap.get(point1).get(point2);
        }

        return sum;
    }

    private static ArrayList<Integer> newPath(ArrayList<Integer> currentPath) {
        Random r = new Random();

        while(true) {
            Integer random1 = r.nextInt(0, 5);
            Integer random2 = r.nextInt(0, 5);

            if(random1.equals(random2)) {
                continue;
            }

            Integer temp = currentPath.get(random1);
            currentPath.set(random1, currentPath.get(random2));
            currentPath.set(random2, temp);

            break;
        }

        return currentPath;
    }
}
