/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jframekash;

import banco.CadastroRede;
import banco.Conexao;
import banco.ConexaoAzure;
import banco.Pipefy;
import banco.TbComponente;
import banco.TbComponenteCrud;
import org.springframework.jdbc.core.JdbcTemplate;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.CentralProcessor;

/**
 *
 * @author vinicius
 */
public class ThreadInsert extends Thread {

    private String serialNumber;
    private String macAddress;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    TbComponenteCrud componeteCrud = new TbComponenteCrud();
    ConexaoAzure con = new ConexaoAzure();
    JdbcTemplate cursor = con.getConexao();
    Looca looca = new Looca();

    public ThreadInsert() {

    }

    @Override
    public void run() {
        while (true) {
            try {
                getComponentes();
            } catch (IOException ex) {
                Logger.getLogger(ThreadInsert.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(2000); // tempo de cada insert em milissegundos
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void getComponentes() throws IOException {
        List<TbComponente> componentes = componeteCrud.selecionar(serialNumber);

        for (TbComponente componente : componentes) {
            insertRegistro(componente.getIdComponente(), componente.getTipo());
        }
    }

    private List<Long> obterPacotes() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> redes = hal.getNetworkIFs();
        NetworkIF rede = redes.get(redes.size() - 1);

        Long pacotesRecebidos = rede.getPacketsRecv();
        Long pacotesEnviados = rede.getPacketsSent();

        List<Long> pacotes = List.of(pacotesEnviados, pacotesRecebidos);

        return pacotes;
    }

    private List<Long> obterBytes() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> redes = hal.getNetworkIFs();
        NetworkIF rede = redes.get(redes.size() - 1);

        Long bytesEnviados = rede.getBytesSent();
        Long bytesRecebidos = rede.getBytesRecv();

        List<Long> bytes = List.of(bytesEnviados, bytesRecebidos);

        return bytes;
    }

    private void insertRegistro(Integer fkComponente, String tipo) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dataHora = dateFormat.format(date);

        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Volume> volumes = grupoDeDiscos.getVolumes();

        Double usoCpu = processador.getUso();
        Long longUsoMemoria = memoria.getEmUso();
        Long discoDisponivel = volumes.get(0).getDisponivel();
        Long totalDisco = volumes.get(0).getTotal();

        Long usoDisco = (totalDisco - discoDisponivel) / 1024 / 1024 / 1024;
        double usoMemoria = (double) longUsoMemoria;

        Long longMemoriaTotal = memoria.getTotal();
        double usoMemoriaTotal = (double) longMemoriaTotal;

        usoMemoria = usoMemoria / 1024 / 1024 / 1024;
        usoMemoriaTotal = usoMemoriaTotal / 1024 / 1024 / 1024;

        Double usoMemoriaPercent = usoMemoria / usoMemoriaTotal * 100;

        usoMemoria = usoMemoria / 1024 / 1024 / 1024;
        
        if (usoCpu > 1) {
            Pipefy.criarCard();
        }

        if (tipo.equals("disco")) {
            cursor.update(String.format("INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ( '%s', '%d', '%s' )", fkComponente, usoDisco, dataHora));
            System.out.println("Insert realizado");
        } else if (tipo.equals("ram")) {
            cursor.update(String.format("INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ( '%s', '%.2f', '%s' )", fkComponente, usoMemoria, dataHora));
            System.out.println("Insert realizado");
            if (usoMemoriaPercent > 70) {
                Pipefy.criarCardRam(usoMemoriaPercent, this.serialNumber, date);
            }

        } else if (tipo.equals("cpu")) {
            cursor.update(String.format("INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ( '%s', '%.2f', '%s' )", fkComponente, usoCpu, dataHora));
            System.out.println("Insert realizado");
            if (usoCpu > 70) {
                Pipefy.criarCardCpu(usoCpu, this.serialNumber, date);
            
            Sistema sistema = looca.getSistema();
            
            if (!(sistema.getSistemaOperacional().equalsIgnoreCase("Windows"))) {
                insertTemp();
            }

            insertRede(obterPacotes(), obterBytes());
        }
    }

    private void insertRede(List<Long> pacotes, List<Long> bytes) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> redes = hal.getNetworkIFs();
        NetworkIF rede = redes.get(redes.size() - 1);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dataHora = dateFormat.format(date);

        Long pacotesAtuaisEnviados = rede.getPacketsSent() - pacotes.get(0); // enviados
        Long pacotesAtuaisRecebidos = rede.getPacketsRecv() - pacotes.get(1); // recebidos

        Double mbAtuaisEnviados = (double) rede.getBytesSent() - bytes.get(0);
        Double mbAtuaisRecebidos = (double) rede.getBytesRecv() - bytes.get(1);

        mbAtuaisEnviados = mbAtuaisEnviados / 1024 / 1024;
        mbAtuaisRecebidos = mbAtuaisRecebidos / 1024 / 1024;

        Double totalEnviado = (double) rede.getBytesSent() / 1024 / 1024;
        Double totalRecebidos = (double) rede.getBytesRecv() / 1024 / 1024;

        cursor.update(String.format(Locale.US, "INSERT INTO tbRegistroRede(fkPlaca, mbEnviados, mbRecebidos, totalEnviado, totalRecebido, pacotesEnviados, pacotesRecebidos, dataHora) VALUES ('%s', %.2f, %.2f, %.2f, %.2f, %d, %d, '%s');", this.macAddress, mbAtuaisEnviados, mbAtuaisRecebidos, totalEnviado, totalRecebidos, pacotesAtuaisEnviados, pacotesAtuaisRecebidos, dataHora));

        System.out.println("Insert de rede realizado");
    }

    public void insertTemp() {
        DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dataHora = dateFormat.format(date);
        
        Temperatura temperatura = looca.getTemperatura();
        Double tempDouble = temperatura.getTemperatura();
        Integer temp = tempDouble.intValue();
        
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor(); 
        
        Long clockLong = cpu.getCurrentFreq()[1];
        Double clockDouble = clockLong.doubleValue();
        clockDouble = clockDouble / Math.pow(10, 6);
        Integer clock = clockDouble.intValue();
        
        cursor.update(String.format("INSERT INTO tbTemperatura(fkMaquina, tempAtual, clock, dataHora) VALUES('%s', '%d', '%d', '%s');", serialNumber, temp, clock, dataHora));
        System.out.println("Insert da temperatura realizado");
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
