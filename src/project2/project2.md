#### TSP Map
![TSP](../../tsp.png "tsp")

#### Search Process 
![search SA](../../hm.jpg "SA")

###### The shortest path we can find is 6-1-5-2-3-7-4-6
###### The sortest path is 379.

1. First, we need to initialize the content of the HMS. We randomly generate five solutions and sort them according to the distance value of the solutions.
```
for (int i=0; i<HMS; i++) {
            ArrayList<Integer> defaultPath = new ArrayList<>() {{
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
            }};
            Collections.shuffle(defaultPath);
            hmsArr.add(defaultPath);
        }

        System.out.println("default hms: " + hmsArr);



        //按从小到大排序，第一个是最小的
        //Sorted from small to large, the first is the smallest
        hmsArr.sort(getComparator());
        for (ArrayList<Integer> arrayList : hmsArr) {
            hmsMap.put(score(arrayList), arrayList);
        }
```
```
[
    [2, 7, 5, 1, 6, 4, 3], 
    [3, 6, 4, 5, 1, 7, 2], 
    [1, 5, 3, 4, 6, 7, 2], 
    [3, 2, 5, 7, 1, 4, 6], 
    [2, 1, 7, 6, 4, 5, 3]
    ]
```
2. According to the requirements, we need to iterate 500 times. For each iteration, we have the following operations
2.1 We need to iterate the number of TSP points (7 here). For each iteration, the first random number rnd1 is generated and compared with HMCR. If the random number rnd1 is less than HMCR, we go to step 2.2. If rnd1 is greater than HMCR, we randomly select a point to move.
2.2 We randomly select a value in HMS as the ith solution. We need to generate the second random number rnd2 and compare it with PAR. If rnd2 is less than PAR, we perform random+1 or - 1 operations on the selected. 
3. After 7 iterations, we can get a new solution. Compare its value with the last value in HMS. If it is less than the value in HMS, replace it and re-order it to get a new HMS

```
HMCR = 0.5
PAR = 0.1

rnd1 = 0.77, rnd2 = 0.00, select point = 6, sol = [6]
rnd1 = 0.05, rnd2 = 0.60, select point = 2, sol = [6, 2]
rnd1 = 0.77, rnd2 = 0.00, select point = 5, sol = [6, 2, 5]
rnd1 = 0.94, rnd2 = 0.00, select point = 4, sol = [6, 2, 5, 4]
rnd1 = 0.64, rnd2 = 0.00, select point = 7, sol = [6, 2, 5, 4, 7]
rnd1 = 0.47, rnd2 = 0.85, select point = 1, sol = [6, 2, 5, 4, 7, 1]
rnd1 = 0.32, rnd2 = 0.06, select point = 3, sol = [6, 2, 5, 4, 7, 1, 3]

new harmony sol = 677
bigger than the last HMS, not use

rnd1 = 0.68, rnd2 = 0.00, select point = 1, sol = [1]
rnd1 = 0.91, rnd2 = 0.00, select point = 7, sol = [1, 7]
rnd1 = 0.99, rnd2 = 0.00, select point = 4, sol = [1, 7, 4]
rnd1 = 0.20, rnd2 = 0.29, select point = 3, sol = [1, 7, 4, 3]
rnd1 = 0.06, rnd2 = 0.25, select point = 2, sol = [1, 7, 4, 3, 2]
rnd1 = 0.13, rnd2 = 0.06, select point = 6, sol = [1, 7, 4, 3, 2, 6]
rnd1 = 0.80, rnd2 = 0.00, select point = 5, sol = [1, 7, 4, 3, 2, 6, 5]
new harmony sol = 633
smaller than the last HMS, add this harmony

the new HMS:
{
    524=[3, 7, 6, 4, 2, 1, 5], 
    607=[3, 2, 5, 7, 6, 4, 1], 
    633=[1, 7, 4, 3, 2, 6, 5], 
    634=[6, 4, 7, 5, 3, 2, 1], 
    674=[4, 3, 7, 5, 6, 1, 2]
    }
```

##### improviseHarmony
```Java
for (int i = 0; i<tspMap.size(); i++) {

            double rnd1 = random.nextDouble();

            Integer randomSolPoint;

            if (rnd1 < HMCR) {
                System.out.println(hmsArr);
                ArrayList<Integer> randomSol = hmsArr.get(random.nextInt(0, hmsArr.size()));
                randomSolPoint = randomSol.get(random.nextInt(0, tspMap.size()));
                System.out.println(randomSolPoint);

                double rnd2 = random.nextDouble();

                if(rnd2 < PAR) {

                    // random +1 or -1
                    while (newHarmony.contains(randomSolPoint)) {

                        randomSolPoint = random.nextDouble(0, 1) > 0.5 ? randomSolPoint + 1 : randomSolPoint - 1;

                        if (randomSolPoint < 1) {
                            randomSolPoint = 1;
                            break;
                        }
                        if (randomSolPoint > tspMap.size()) {
                            randomSolPoint = tspMap.size();
                            break;
                        }
                    }
                }

            } else {
                randomSolPoint = random.nextInt(1, tspMap.size()+1);
            }

            // if random sol point contains, random one
            Integer c = 0;
            while (newHarmony.contains(randomSolPoint)) {
                c++;
                System.out.println("sol point repeat c="+c + "new = " + newHarmony);

                randomSolPoint = random.nextInt(1, tspMap.size()+1);
            }

            newHarmony.add(i, randomSolPoint);
        }
```


