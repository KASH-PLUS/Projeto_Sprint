from psutil import *
import time


def get_size(bytes):

    for unit in ['', 'K', 'M', 'G', 'T', 'P']:
        if bytes < 1024:
            return f"{bytes:.2f}{unit}B"
        bytes /= 1024


io = net_io_counters()
# extract the total bytes sent and received
bytes_sent, bytes_recv = io.bytes_sent, io.bytes_recv

placa = net_if_addrs()

for i in enumerate(placa):

    if (i[0] == 1):
        minha_placa = i[1]

placa = net_if_addrs()[minha_placa]

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

mbytesEnviados = net_io_counters().bytes_sent
mbytesRecebidos = net_io_counters().bytes_recv

mbytesEnviados = mbytesEnviados / 1024 / 1024
mbytesRecebidos = mbytesRecebidos / 1024 / 1024

print("Bytes Enviados: " + str(mbytesEnviados))
print("Bytes Recebidos: " + str(mbytesRecebidos))

print("-"*50)

ultimosRecebidos = net_io_counters().bytes_recv
ultimosEnviados = net_io_counters().bytes_sent
total = ultimosRecebidos + ultimosEnviados

while True:

    bytesRecebidos = net_io_counters().bytes_recv
    bytesEnviados = net_io_counters().bytes_sent

    novosRecebidos = bytesRecebidos - ultimosRecebidos
    novosEnviados = bytesEnviados - ultimosEnviados

    mbRecebidos = novosRecebidos / 1024 / 1024
    mbEnviados = novosEnviados / 1024 / 1024
 
    print(
        f"Enviados: {mbEnviados:.2f}MB    Recebidos:{mbRecebidos:.2f}MB")

    ultimosRecebidos = bytesRecebidos
    ultimosEnviados = bytesEnviados
    

    time.sleep(1)
