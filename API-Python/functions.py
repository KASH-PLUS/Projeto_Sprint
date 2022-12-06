import datetime
from dis import disco
from pickletools import read_int4
import time
import json
from psutil import *
import os
import platform
from database import insert, select
import cpuinfo
from uuid import getnode as get_mac
from random import randint
from matplotlib.animation import FuncAnimation
import matplotlib.pyplot as plt  # Definindo um "apelido" para a biblioteca
import openpyxl
import requests
# from wordCloud import cloud

if os.name == "nt":
    sistema = "Windows"
    codeCleaner = "cls"
else:
    sistema = "Linux"
    codeCleaner = "clear"


def randomSerial():
    num = randint(100000000, 999999999)
    serial = "BRJ" + str(num)
    return serial


def conversao_bytes(valor, tipo):
    if tipo == 1:  # KB
        return f'{valor / 1024: .2f}'
    elif tipo == 2:  # MB'
        return f'{valor / 1024 / 1024: .2f}'
    elif tipo == 3:  # GB
        return f'{valor / 1024 / 1024 / 1024: .2f}'


def verificarComponentes(serialNumber):
    query = f"SELECT idComponente, tipo from tbComponente, tbMaquina where serialNumber = '{serialNumber}' and fkMaquina = tbMaquina.serialNumber order by tipo;"
    componentes = select(query, True)
    idComp = componentes
    return idComp


def divisaoComponentes(serialNumber):
    idComp = verificarComponentes(serialNumber)
    idCpu = []
    idDisco = []
    idRam = []
    for i in idComp:
        if (i[1] == 'cpu'):
            idCpu.append(i[0])
        if (i[1] == 'disco'):
            idDisco.append(i[0])
        if (i[1] == 'ram'):
            idRam.append(i[0])
    return idCpu, idDisco, idRam


def metricasMaximas(idCpu, idDisco, idRam):
    if sistema == "Windows":
        disco = "C:\\"
    else:
        disco = "/"

    for i in idCpu:
        queryCpu = f"UPDATE tbComponente SET metricaMaxima = 100 WHERE idComponente = {i};"
        time.sleep(1)
        insert(queryCpu)

    for i in idDisco:
        queryDisco = f"UPDATE tbComponente SET metricaMaxima = {conversao_bytes(disk_usage(disco).total, 3)} WHERE idComponente = {i};"
        time.sleep(1)
        insert(queryDisco)

    for i in idRam:
        queryRam = f"UPDATE tbComponente SET metricaMaxima = {conversao_bytes(virtual_memory().total, 3)} WHERE idComponente = {i};"
        time.sleep(1)
        insert(queryRam)


def cadastroRede(serialNumber):
    query = f"SELECT * FROM tbRede WHERE fkMaquina = '{serialNumber}';"

    dados = select(query)

    placa = net_if_addrs()

    for i in enumerate(placa):

        if (i[0] == 1):
            minha_placa = i[1]

    placa = net_if_addrs()[minha_placa]

    macAddress = placa[2].address

    ipv4 = placa[0].address
    netmask4 = placa[0].netmask
    ipv6 = placa[1].address[:25]

    if type(dados) == type(None):
        print("eh none")
        query = f"INSERT INTO tbRede(macAddress, ipv4, ipv6, netmask4, fkMaquina) VALUES ('{macAddress}', '{ipv4}', '{ipv6}', '{netmask4}', '{serialNumber}');"
    else:
        macSelect = dados[0]
        queryDelete = f"DELETE FROM tbRegistroRede WHERE fkPlaca = '{macSelect}';"
        query = f"UPDATE tbRede SET macAddress = '{macAddress}', ipv4 = '{ipv4}', ipv6 = '{ipv6}', netmask4 = '{netmask4}' WHERE fkMaquina = '{serialNumber}';"
        insert(queryDelete)

    insert(query)

    return 0


def getMac(serialNumber):
    query = f"SELECT macAddress FROM tbRede WHERE fkMaquina = '{serialNumber}'"

    dados = select(query)

    return dados


