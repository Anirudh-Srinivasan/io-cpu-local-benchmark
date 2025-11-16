# Threads run results

Idea
* Create [io+cpu] task as a runnable and invoke them using a single executor service
* create futures and let it be managed by executor service
* single executor service for both cpu and io bound tasks
* control count of threads to 16 using the formula cpu_count * (1 + wait_time/service_time)


| Sno | Run Time (ms) |
|-----|---------------|
| 1   | 2909.07       |
| 2   | 3236.93       |
| 3   | 2813.64       |
| 4   | 2860.75       |
| 5   | 2986.32       |
| Average | 2961.34       |
