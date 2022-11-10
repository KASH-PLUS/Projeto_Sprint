from psutil import *

placa = net_if_addrs()['eno1']

print(placa)
print("-"*50)

print("Ipv4 Local: " + placa[0].address)
print("Mascara de rede ipv4: " + placa[0].netmask)
print("Ipv6 Local: " + placa[1].address[:25])
print("Mascara de rede ipv6: " + placa[1].netmask) 
print("MAC Address: " + placa[2].address)

print("-"*50)

print(net_io_counters())

print("-"*50)

