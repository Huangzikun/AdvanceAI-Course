package project1;

import java.util.*;

public class Project1 {

    static Integer researcherCount = 10;
    /**
     * meeting and has the same user meeting
     */
    static Map<String, ArrayList<String>> graph = new HashMap<>() {{
        put("P1", new ArrayList<>() {{
            add("P2");
            add("P3");
            add("P6");
        }});
        put("P2", new ArrayList<>() {{
            add("P1");
            add("P3");
            add("P5");
            add("P6");
            add("P8");
        }});
        put("P3", new ArrayList<>() {{
            add("P1");
            add("P2");
            add("P4");
            add("P5");
            add("P6");
            add("P8");
        }});
        put("P4", new ArrayList<>() {{
            add("P3");
            add("P6");
        }});
        put("P5", new ArrayList<>() {{
            add("P2");
            add("P3");
            add("P6");
            add("P7");
            add("P8");
        }});
        put("P6", new ArrayList<>() {{
            add("P1");
            add("P2");
            add("P3");
            add("P4");
            add("P5");
            add("P7");
            add("P8");
        }});
        put("P7", new ArrayList<>() {{
            add("P5");
            add("P6");
            add("P8");
        }});
        put("P8", new ArrayList<>() {{
            add("P2");
            add("P3");
            add("P5");
            add("P6");
            add("P7");
        }});
    }};

    static Map<Integer, ArrayList<String>> researchers = new HashMap<>() {{
        put(1, new ArrayList<>() {{
            add("P1");
            add("P2");
            add("P3");
            add("P6");
        }});
        put(2, new ArrayList<>() {{
            add("P2");
            add("P3");
        }});
        put(3, new ArrayList<>() {{
            add("P2");
            add("P3");
            add("P5");
            add("P8");
        }});
        put(4, new ArrayList<>() {{
            add("P4");
            add("P6");
        }});
        put(5, new ArrayList<>() {{
            add("P1");
            add("P6");
        }});
        put(6, new ArrayList<>() {{
            add("P1");
            add("P3");
            add("P4");
            add("P6");
        }});
        put(7, new ArrayList<>() {{
            add("P7");
            add("P8");
        }});
        put(8, new ArrayList<>() {{
            add("P5");
            add("P7");
        }});
        put(9, new ArrayList<>() {{
            add("P1");
            add("P2");
        }});
        put(10, new ArrayList<>() {{
            add("P5");
            add("P6");
            add("P7");
            add("P8");
        }});
    }};

    /**
     * 3day -> 2slots -> 2room
     */
    static Map<Integer, Map<Integer, List<String>>> arrangeMap = new HashMap<>() {{
        put(1, new HashMap<>() {{
            put(1, new ArrayList<>(2));
            put(2, new ArrayList<>(2));
        }});
        put(2, new HashMap<>() {{
            put(1, new ArrayList<>(2));
            put(2, new ArrayList<>(2));
        }});
        put(3, new HashMap<>() {{
            put(1, new ArrayList<>(2));
            put(2, new ArrayList<>(2));
        }});
    }};

    static Map<String, ArrayList<String>> sortMap(Map<String, ArrayList<String>> map) {
        /**
         * sort by size
         */
        LinkedList<ArrayList<String>> linkedList = new LinkedList<>();
        for (String i : map.keySet()) {
                linkedList.push(map.get(i));
        }

        linkedList.sort((o1, o2) -> o1.size() - o2.size());

        Map<String, ArrayList<String>> sortMap = new LinkedHashMap<>();
        for (ArrayList<String> list : linkedList) {
            for (String i : map.keySet()) {
                if(list.equals(map.get(i))) {
                    sortMap.put(i, map.get(i));
                }
            }
        }

        return sortMap;
    }

    static Integer G(Map<Integer, Map<Integer, List<String>>> arrangeMap) {
        Integer solution = 0;


        ArrayList<String> meetings = new ArrayList<>();
        Map<Integer, LinkedHashSet<String>> currentDayMap = new HashMap<>();

        Over:
        {
            for (int day = 1; day <= arrangeMap.size(); day++) {
                currentDayMap.put(day, new LinkedHashSet<>());

                for (int slot = 1; slot <= arrangeMap.get(day).size(); slot++) {
                    for (int room = 0; room < arrangeMap.get(day).get(slot).size(); room++) {
                        String currentMeeting = arrangeMap.get(day).get(slot).get(room);

                        //If it is empty, it indicates that it has not been arranged here
                        //如果是空，表明还没有安排到这里
                        if (Objects.isNull(currentMeeting)) {
                            break Over;
                        }

                        meetings.add(currentMeeting);
                        currentDayMap.get(day).add(currentMeeting);

                    }
                }
            }
        }

        /**
         * cal i: n penalty is given if the researcher is idle (does not attend a meeting
         * between two meetings) for n timeslots (i.e., n=1,2,3,...).
         * 如果研究者在n个时隙（即，n＝1，2，3，…）内空闲（不参加两次会议之间的会议），则给予n个惩罚。
         */
        for (int i=1; i<=researchers.size(); i++) {
            for (String s: meetings) {
                if(!researchers.get(i).contains(s)) {
                    solution++;
                }
            }
        }

        /**
         * cal ii: If a researcher has only one meeting scheduled per day, they lose one
         * point (whereas he actually has more than one project meeting).
         * 如果研究人员每天只安排一次会议，他们会失去一分
         */
        for (int i=1; i<=researchers.size(); i++) {
            for (int day = 1; day <= arrangeMap.size(); day++) {
                LinkedHashSet<String> currentDay = currentDayMap.get(day);

                //cross
                List<String> copy = new ArrayList<>(researchers.get(i));
                copy.retainAll(currentDay);

                if(copy.size() == 1) {
                    solution++;
                }
            }
        }

        return solution;
    }

    public static Integer eval(Map<Integer, Map<Integer, List<Integer>>> arrangeMap) {


        return 0;
    }

    public static <string> void main(String[] args) {
        Map<String, ArrayList<String>> sort = sortMap(graph);

        System.out.println(sort);

        arrangeMap.put(1, new HashMap<>() {{
            put(1, new ArrayList<>() {{
                add("P1");
            }});
        }});

        Integer s = G(arrangeMap);
        System.out.println(s);
    }
}
