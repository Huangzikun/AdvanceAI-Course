## question

- S1: Math, English, Biology, Chemistry
- S2: Math, English, Computer Science, Geography
- S3: Biology, Psychology, Geography, Spanish
- S4: Biology, Computer Science, History, French
- S5: English, Psychology, Computer Science, History
- S6: Psychology, Chemistry, Computer Science, French
- S7: Psychology, Geography, History, Spanish

Let say, we need to schedule the exams for these students (see assignment 2) within 4 examination days, where each exam day has two exam slots.
  The following penalty will be imposed if:
  - Penalty = 2 for each student that need to seat 2 exams consecutively in the same day.
  - Penalty = 1 for each student that has one slot gap between two exams.

Find an examination timetable that spread the exam for each student across the examination periods (within 4 days), i.e. an exam timetable with minimum penalty. You may use any local search (e.g. Hill Climbing, Simulated Annealing, etc.) to search for a better schedule (just show 2 iterations).

Calculate the quality of the generated timetable.

-----
| day1.1       | day1.2         | day2.1   | day2.2    | day3.1           | day3.2     | day4.1      | day4.2   |
|--------------|----------------|----------|-----------|------------------|------------|-------------|----------|
| Math,History | English,French | Biology  | Chemistry | Computer Science | Geography  | Psychology  | Spanish  |


s1 penalty = 2 + 2 = 4
s2 penalty = 2 + 2 = 4
s3 penalty = 2
s4 penalty = 1 + 2 = 3
s5 penalty = 1
s6 penalty = 1
s7 penalty = 2
all penalty = 4+4+2+3+1+1+2 = 17

First iteration: select one course put it on another slot

| day1.1 | day1.2         | day2.1  | day2.2            | day3.1           | day3.2     | day4.1      | day4.2   |
|--------|----------------|---------|-------------------|------------------|------------|-------------|----------|
| Math   | English,French | Biology | Chemistry,History | Computer Science | Geography  | Psychology  | Spanish  |

s1 penalty = 2 + 2 = 4
s2 penalty = 2 + 2 = 4
s3 penalty = 2
s4 penalty = 2
s5 penalty = 1 + 1 = 2
s6 penalty = 1 + 1 = 2
s7 penalty = 1 + 2 = 3
all penalty = 4+4+2+2+1+2+3 = 18
so don't select

| day1.1 | day1.2         | day2.1  | day2.2            | day3.1           | day3.2     | day4.1          | day4.2   |
|--------|----------------|---------|-------------------|------------------|------------|-----------------|----------|
|        | English,French | Biology | Chemistry,History | Computer Science | Geography  | Psychology,Math | Spanish  |

s1 penalty = 2
s2 penalty = 2
s3 penalty = 2
s4 penalty = 2
s5 penalty = 1 + 1 = 2
s6 penalty = 1 + 1 = 2
s7 penalty = 1 + 2 = 3
all penalty = 2+2+2+2+2+2+3 = 15
select this one


Second iteration: select one course put it on another slot

| day1.1     | day1.2         | day2.1  | day2.2            | day3.1           | day3.2     | day4.1 | day4.2   |
|------------|----------------|---------|-------------------|------------------|------------|--------|----------|
| Psychology | English,French | Biology | Chemistry,History | Computer Science | Geography  | Math   | Spanish  |
s1 = 2
s2 = 2
s3 = 1 + 1 = 2
s4 = 2
s5 = 2
s6 = 2
s7 = 1 + 1 = 2
all = 14
select this one


Calculate penalty for the current timetable.
And move one course, satisfy hard contract as a list, no student's exam conflicts.
If this list is empty, return.
Iteration all item in this list, if anyone's penalty less than globalPenalty, use this as globalPenalty.
```
globalPenalty = 999;
globalTimetable = null;
function iteration(timetable) {
    
    newPenalty = penalty(timetable);
    if(newPenalty <= globalPenalty) {
        globalPenalty   = newPenalty;
        globalTimetable = timetable;
    } else {
        return ;
    }
    
    newTimetableList = moveOneCourseSatisfyHardcontract();
    if(newTimetableList.isEmpty()) {
      return ;
    }
    
    foreach(newTimetableList as newTimetable) {
        interation(newTimetable);
    }
}
```

