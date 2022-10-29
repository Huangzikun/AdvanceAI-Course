package Week2;

import java.util.*;

public class GraphColorMain {
    public static <string> void main(String[] args) {

        ArrayList<String> UE = new ArrayList<>() {{
            add("Sineman");
            add("Limitson");
            add("Axiomus");
            add("Functionini");
        }};

        ArrayList<String> GE = new ArrayList<>(){{
            add("Graphian");
            add("Vectorades");
            add("Functionini");
            add("Infinitescu");
        }};

        ArrayList<String> C = new ArrayList<>() {{
           add("Lemmeau");
           add("Randomov");
           add("Proofizaki");
        }};

        ArrayList<String> L = new ArrayList<>() {{
            add("Van Sum");
            add("Sineman");
            add("Lemmeau");
        }};

        ArrayList<String> S = new ArrayList<>() {{
            add("Graphian");
            add("Randomov");
            add("Vectorades");
            add("Limitson");
        }};

        ArrayList<String> P = new ArrayList<>() {{
            add("Vectorades");
            add("Van Sum");
            add("Parabolton");
        }};

        ArrayList<ArrayList<String>> list = new ArrayList<>(){{
            add(UE);
            add(GE);
            add(C);
            add(L);
            add(S);
            add(P);
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
