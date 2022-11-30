from psutil import *
import time

ultimosRecebidos = net_io_counters().packets_recv
ultimosEnviados = net_io_counters().packets_sent
total = ultimosRecebidos + ultimosEnviados

while True:

    pacotesRecebidos = net_io_counters().packets_recv
    pacotesEnviados = net_io_counters().packets_sent

    novosRecebidos = pacotesRecebidos - ultimosRecebidos
    novosEnviados = pacotesEnviados - ultimosEnviados

    print(
        f"Enviados: {novosEnviados}    Recebidos:{novosRecebidos}")

    ultimosRecebidos = pacotesRecebidos
    ultimosEnviados = pacotesEnviados
    

    time.sleep(1)
