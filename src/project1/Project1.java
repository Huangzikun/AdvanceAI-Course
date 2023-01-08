package project1;

import java.util.*;

public class Project1 {

    static List<String> allMeetings = new ArrayList<>() {{
        add("P1");
        add("P2");
        add("P3");
        add("P4");
        add("P5");
        add("P6");
        add("P7");
        add("P8");
    }};
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
    static Map<Integer, Map<Integer, Map<Integer, String>>> arrangeMap = new HashMap<>() {{
        put(1, new HashMap<>() {{
            put(1, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
            put(2, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
        }});
        put(2, new HashMap<>() {{
            put(1, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
            put(2, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
        }});
        put(3, new HashMap<>() {{
            put(1, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
            put(2, new HashMap<>() {{
                put(1, null);
                put(2, null);
            }});
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

    /**
     * Actual cost
     * 实际代价
     */
    static Integer G(Map<Integer, Map<Integer, Map<Integer, String>>> arrangeMap) {
        Integer solution = 0;

        ArrayList<String> meetings = new ArrayList<>();
        Map<Integer, LinkedHashSet<String>> currentDayMap = new HashMap<>();

        for (int day = 1; day <= arrangeMap.size(); day++) {
            currentDayMap.put(day, new LinkedHashSet<>());

            for (int slot = 1; slot <= arrangeMap.get(day).size(); slot++) {
                for (int room = 1; room <= arrangeMap.get(day).get(slot).size(); room++) {
                    String currentMeeting = arrangeMap.get(day).get(slot).get(room);

                    //If it is empty, it indicates that it has not been arranged here
                    //如果是空，表明还没有安排到这里
                    if (!Objects.isNull(currentMeeting)) {
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

        /**
         * iii: Every day D1 or D2 when a room is unoccupied, there will be a two-point penalty (not reserved for a meeting).
         * 每天D1或D2房间无人时，将处以两分罚款
         */
        if(currentDayMap.get(1).size() < 4) {
            solution += 2;
        }
        if(currentDayMap.get(2).size() < 4) {
            solution += 2;
        }

        return solution;
    }

    /**
     * Estimated cost
     * 预估代价
     */
    static Map<String, Integer> H(Map<Integer, Map<Integer, Map<Integer, String>>> currentArrangeMap, List<String> openList) {
        Map<String, Integer> solution = new HashMap<>();

        Integer G = G(currentArrangeMap);

        Over:
        {
            for (int day = 1; day <= arrangeMap.size(); day++) {
                for (int slot = 1; slot <= arrangeMap.get(day).size(); slot++) {
                    for (int room = 1; room <= arrangeMap.get(day).get(slot).size(); room++) {
                        String currentMeeting = arrangeMap.get(day).get(slot).get(room);

                        //If it is empty, arrange here
                        //如果是空，安排
                        if (Objects.isNull(currentMeeting)) {

                            Integer D = day;
                            Integer S = slot;
                            Integer R = room;

                            if(room == 1) {
                                if(slot == 1) {
                                    //前一天的最后一个
                                    D = Math.min(1, day - 1);
                                    R = 2;
                                    S = 2;
                                } else {
                                    S = Math.min(1, slot - 1);
                                    R = 2;
                                }
                            } else {
                                R = Math.min(1, room-1);
                            }

                            String lastMeeting = arrangeMap.get(D).get(S).get(R);

                            for (String meeting : openList) {

                                Map<Integer, Map<Integer, Map<Integer, String>>> testMap = new HashMap<>(currentArrangeMap);
                                testMap.get(day).get(slot).put(room, meeting);
                                Integer testG = G(testMap);
                                /**
                                 * 已知如果这么安排会增加的罚分
                                 */
                                Integer addSol = testG - G;

                                /**
                                 * 预测如果这样安排会有多少罚分
                                 */
                                List<String> unArrangeList = new ArrayList<>(openList);
                                unArrangeList.remove(meeting);

                                for (int i = 0; i<unArrangeList.size(); i++) {
                                    List<String> A;
                                    if(i == 0) {
                                        A = new ArrayList<>(graph.get(meeting));
                                    } else {
                                        A = new ArrayList<>(graph.get(unArrangeList.get(i-1)));
                                    }

                                    List<String> B = new ArrayList<>(graph.get(unArrangeList.get(i)));

                                    A.retainAll(B);

                                    //之后的所有有变化的差集都有可能导致加分
                                    addSol += A.size();
                                }



                                solution.put(meeting, addSol);
                            }



                            break Over;
                        }
                    }
                }
            }
        }




        return solution;
    }

    /**
     * !!! Hard constraint
     * Next allowed meeting
     * @param arrangeMap
     * @return
     */
    static List<String> openList(Map<Integer, Map<Integer, Map<Integer, String>>> arrangeMap) {
        List<String> openList = new ArrayList<>(allMeetings);
        ArrayList<String> meetings = new ArrayList<>();


        for (int day = 1; day <= arrangeMap.size(); day++) {
            for (int slot = 1; slot <= arrangeMap.get(day).size(); slot++) {
                for (int room = 1; room <= arrangeMap.get(day).get(slot).size(); room++) {
                    String currentMeeting = arrangeMap.get(day).get(slot).get(room);

                    //If it is empty, it indicates that it has not been arranged here
                    //如果是空，表明还没有安排到这里
                    if (!Objects.isNull(currentMeeting)) {
                        meetings.add(currentMeeting);
                    }
                }
            }
        }

        /**
         * Get the list of unscheduled meetings
         * 取没安排的会议列表
         */
        openList.removeAll(meetings);

        Over:
        {
            for (int day = 1; day <= arrangeMap.size(); day++) {
                for (int slot = 1; slot <= arrangeMap.get(day).size(); slot++) {
                    for (int room = 1; room <= arrangeMap.get(day).get(slot).size(); room++) {
                        String currentMeeting = arrangeMap.get(day).get(slot).get(room);

                        //If it is empty, arrange here
                        //如果是空，安排
                        if (Objects.isNull(currentMeeting)) {

                            //If it is the second room, you need to check the conflict with the first room
                            //如果当前是第二个房间，需要检查和第一个房间的冲突
                            if(room == 2) {
                                String room1Meeting = arrangeMap.get(day).get(slot).get(1);
                                List<String> conflict = graph.get(room1Meeting);

                                openList.removeAll(conflict);
                            }

                            break Over;
                        }

                        meetings.add(currentMeeting);
                    }
                }
            }
        }

        return openList;
    }

    public static Integer eval(Map<Integer, Map<Integer, List<Integer>>> arrangeMap) {



        return 0;
    }

    public static <string> void main(String[] args) {
        Map<String, ArrayList<String>> sort = sortMap(graph);

        System.out.println(sort);

        arrangeMap.get(1).get(1).put(1, "P1");

        Integer s = G(arrangeMap);
        System.out.println(s);

        List<String> openList = openList(arrangeMap);
        System.out.println(openList);

        H(arrangeMap, openList);

    }
}
