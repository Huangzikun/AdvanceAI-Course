package project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project1 {
    Map<Integer, ArrayList<Integer>> graph = new HashMap<>() {{
        put(1, new ArrayList<>() {{
            add(2);
            add(3);
            add(6);
        }});
        put(2, new ArrayList<>() {{
            add(1);
            add(3);
            add(5);
            add(6);
            add(8);
        }});
        put(3, new ArrayList<>() {{
            add(1);
            add(2);
            add(4);
            add(5);
            add(6);
            add(8);
        }});
        put(4, new ArrayList<>() {{
            add(3);
            add(6);
        }});
        put(5, new ArrayList<>() {{
            add(2);
            add(3);
            add(6);
            add(7);
            add(8);
        }});
        put(6, new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(7);
            add(8);
        }});
        put(7, new ArrayList<>() {{
            add(5);
            add(6);
            add(8);
        }});
        put(8, new ArrayList<>() {{
            add(2);
            add(3);
            add(5);
            add(6);
            add(7);
        }});
    }};


}
