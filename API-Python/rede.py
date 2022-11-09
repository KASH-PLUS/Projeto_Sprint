from psutil import *

print(net_if_stats())

print("-" * 50)                 

print(net_if_addrs())

print("-" * 50)

print(net_io_counters())