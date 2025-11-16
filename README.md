# Threads run results

Idea
* Create [io+cpu] task as a runnable and invoke them using a single executor service
* create futures and let it be managed by executor service
* single executor service for both cpu and io bound tasks
* control count of threads to 16 using the formula cpu_count * (1 + wait_time/service_time)


| Sno | Run Time (ms) |
|-----|---------------|
| 1   | 2003.96       |
| 2   | 2480.25       |
| 3   | 2308.48       |
| 4   | 2749.67       |
| 5   | 2181.92       |
| Average | 2359.26       |