#### Code
```Java
package project2;

import Tool.Tool;

import java.io.IOException;
import java.util.*;

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


    static Integer HMS = 5;
    static Double HMCR = 0.5;

    static Double PAR = 0.1;

    static Integer NI = 500;

    private static final ArrayList<Integer> scoreList = new ArrayList<>(NI);


    public static <string> void main(String[] args) throws IOException {

        ArrayList<ArrayList<Integer>> hmsArr = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> hmsMap = new TreeMap<>();

        ArrayList<Integer> hmsBest = new ArrayList<>();
        ArrayList<Integer> search  = new ArrayList<>();
        ArrayList<Integer> accept  = new ArrayList<>();

        /**
         * init HMS
         */
        for (int i=0; i<HMS; i++) {
            ArrayList<Integer> defaultPath = new ArrayList<>() {{
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
            }};
            Collections.shuffle(defaultPath);
            hmsArr.add(defaultPath);
        }

        System.out.println("default hms: " + hmsArr);



        //按从小到大排序，第一个是最小的
        hmsArr.sort(getComparator());
        for (ArrayList<Integer> arrayList : hmsArr) {
            hmsMap.put(score(arrayList), arrayList);
        }

        System.out.println("default hmsMap: " + hmsMap);
        hmsBest.add((Integer) hmsMap.keySet().toArray()[0]);
        scoreList.add((Integer) hmsMap.keySet().toArray()[0]);


        for (int i = 0; i<NI; i++) {
            ArrayList<Integer> newHarmony = improviseHarmony(hmsArr);

            System.out.println("new harmony sol = " + score(newHarmony));

            Integer index = hmsArr.size()-1;

            if (score(newHarmony) < score(hmsArr.get(index))) {
                ArrayList<Integer> old = hmsArr.get(index);

                hmsArr.remove(index.intValue());
                hmsArr.add(index, newHarmony);

                hmsMap.remove(score(old));
                hmsMap.put(score(newHarmony), newHarmony);

                scoreList.add(score(newHarmony));
            } else {
                scoreList.add(score(hmsArr.get(index)));
            }

            System.out.println("hmsMap: " + hmsMap);

            hmsArr.sort(getComparator());

            hmsBest.add((Integer) hmsMap.keySet().toArray()[0]);
        }

        System.out.println(hmsArr);
        System.out.println(score(hmsArr.get(0)));
        System.out.println(scoreList);

        System.out.println(hmsBest);

        Tool.array2CSV(hmsBest, "hm_best.csv");
        Tool.array2CSV(scoreList, "hm_score_list.csv");
    }

    public static ArrayList<Integer> improviseHarmony(ArrayList<ArrayList<Integer>> hmsArr) {
        ArrayList<Integer> newHarmony = new ArrayList<>();

        Random random = (new Random());

        for (int i = 0; i<tspMap.size(); i++) {

            double rnd = random.nextDouble();

            Integer randomSolPoint;

            if (rnd < HMCR) {

                ArrayList<Integer> randomSol = hmsArr.get(random.nextInt(0, hmsArr.size()));
                randomSolPoint = randomSol.get(random.nextInt(0, tspMap.size()));

                if(rnd < PAR) {
                    while (newHarmony.contains(randomSolPoint)) {

                        randomSolPoint = random.nextDouble(0, 1) > 0.5 ? randomSolPoint + 1 : randomSolPoint - 1;

                        if (randomSolPoint < 1) {
                            randomSolPoint = 1;
                            break;
                        }
                        if (randomSolPoint > tspMap.size()) {
                            randomSolPoint = tspMap.size();
                            break;
                        }
                    }
                }

            } else {
                randomSolPoint = random.nextInt(1, tspMap.size()+1);
            }

            Integer c = 0;
            while (newHarmony.contains(randomSolPoint)) {
                c++;
                System.out.println("sol point repeat c="+c + "new = " + newHarmony);

                randomSolPoint = random.nextInt(1, tspMap.size()+1);
            }

            newHarmony.add(i, randomSolPoint);
        }

        return newHarmony;
    }

    private static Comparator<ArrayList<Integer>> getComparator() {

        return Comparator.comparingInt(Project2::score);
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

}

```