def monitorar():
    while (True):
        try:
            os.system(codeCleaner)
            # MEMORIA RAM
            memoriaTotal = f'{conversao_bytes(virtual_memory().total, 3)}GB'
            memoriaDisponivel = f'{conversao_bytes(virtual_memory().available, 3)}GB'
            memoriaEmUsoPerc = virtual_memory().percent
            usoAtualMemoria = f'{conversao_bytes(virtual_memory().used, 3)}GB'

            # CPU
            usoCpuPorc = f'{cpu_percent()}%'
            usoPorCore = cpu_percent(percpu=True)

            # DISCO
            particoes = []
            if sistema == "Windows":
                # identificando partições
                for part in disk_partitions(all=False):
                    if part[0] == "F:\\":
                        break
                    elif part[0] == "E:\\":
                        break
                    else:
                        particoes.append(part[0])
            elif sistema == "Linux":
                particoes.append("/")

            porcentagemOcupados = []
            for j in particoes:
                porcentagemOcupados.append(disk_usage(j).percent)

            # Print parte da memória
            print("\033[1mInformações de memória\033[0m\n")
            print("\033[1mTotal:\033[0m", memoriaTotal)
            print("\033[1mMemória Disponível:\033[0m", memoriaDisponivel)
            print("\033[1mUso atual:\033[0m", usoAtualMemoria)
            print("\033[1mPorcentagem de uso:\033[0m", memoriaEmUsoPerc)

            print("\n", "-" * 100, "\n")

            # Print CPU
            print("\033[1mInformações de CPU\033[0m\n")
            print("\033[1mUso total:\033[0m ", usoCpuPorc)

            print("\033[1mUso por core:\033[0m")

            for i in enumerate(usoPorCore):
                print(f'CPU_{i[0]}: {i[1]}%')

            print("\n", "-" * 100, "\n")

            # Print disco
            print("\033[1mInformações do disco\033[0m\n")
            print("\033[1mPartições encontradas:\033[0m ")

            for i in enumerate(particoes):
                print(f"Uso da partição {i[1]}: {porcentagemOcupados[0]}")

            print("\n\nAperte ctrl + c para retornar")

            time.sleep(1)
        except KeyboardInterrupt:
            return "0"


def info():
    os.system(codeCleaner)

    freqCpu = f'{round(cpu_freq().max, 0)}Mhz'
    qtdCores = cpu_count()
    qtdThreads = cpu_count(logical=False)
    tempoGasto = f"{round(cpu_times().user / 60 / 60, 2)} Horas"
    processador = cpuinfo.get_cpu_info()['brand_raw']

    arquitetura = cpuinfo.get_cpu_info()['arch']
    if arquitetura == "X86_32":
        arquitetura = "32 bits"
    elif arquitetura == "X86_64":
        arquitetura = "64 bits"

        mac = get_mac()

    macString = ':'.join(("%012X" % mac)[i:i+2] for i in range(0, 12, 2))

    versaoSistemas = platform.version()
    memoriaTotal = f'{conversao_bytes(virtual_memory().total, 3)}GB'

    print("\033[1mInformações sobre o computador\033[0m\n\n")
    print("\033[1mSistema Operacional\033[0m", sistema)
    print("\033[1mVersão do sistema\033[0m", versaoSistemas)
    print("\033[1mMac Address\033[0m", macString)
    print("\033[1mArquitetura: \033[0m", arquitetura)
    print("\033[1mProcessador:\033[0m", processador)
    print("\033[1mQuantidade total de núcleos do processador:\033[0m", qtdCores)
    print("\033[1mQuantidade de Threads:\033[0m ", qtdThreads)
    print("\033[1mFrequência do processador:\033[0m ", freqCpu)
    print("\033[1mTotal de RAM :\033[0m", memoriaTotal)
    print("\033[1mTempo gasto pelo usuário no computador desde a última vez em que foi ligado:\033[0m", tempoGasto)

    input("\n\n\033[1mPressione Enter para prosseguir...\033[0m")
    return 0


