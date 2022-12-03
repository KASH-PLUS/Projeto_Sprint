/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jframekash;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

/**
 *
 * @author vinicius
 */
public class ThreadInsert extends Thread {

    private String serialNumber;

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
                Thread.sleep(30000); // tempo de cada insert
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

        } else if (tipo.equals("cpu")) {
            cursor.update(String.format("INSERT INTO tbRegistro(fkComponente, registro, dataHora) VALUES ( '%s', '%.2f', '%s' )", fkComponente, usoCpu, dataHora));
            System.out.println("Insert realizado");
            
            Sistema sistema = looca.getSistema();
            
            if (!(sistema.getSistemaOperacional().equalsIgnoreCase("Windows"))) {
                insertTemp();
            }
        }
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
