import matplotlib.pyplot as plt
import csv
import numpy as np

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/sa_best.csv", 'r') as f:
    csvReader = csv.reader(f)
    listSaBest = list(csvReader)
    listSaBest = np.array(listSaBest).flatten().astype("float64")

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/sa_search.csv", 'r') as f:
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
plt.savefig("sa.jpg")

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/gd_best.csv", 'r') as f:
    csvReader = csv.reader(f)
    listGdBest = list(csvReader)
    listGdBest = np.array(listGdBest).flatten().astype("float64")

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/gd_search.csv", 'r') as f:
    csvReader = csv.reader(f)
    listSearch = list(csvReader)
    listSearch = np.array(listSearch).flatten().astype("float64")

with open("/Users/huangzikun/IdeaProjects/AdvanceAI/gd_level.csv", 'r') as f:
    csvReader = csv.reader(f)
    listLevel = list(csvReader)
    listLevel = np.array(listLevel).flatten().astype("float64")

xPoint = list(np.arange(1, len(listSearch)+1))
fig = plt.figure(figsize=(10, 6))
ax2 = fig.add_subplot(111)
ax2.plot(xPoint, listSearch, color="b", label="search")
ax2.plot(xPoint, listLevel, color="g", label='level')
ax2.plot(xPoint, listGdBest, color="r", label="best")

ax2.legend(labels=('search', 'level', 'best'))

ax2.set_xlabel("iter")
ax2.set_ylabel("score")
plt.savefig("gd.jpg")