def insertPeriodico(idCpu, idDisco, idRam, macAddress, serialNumber, urlOpen):
    intervaloInsert = 2  # em segundos

    ultimosRecebidos = net_io_counters().bytes_recv
    ultimosEnviados = net_io_counters().bytes_sent
    ultimosPacotesRecebidos = net_io_counters().packets_recv
    ultimosPacotesEnviados = net_io_counters().packets_sent

    while True:
        
        dataHora = datetime.datetime.now()
        dataHora = datetime.datetime.strftime(dataHora, "%Y-%m-%d %H:%M:%S")

        # Pegando dados de rede

        bytesRecebidos = net_io_counters().bytes_recv
        bytesEnviados = net_io_counters().bytes_sent

        novosRecebidos = bytesRecebidos - ultimosRecebidos
        novosEnviados = bytesEnviados - ultimosEnviados

        mbRecebidos = novosRecebidos / 1024 / 1024
        mbEnviados = novosEnviados / 1024 / 1024

        mbEnviadosTotal = bytesEnviados / 1024 / 1024
        mbRecebidosTotal = bytesRecebidos / 1024 / 1024

        # Pacotes

        pacotesRecebidos = net_io_counters().packets_recv
        pacotesEnviados = net_io_counters().packets_sent

        novosPacotesRecebidos = pacotesRecebidos - ultimosPacotesRecebidos
        novosPacotesEnviados = pacotesEnviados - ultimosPacotesEnviados

        queryRede = f"INSERT INTO tbRegistroRede(fkPlaca, mbEnviados, mbRecebidos, totalEnviado, totalRecebido, pacotesEnviados, pacotesRecebidos, dataHora) VALUES ('{macAddress}', {mbEnviados:.2f}, {mbRecebidos:.2f}, {mbEnviadosTotal:.2f}, {mbRecebidosTotal:.2f}, {novosPacotesEnviados}, {novosPacotesRecebidos},'{dataHora}');"
        insert(queryRede)

        ultimosRecebidos = bytesRecebidos
        ultimosEnviados = bytesEnviados

        ultimosPacotesRecebidos = pacotesRecebidos
        ultimosPacotesEnviados = pacotesEnviados

        #Dados de Registro 

        usoAtualMemoria = conversao_bytes(virtual_memory().used, 3)
        usoCpuPorc = cpu_percent()
        usoAtualMemoriaPorc = virtual_memory().percent
        # usoOciosidade = f'{cpu_times(percpu=False).idle / 3600: .2f}'
        # usoUsuario = f'{cpu_times(percpu=False).user / 3600: .2f}'
        usoOciosidade = cpu_times(percpu=False).idle
        usoUsuario = cpu_times(percpu=False).user

        particoes = []
        if sistema == "Windows":
            for part in disk_partitions(all=False):  # identificando partições
                if part[0] == "F:\\":
                    break
                elif part[0] == "E:\\":
                    break
                else:
                    particoes.append(part[0])
        elif sistema == "Linux":
            particoes.append("/")

        discoOcupado = []
        discoTotal = []
        for j in particoes:
            discoOcupado.append(conversao_bytes(disk_usage(j).used, 3))
            discoTotal.append(conversao_bytes(disk_usage(j).total, 3))

        usoDisco = discoOcupado[0]

        queryTempoUso = f"INSERT INTO tbOciosidade(fkMaquina, usoUsuario, usoOcioso, datahora) VALUES ('{serialNumber}', '{usoUsuario}', '{usoOciosidade}', '{dataHora}');"
        insert(queryTempoUso)

        for i in idCpu:
            queryCpu = f"INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ('{i}', {usoCpuPorc}, '{dataHora}');"
            insert(queryCpu)

        for i in idDisco:
            queryDisco = f"INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ('{i}', {usoDisco}, '{dataHora}');"
            insert(queryDisco)

        for i in idRam:
            queryRam = f"INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ('{i}', {usoAtualMemoria}, '{dataHora}');"
            insert(queryRam)

        # url = "https://api.pipefy.com/graphql"

        # usoCpuPorc = str(usoCpuPorc)
        # usoDisco = str(usoDisco)
        # usoAtualMemoria = str(usoAtualMemoria)
        # dataHora = str(dataHora)

        # if usoCpuPorc > 0:
        #     payload = {("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\", phase_id: \\\"317555598\\\" title:\\\"Alerta de uso de Disco - Máquina "+ serialNumber +" \\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"O uso de seu disco está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\" "+ serialNumber +" \\\"} {field_id: \\\"uso\\\", field_value: \\\" "+ usoCpuPorc +" \\\"} {field_id: \\\"data_hora\\\", field_value: \\\" "+ dataHora +" \\\"} ] } ) {clientMutationId card {id title }}}\"}")}
        #     headers = {
        #         "accept": "application/json",
        #         "Authorization": "sha512-lLFRPmsF5Wq2e3Y++70j7xewpYmBAvJBP2hm6Wcf7hqv6tl5T2CS8XNJ+vm/ZV0oeVjJ+SgshtS+6CQtRpXRBA==?Ufww",
        #         "content-type": "application/json"
        #     }
        #     response = requests.post(url, json=payload, headers=headers)

        # if usoDisco > 0:
        #     payload = {("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\", phase_id: \\\"317566487\\\" title:\\\"Alerta de uso de Disco - Máquina "+ serialNumber +" \\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"O uso de seu disco está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\" "+ serialNumber +" \\\"} {field_id: \\\"uso\\\", field_value: \\\" "+  usoDisco +" GB\\\"} {field_id: \\\"data_hora\\\", field_value: \\\" "+  dataHora +"\\\"} ] } ) {clientMutationId card {id title }}}\"}")}
        #     headers = {
        #         "accept": "application/json",
        #         "Authorization": "sha512-lLFRPmsF5Wq2e3Y++70j7xewpYmBAvJBP2hm6Wcf7hqv6tl5T2CS8XNJ+vm/ZV0oeVjJ+SgshtS+6CQtRpXRBA==?Ufww",
        #         "content-type": "application/json"
        #     }
        #     response = requests.post(url, json=payload, headers=headers)

        # if usoAtualMemoria > 0:
        #     payload = {("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\", phase_id: \\\"317574217\\\" title:\\\"Alerta de uso de Ram - Máquina "+ serialNumber +" \\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"O uso de seu disco está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\" "+ serialNumber +" \\\"} {field_id: \\\"uso\\\", field_value: \\\" "+  usoAtualMemoria +" GB\\\"} {field_id: \\\"data_hora\\\", field_value: \\\" "+  dataHora +" \\\"} ] } ) {clientMutationId card {id title }}}\"}")}
        #     headers = {
        #         "accept": "application/json",
        #         "Authorization": "sha512-lLFRPmsF5Wq2e3Y++70j7xewpYmBAvJBP2hm6Wcf7hqv6tl5T2CS8XNJ+vm/ZV0oeVjJ+SgshtS+6CQtRpXRBA==?Ufww",
        #         "content-type": "application/json"
        #     }
        #     # Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQyNTF9fQ.BDmwgdA6MjMvUaD3x6l34bBKpv9-8epdwzgHOwIOV_zljVHNf1lefNgHg0--ZA_vLjSVT2cFwpwOmiIcPwqfQw
        #     response = requests.post(url, json=payload, headers=headers)
        # # SITE PARA PEGAR ID DE CADA FIELD DO CARD PIPEFY
        # # https://app.pipefy.com/graphiql
        # url = "https://api.pipefy.com/graphql"

        # data = {"query": "mutation{ createCard( input: { pipe_id: \"302821637\" fields_attributes: [ {field_id: \"aviso\", field_value: \"Sua CPU está fora do ideal\"} {field_id: \"serial_number\", field_value: \"AQUI VIRÁ O SERIAL NUMBER\"} {field_id: \"uso\", field_value: \"AQUI VIRÁ O USO DA CPU\"} {field_id: \"data_hora\", field_value: \"08/11/2022 00:00\"} ] } ) {clientMutationId card {id title }}}"}

        # headers = {
        #     "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQwMDV9fQ.zV0vqSFmSDOMiHXQ2QsOhfjt_khTh5EJ_0myF0dbD8dzebwTL-nJ2wo7HR-sIrG6mkR0RdZV7uEIdY-c4aUfww",
        #     "Content-Type": "application/json"}

        # response = requests.request("POST", url, json=data, headers=headers)

        # print(response)

        # print(response.text, "oi")

        # Temperatura
        if os.name == "nt":
            capturaTemp(serialNumber, urlOpen)
        else:
            # Temperatura - Linux
            tempInfo = sensors_temperatures()
            tempAtual = int(tempInfo['coretemp'][0][1])

            #Clock - Linux
            clock = int(cpu_freq().current)

            query = f"INSERT INTO tbTemperatura(fkMaquina, tempAtual, clock, dataHora) VALUES ('{serialNumber}', '{tempAtual}', '{clock}', '{dataHora}');"
            insert(query)

        # Processos
        listaProcessos = []

        for proc in process_iter():
            infoProc = proc.as_dict(['name', 'cpu_percent', 'memory_percent'])
            if infoProc['cpu_percent'] > 0 and infoProc['name'] != 'System Idle Process':
                listaProcessos.append(infoProc)

        def func(e):
            return e['cpu_percent']

        listaProcessos.sort(key=func, reverse=True)

        for proc in listaProcessos[:1]:
            nomeProcesso = proc['name']
            cpuProcesso = proc['cpu_percent']
            ramProcesso = round(proc['memory_percent'], 2)

            queryProc = f"INSERT INTO tbProcesso(fkMaquina, processo, usoCpu, usoRam, dataHora) VALUES ('{serialNumber}', '{nomeProcesso}', '{cpuProcesso}', '{ramProcesso}', '{dataHora}');"
            insert(queryProc)

        time.sleep(intervaloInsert)


