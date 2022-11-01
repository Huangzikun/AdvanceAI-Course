package Week2;

import java.util.*;

/**
 * 1. init the Map. The key is the code of the point, the list is the list of points and lines
 * Map<Integer, ArrayList<Integer>> map = new HashMap<>();
 * {1=[2, 3, 4, 5, 6], 2=[1, 3, 4, 5, 6, 7], 3=[1, 2, 4, 5, 6, 7], 4=[1, 2, 3, 5, 6, 7], 5=[1, 2, 3, 4, 6, 7], 6=[1, 2, 3, 4, 5, 7], 7=[2, 3, 4, 5, 6]}
 * <p>
 * 2. sort map by lines number
 * map.sortBylinesNumber();
 * {2=[1, 3, 4, 5, 6, 7], 1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6], 3=[1, 2, 4, 5, 6, 7], 4=[1, 2, 3, 5, 6, 7], 5=[1, 2, 3, 4, 6, 7], 6=[1, 2, 3, 4, 5, 7]}
 * <p>
 * 3. check point if the point can use one color, use it and remove it.
 * while(map.notEmpty()) {
 *     foreach(map.point()) {
 *         if(point.canUseColor()) {
 *             map.removeThisPoint();
 *             color++;
 *         }
 *     }
 * }
 * {3=[1, 2, 4, 5, 6, 7], 1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6], 4=[1, 2, 3, 5, 6, 7], 5=[1, 2, 3, 4, 6, 7], 6=[1, 2, 3, 4, 5, 7]}
 * {4=[1, 2, 3, 5, 6, 7], 1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6], 5=[1, 2, 3, 4, 6, 7], 6=[1, 2, 3, 4, 5, 7]}
 * {5=[1, 2, 3, 4, 6, 7], 1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6], 6=[1, 2, 3, 4, 5, 7]}
 * {6=[1, 2, 3, 4, 5, 7], 1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6]}
 * {1=[2, 3, 4, 5, 6], 7=[2, 3, 4, 5, 6]}
 * {1=5, 2=0, 3=1, 4=2, 5=3, 6=4, 7=5}

 so
 q1: The least is 6
 q2:
 1: S2
 2: S1, S3
 3: S4
 4: S5
 5: S6
 7: S7
 */
public class CourseGraphColorMain {
    public static <string> void main(String[] args) {

        ArrayList<String> S1 = new ArrayList<>() {{
            add("Math");
            add("English");
            add("Biology");
            add("Chemistry");
        }};
        ArrayList<String> S2 = new ArrayList<>(){{
            add("Math");
            add("English");
            add("Computer Science");
            add("Geography");
        }};
        ArrayList<String> S3 = new ArrayList<>() {{
           add("Biology");
           add("Psychology");
           add("Geography");
           add("Spanish");
        }};
        ArrayList<String> S4 = new ArrayList<>() {{
            add("Biology");
            add("Computer Science");
            add("History");
            add("French");
        }};
        ArrayList<String> S5 = new ArrayList<>() {{
            add("English");
            add("Psychology");
            add("Computer Science");
            add("History");
        }};
        ArrayList<String> S6 = new ArrayList<>() {{
            add("Psychology");
            add("Chemistry");
            add("Computer Science");
            add("French");
        }};
        ArrayList<String> S7 = new ArrayList<>() {{
            add("Psychology");
            add("Geography");
            add("History");
            add("Spanish");
        }};

        ArrayList<ArrayList<String>> list = new ArrayList<>(){{
            add(S1);
            add(S2);
            add(S3);
            add(S4);
            add(S5);
            add(S6);
            add(S7);
        }};


        /**
         * init the Map. The key is the code of the point, the list is the list of points and lines
         * {1=[2, 4, 5], 2=[1, 5, 6], 3=[4, 5], 4=[1, 3, 6], 5=[1, 2, 3, 6], 6=[2, 4, 5]}
         */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0; i<list.size(); i++) {

            map.put(i+1, new ArrayList<>());
            ArrayList<String> list1 = list.get(i);

            for (int j=0; j<list.size(); j++) {
                if(i == j) {
                    continue;
                }

                ArrayList<String> list2 = new ArrayList<>(list.get(j));
                list2.retainAll(list1);

                if(list2.size() > 0) {
                    map.get(i+1).add(j+1);
                }
            }
        }

        Map<Integer, Integer> resultMap = new HashMap<>();
        loop(map, resultMap, 0);

        System.out.println(resultMap);

    }

    static void loop(Map<Integer, ArrayList<Integer>> map, Map<Integer, Integer> resultMap, Integer color)
    {
        while (map.size() > 0) {

            Map<Integer, ArrayList<Integer>> paintPoint = new HashMap<>();
            /**
             * sort by size
             * {5=[1, 2, 3, 6], 1=[2, 4, 5], 6=[2, 4, 5], 2=[1, 5, 6], 3=[4, 5], 4=[1, 3, 6]}
             */
            LinkedList<ArrayList<Integer>> linkedList = new LinkedList<>();
            for (Integer i : map.keySet()) {
                if(linkedList.isEmpty()) {
                    linkedList.push(map.get(i));
                } else {
                    if(map.get(i).size() > linkedList.getFirst().size()) {
                        linkedList.addFirst(map.get(i));
                    } else {
                        linkedList.addLast(map.get(i));
                    }
                }
            }
            Map<Integer, ArrayList<Integer>> sortMap = new LinkedHashMap<>();
            for (ArrayList<Integer> list : linkedList) {
                for (Integer i : map.keySet()) {
                    if(list.equals(map.get(i))) {
                        sortMap.put(i, map.get(i));
                    }
                }
            }
            /**
             * ---- end sort
             */

            System.out.println(sortMap);
            for (Integer key: sortMap.keySet()) {
                ArrayList<Integer> list = sortMap.get(key);

                /**
                 * check point
                 */
                boolean b = false;
                for (Integer pp: paintPoint.keySet()) {
                    if(list.contains(pp)) {
                        b = true;
                    }
                }
                if(!b) {
                    paintPoint.put(key, sortMap.get(key));
                    resultMap.put(key, color);
                }
            }

            /**
             * remove point
             */
            if(paintPoint.size() > 0) {
                for (Integer pp: paintPoint.keySet()) {
                    map.remove(pp);
                }
            }

            color++;
            loop(map, resultMap, color);
        }
    }
}
