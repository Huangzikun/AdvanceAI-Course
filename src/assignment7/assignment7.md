### Tabu Search Algorithm
#### Iterations

* 1 iteration
[1, 2, 3, 4, 5, 6] = 865
tabu-list = []
</br>
* 2 iteration
[2, 1, 3, 4, 5, 6] = 806
[1, 3, 2, 4, 5, 6] = 720
[1, 2, 4, 3, 5, 6] = 590 <font color=green>best</font>
[1, 2, 3, 5, 4, 6] = 781
[1, 2, 3, 4, 6, 5] = 772
tabu-list = [{3, 4}]
</br>
* 3 iteration
[2, 1, 4, 3, 5, 6] = 717
[1, 4, 2, 3, 5, 6] = 631 <font color=blue>interation best</font>
[1, 2, 3, 4, 5, 6] = 865
[1, 2, 4, 5, 3, 6] = 687
[1, 2, 4, 3, 6, 5] = 678
tabu-list = [{2, 4}, {3, 4}]
</br>
* 4 iteration
[4, 1, 2, 3, 5, 6] = 753
[1, 2, 4, 3, 5, 6] = 590
[1, 4, 3, 2, 5, 6] = 659
[1, 4, 2, 5, 3, 6] = 481 <font color=green>best</font>
[1, 4, 2, 3, 6, 5] = 719
tabu-list = [{3, 5}, {2, 4}]
</br>
* 5 iteration
[4, 1, 2, 5, 3, 6] = 603
[1, 2, 4, 5, 3, 6] = 687
[1, 4, 5, 2, 3, 6] = 756
[1, 4, 2, 3, 5, 6] = 631
[1, 4, 2, 5, 6, 3] = 542 <font color=blue>interation best</font>
tabu-list = [{3, 6}, {3, 5}]
</br>
* 6 iteration
[4, 1, 2, 5, 6, 3] = 687
[1, 2, 4, 5, 6, 3] = 748
[1, 4, 5, 2, 6, 3] = 697
[1, 4, 2, 6, 5, 3] = 572 <font color=blue>interation best</font>
[1, 4, 2, 5, 3, 6] = 481 <font color=red>!tabu</font>
tabu-list = [{5, 6}, {3, 6}]
</br>
* 7 iteration
[4, 1, 2, 6, 5, 3] = 717
[1, 2, 4, 6, 5, 3] = 567
[1, 4, 6, 2, 5, 3] = 516 <font color=green>best</font>
[1, 4, 2, 5, 6, 3] = 542
[1, 4, 2, 6, 3, 5] = 599
tabu-list = [{2, 6}, {5, 6}]
</br>
* 8 iteration
[4, 1, 6, 2, 5, 3] = 539
[1, 6, 4, 2, 5, 3] = 389 <font color=green>best</font>
[1, 4, 2, 6, 5, 3] = 572
[1, 4, 6, 5, 2, 3] = 636
[1, 4, 6, 2, 3, 5] = 693
tabu-list = [{4, 6}, {2, 6}]
</br>
* 9 iteration
[6, 1, 4, 2, 5, 3] = 481 <font color=blue>interation best</font>
[1, 4, 6, 2, 5, 3] = 516 
[1, 6, 2, 4, 5, 3] = 600
[1, 6, 4, 5, 2, 3] = 664
[1, 6, 4, 2, 3, 5] = 566
tabu-list = [{1, 6}, {4, 6}]
</br>
* 10 iteration
[1, 6, 4, 2, 5, 3] = 389 <font color=red>!tabu</font>
[6, 4, 1, 2, 5, 3] = 603
[6, 1, 2, 4, 5, 3] = 687
[6, 1, 4, 5, 2, 3] = 756
[6, 1, 4, 2, 3, 5] = 631
tabu-list = [{1, 4}, {1, 6}]


### Genetic Algorithm
#### a)
##### Selection
The selection operation selects excellent individuals from the old population with a certain probability to form a new population, so as to reproduce the next generation of individuals. The probability of an individual being selected is determined by a scoring function, such as the distance in the TSP problem.

##### Crossover
Cross operation refers to selecting two individuals from the population with a certain selection direction and randomness, and passing the excellent features of the parent string to the child string through the exchange and combination of two chromosomes, so as to generate new excellent individuals.

##### Mutation
Crossover can ensure that each evolution leaves good genes, but it only selects the original result set, which can only ensure that the calculation result is closer to the local optimal solution after N times of evolution. In order to jump out of the local optimal solution, we need to randomly select a gene mutation from 0 to 1 for mutation.

#### High rate of mutation
If the mutation rate is very high, it will lead to the failure of the previous screening and crossover, which is more like random search.

#### Use elitism
Elitism will fall into the local optimal solution and cannot jump out.

#### b)
```
1010
1000
0100
0110
1100
```
##### i
No. Because there is no set of intersections, the value of f(1111)=15 can be obtained.

##### ii
Yes.
<font color=red>11</font>00 + 01<font color=red>10</font> = 1110

Select the last bit 0 mutation to 1

1110 -> 111<font color=red>1</font>=15

#### c)
* Initial Population
``` 
011110110 = 0
011001011 = 1
101101110 = 0
000010101 = 2
```
<font color=red>01100</font>1011 + 00001<font color=red>0101</font> = 011000101
00001<font color=red>1011</font> + <font color=red>00001</font>0101 = 000011011

<font color=green>mutation secont bit</font>

* Seconnd Population
``` 
011001011 = 1
000010101 = 2
001000101 = 2
010011011 = 1
```
<font color=red>00001</font>0101 + 00100<font color=red>0101</font> = 000010101
00001<font color=red>0101</font> + <font color=red>00100</font>0101 = 001000101

<font color=green>mutation third bit</font>

* Third Population
``` 
000010101 = 2
001000101 = 2
010010101 = 3
011000101 = 1
```
It can be seen that the results will be more closer best: 010101010=4 after mutation and crossover.




