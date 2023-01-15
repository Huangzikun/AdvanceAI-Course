import matplotlib.pyplot as plt
import csv
import numpy as np

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/hm_best.csv", 'r') as f:
    csvReader = csv.reader(f)
    listSaBest = list(csvReader)
    listSaBest = np.array(listSaBest).flatten().astype("float64")

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/hm_score_list.csv", 'r') as f:
    csvReader = csv.reader(f)
    listSearch = list(csvReader)
    listSearch = np.array(listSearch).flatten().astype("float64")

xPoint = list(np.arange(1, len(listSearch)+1))

fig = plt.figure(figsize=(10, 6), dpi=800)
ax1 = fig.add_subplot(111)
ax1.plot(xPoint, listSearch, color="cyan", label="search")
ax1.plot(xPoint, listSaBest, color="orange", label="best")
ax1.legend(labels=('search', 'best'))

ax1.set_xlabel("iter")
ax1.set_ylabel("score")
plt.savefig("hm.jpg")