def capturaTemp(serialNumber, urlOpen):
    try:
        url = urlOpen + "data.json"
        req = requests.get(url)
        jsonText = req.text.encode("utf8")
        data = json.loads(jsonText)

        tempDados = int(data["Children"][0]["Children"][0]
                        ["Children"][1]["Children"][0]["Value"][:2])
        clockDado = int(data["Children"][0]["Children"][0]
                        ["Children"][0]["Children"][1]["Value"][:4])

        dataHora = datetime.datetime.now()
        dataHora = datetime.datetime.strftime(dataHora, "%Y-%m-%d %H:%M:%S")
        query = f"INSERT INTO tbTemperatura(fkMaquina, tempAtual, clock, dataHora) VALUES ('{serialNumber}', '{tempDados}', '{clockDado}', '{dataHora}');"
        insert(query)
    except:
        print('Erro ao capturar a temperatura')
        print('Por favor ligue o open hardware monitor na url:')
        print(url + '\n\n')


def relatorio():
    os.system(codeCleaner)

    hora = datetime.datetime.now()
    with open('DadosMaquina.txt', 'w', encoding='utf-8') as arquivo:
        arquivo.write(
            "Data e hora do momento que foi salvo os dados:\n" + str(hora))

    versaoSistemas = platform.version()
    arquitetura = cpuinfo.get_cpu_info()['arch']
    if arquitetura == "X86_32":
        arquitetura = "32 bits"
    elif arquitetura == "X86_64":
        arquitetura = "64 bits"

        mac = get_mac()
    macString = ':'.join(("%012X" % mac)[i:i+2] for i in range(0, 12, 2))
    tempoGasto = f"{round(cpu_times().user / 60 / 60, 2)} Horas"
    processador = cpuinfo.get_cpu_info()['brand_raw']
    with open('DadosMaquina.txt', 'a', encoding='utf-8') as arquivo:
        arquivo.write("\n\n━━━━━ Informações do computador ━━━━━\n\nSistema operacional: {}\nVersão do sistema: {}\nMac Address: {}\nArquiterura: {}\nProcessador: {}\nTempo gasto do computador desde a última vez em que foi ligado: {}\n".format(
            sistema, versaoSistemas, macString, arquitetura, processador, tempoGasto))

    memoriaTotal = f'{conversao_bytes(virtual_memory().total, 3)}GB'
    memoriaDisponivel = f'{conversao_bytes(virtual_memory().available, 3)}GB'
    usoAtualMemoria = f'{conversao_bytes(virtual_memory().used, 3)}GB'
    memoriaEmUsoPerc = virtual_memory().percent
    with open('DadosMaquina.txt', 'a', encoding='utf-8') as arquivo:
        arquivo.write("\n━━━━━ MEMÓRIA RAM ━━━━━\n\nMemória total: {} \nMemória disponivel: {} \nUso atual: {} \nPorcentagem de uso: {}%\n".format(
            memoriaTotal, memoriaDisponivel, usoAtualMemoria, memoriaEmUsoPerc))

    freqCpu = float(round(cpu_freq().current, 0))
    usoCpuPorc = f'{cpu_percent()}%'
    usoPorCore = cpu_percent(percpu=True)
    qtdCores = cpu_count()
    qtdThreads = cpu_count(logical=False)
    with open('DadosMaquina.txt', 'a', encoding='utf-8') as arquivo:
        arquivo.write("\n━━━━━ CPU ━━━━━\n\nUso total: {}\nFrequência da CPU: {}Mhz\nQuantidade de núcleos: {}\nQuantidade de Threads: {}\nUso por core: {}\n".format(
            usoCpuPorc, freqCpu, qtdCores, qtdThreads, usoPorCore))

    particoes = []
    if sistema == "Windows":
        for part in disk_partitions(all=False):  # identificando partições
            if part[0] == "F:\\":
                break
            elif part[0] == "E:\\":
                break
            else:
                particoes.append(part[0])
    elif sistema == "Linux":
        particoes.append("/")

    porcentagemOcupados = []
    for j in particoes:
        porcentagemOcupados.append(disk_usage(j).percent)
    with open('DadosMaquina.txt', 'a', encoding='utf-8') as arquivo:
        arquivo.write("\n━━━━━ Disco ━━━━━\n\nPartições: {} \nPorcentagem ocupada de cada partição: {}\n".format(
            particoes, porcentagemOcupados))

    print('Sucesso!!\n\nSeus dados foram salvos em um relatório chamado DadosMaquina.txt\n')
    input("\nPressione Enter para voltar ao menu...\n")
    return "0"


