package project2;

import java.util.HashMap;
import java.util.Map;

public class Project2 {
    private static final Map<Integer, Map<Integer, Integer>> tspMap = new HashMap<>() {{
        put(1, new HashMap<>() {{
            put(2, 174);
            put(3, 88);
            put(4, 54);
            put(5, 23);
            put(6, 62);
            put(7, 140);
        }});
        put(2, new HashMap<>() {{
            put(1, 174);
            put(3, 79);
            put(4, 75);
            put(5, 70);
            put(6, 130);
            put(7, 143);
        }});
        put(3, new HashMap<>() {{
            put(1, 88);
            put(2, 79);
            put(4, 88);
            put(5, 55);
            put(6, 120);
            put(7, 23);
        }});
        put(4, new HashMap<>() {{
            put(1, 54);
            put(2, 75);
            put(3, 88);
            put(5, 66);
            put(6, 59);
            put(7, 63);
        }});
        put(5, new HashMap<>() {{
            put(1, 23);
            put(2, 70);
            put(3, 55);
            put(4, 66);
            put(6, 110);
            put(7, 142);
        }});
        put(6, new HashMap<>() {{
            put(1, 62);
            put(2, 130);
            put(3, 120);
            put(4, 59);
            put(5, 110);
            put(7, 115);
        }});

        put(7, new HashMap<>() {{
            put(1, 140);
            put(2, 143);
            put(3, 23);
            put(4, 63);
            put(5, 142);
            put(6, 115);
        }});
    }};

}
