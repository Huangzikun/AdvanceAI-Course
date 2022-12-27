package assignment7;

import java.io.IOException;
import java.util.*;

public class Tabu {

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
    private static final ArrayList<String> tabu = new ArrayList<>(2);

    public static <string> void main(String[] args) {
        /**
         * default
         */
        ArrayList<Integer> bestPath = new ArrayList<>();
        ArrayList<Integer> bestList = new ArrayList<>();
        ArrayList<Integer> acceptList = new ArrayList<>();

        bestPath.add(6);
        bestPath.add(1);
        bestPath.add(4);
        bestPath.add(2);
        bestPath.add(5);
        bestPath.add(3);

        Integer bestScore = score(bestPath);

        ArrayList<ArrayList<Integer>> newPathList = newPath(bestPath);
        ArrayList<Integer> scoreList = new ArrayList<>();

        System.out.println(score(bestPath));
        for(int x=0; x<newPathList.size(); x++) {
            ArrayList<Integer> path = newPathList.get(x);
            System.out.println(path + " = " + score(path));
        }
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

    private static ArrayList<ArrayList<Integer>> newPath(ArrayList<Integer> currentPath) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i=0; i<currentPath.size()-1; i++) {
            int j = i+1;

            ArrayList<Integer> tempArrayList = new ArrayList<>(currentPath);

            Integer temp = tempArrayList.get(i);
            tempArrayList.set(i, tempArrayList.get(j));
            tempArrayList.set(j, temp);

            list.add(tempArrayList);
        }

        return list;
    }


}