def plotar():

    # o def é a forma que você define uma função em python, com uma sequência de instruções que podem ser solicitadas mais de uma vez sem a necessidade de repetição de cód
    def definirGraficos(frame):

        dispositivos = disk_partitions()

        # adicionando os valores capturados pelo psutil na lista valores
        consumoRAM.append(virtual_memory()[2])
        consumoRAM.remove(consumoRAM[0])  # remove o primeiro valor da lista
        # adicionando os valores capturados pelo psutil na lista valores
        consumoCPU.append(cpu_percent(interval=None))
        consumoCPU.remove(consumoCPU[0])  # remove o primeiro valor da lista

        graficosRAM.cla()  # limpa os eixos
        graficosRAM.plot(consumoRAM, color='#b449de')  # plota o gráfico
        # marcador (bolinha) na posição atual do gráfico
        graficosRAM.scatter(len(consumoRAM) - 1,
                            consumoRAM[-1], color='#b449de')
        graficosRAM.title.set_text(
            f'Consumo de RAM - {consumoRAM[-1]}%')  # título do gráfico
        graficosRAM.set_ylim(0, 100)  # limite do eixo y

        graficosCPU.cla()  # limpa os eixos
        graficosCPU.plot(consumoCPU, color='#49a7de')  # plota o gráfico
        # marcador (bolinha) na posição atual do gráfico
        graficosCPU.scatter(len(consumoCPU) - 1,
                            consumoCPU[-1], color='#49a7de')
        graficosCPU.title.set_text(
            f'Consumo de CPU - {consumoCPU[-1]}%')  # título do gráfico
        graficosCPU.set_ylim(0, 100)  # limite do eixo y

        # Criando um sistema que detecata quantas unidades de armazenamento tem na sua máquina e gerando um gráfico do tipo Pie = Torta/Pizza com a % de disco espaço e de espaço disponível
        i = 3
        # setando as cores que serão utilizadas no gráfico para ficarem estáticas.
        cores = '#a5a8a8', '#55cfed'

        if sistema == "Windows":
            for dispositivo in dispositivos:

                if dispositivo.device == "F:\\":
                    break

                # Capturando a capacidade total de armazenamento do disco
                armzTotalDisco = round(
                    (disk_usage(f'{dispositivo.device}')[0]) / (10**9), 2)
                # Capturando o espaço usado do disco
                espacoUsadoDisco = round(
                    (disk_usage(f'{dispositivo.device}')[1]) / (10**9), 2)
                # Capturando o espaço disponível do disco
                espacoLivreDisco = round(
                    (disk_usage(f'{dispositivo.device}')[2]) / (10**9), 2)

                # Definindo as lavels (Textos) da legenda
                labels = f'Espaço Usado - {espacoUsadoDisco} Gb', f'Espaço Disponível - {espacoLivreDisco} Gb'
                # Definindo os tamanhos do gráfico de pizza em %
                sizes = [((espacoUsadoDisco/armzTotalDisco)*100),
                         ((espacoLivreDisco/armzTotalDisco)*100)]

                # Plotando o gráfico de pizza nas posições corretas da janela de gráficos
                graficosUnidArmz = plt.subplot(2, 2, i)
                # Setando as configurações do gráfico, como as medidas com arredondamento de 1 casa decimal, o ângulo de inicio do gráfico e suas cores.
                graficosUnidArmz.pie(
                    sizes, autopct='%1.1f%%', startangle=0, colors=cores)
                graficosUnidArmz.title.set_text(
                    f'Unidade - {dispositivo.device}')  # título do gráfico
                # Setando as configurações da legenda, como os títulos e sua posição.
                graficosUnidArmz.legend(
                    labels, loc="best", bbox_to_anchor=((0.55, -0.5, 0.5, 0.5)))
                # Definindo a proporção de forma do gráficos para que as unidades de dados sejam as mesmas em todas as direções.
                graficosUnidArmz.axis('equal')

                i = i+1
        else:
            # Capturando a capacidade total de armazenamento do disco
            armzTotalDisco = round((disk_usage('/')[0]) / (10**9), 2)
            # Capturando o espaço usado do disco
            espacoUsadoDisco = round((disk_usage('/')[1]) / (10**9), 2)
            # Capturando o espaço disponível do disco
            espacoLivreDisco = round((disk_usage('/')[2]) / (10**9), 2)

            # Definindo as lavels (Textos) da legenda
            labels = f'Espaço Usado - {espacoUsadoDisco} Gb', f'Espaço Disponível - {espacoLivreDisco} Gb'
            # Definindo os tamanhos do gráfico de pizza em %
            sizes = [((espacoUsadoDisco/armzTotalDisco)*100),
                     ((espacoLivreDisco/armzTotalDisco)*100)]

            # Plotando o gráfico de pizza nas posições corretas da janela de gráficos
            graficosUnidArmz = plt.subplot(2, 2, 3)
            # Setando as configurações do gráfico, como as medidas com arredondamento de 1 casa decimal, o ângulo de inicio do gráfico e suas cores.
            graficosUnidArmz.pie(sizes, autopct='%1.1f%%',
                                 startangle=0, colors=cores)
            graficosUnidArmz.title.set_text(
                f'Unidade - /')  # título do gráfico
            # Setando as configurações da legenda, como os títulos e sua posição.
            graficosUnidArmz.legend(
                labels, loc="best", bbox_to_anchor=((0.55, -0.5, 0.5, 0.5)))
            # Definindo a proporção de forma do gráficos para que as unidades de dados sejam as mesmas em todas as direções.
            graficosUnidArmz.axis('equal')

    # cria uma lista com 10 zeros, esta lista será utilizada no eixo y gráfico
    consumoRAM = [0] * 10
    consumoCPU = [0] * 10

    # propriedades dos gráficos
    # cria a janela com o tamanho e a cor
    telaPrincipal = plt.figure(figsize=(7, 6), facecolor='#EEE')
    # criando os gráficos de CPU e RAM dentro da janela
    graficosRAM = plt.subplot(321)
    graficosCPU = plt.subplot(322)

    # tira a visualização dos dados do eixo x
    graficosRAM.axes.get_xaxis().set_visible(False)
    graficosRAM.set_facecolor('#DDD')  # define a cor do gráfico

    # tira a visualização dos dados do eixo x
    graficosCPU.axes.get_xaxis().set_visible(False)
    graficosCPU.set_facecolor('#DDD')  # define a cor do gráfico

    # função para chamar em loop a função definirGraficos em um intervalo de 1s ou 1000ms (milissegundos)
    animacaoGeral = FuncAnimation(
        telaPrincipal, definirGraficos, interval=1000)

    # exibe a tela principal com os gráficos
    plt.show()

    return "0"


def arquivoCSV():
    os.system(codeCleaner)

    hora = datetime.datetime.now()
    memoriaTotal = float(conversao_bytes(virtual_memory().total, 3))
    memoriaDisponivel = float(conversao_bytes(virtual_memory().available, 3))
    usoAtualMemoria = float(conversao_bytes(virtual_memory().used, 3))
    usoCpuPorc = float(cpu_percent())
    freqCpu = float(round(cpu_freq().current, 0))

    particoes = []
    if sistema == "Windows":
        for part in disk_partitions(all=False):
            if part[0] == "F:\\" or part[0] == "E:\\":
                break
            else:
                particoes.append(part[0])
    elif sistema == "Linux":
        particoes.append("/")

    porcentagemOcupados = []
    for j in particoes:
        porcentagemOcupados.append(disk_usage(j).percent)

    book = openpyxl.Workbook()
    book.create_sheet('Simulação')
    page = book['Simulação']
    page.append(['Momento:', hora])
    page.append(['Máquina', 'Memória total (GB)', 'Memória disponível (GB)', 'Uso atual da memória (GB)',
                'Uso total da CPU (%)', 'Frequência da CPU', 'Partições', 'Porcentagem ocupada de cada partição'])
    page.append(['Máquina 1:', memoriaTotal, memoriaDisponivel, usoAtualMemoria,
                usoCpuPorc, freqCpu, str(particoes), str(porcentagemOcupados)])
    page.append(['Máquina 2:', memoriaTotal * 0.9, memoriaDisponivel * 0.9, usoAtualMemoria *
                0.9, usoCpuPorc * 1.2, freqCpu * 0.9, str(particoes), str(porcentagemOcupados)])
    page.append(['Máquina 3:', memoriaTotal * 1.4, memoriaDisponivel * 1.4, usoAtualMemoria *
                1.4, usoCpuPorc * 0.9, freqCpu * 1.5, str(particoes), str(porcentagemOcupados)])
    page.append(['Máquina 4:', memoriaTotal * 0.7, memoriaDisponivel * 0.7, usoAtualMemoria *
                0.7, usoCpuPorc * 2, freqCpu * 1.4, str(particoes), str(porcentagemOcupados)])
    page.append(['Máquina 5:', memoriaTotal * 1.8, memoriaDisponivel * 1.8, usoAtualMemoria *
                1.8, usoCpuPorc * 1.8, freqCpu * 2, str(particoes), str(porcentagemOcupados)])

    book.save('SimulacaoCaixas.csv')

    print('Sucesso!!\n\nSeus dados foram salvos em um relatório chamado SimulacaoCaixas.csv\n')
    input("\nPressione Enter para voltar ao menu...\n")
    return "0"
