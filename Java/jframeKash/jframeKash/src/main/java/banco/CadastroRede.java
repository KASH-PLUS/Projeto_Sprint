/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

import com.mycompany.jframekash.ThreadInsert;
import java.util.Arrays;
import java.util.List;
import org.jfree.chart.plot.ThermometerPlot;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

/**
 *
 * @author vinicius
 */
public class CadastroRede {

    private String serialNumber;
    private SystemInfo si = new SystemInfo();
    private HardwareAbstractionLayer hal = si.getHardware();
    private List<NetworkIF> redes = hal.getNetworkIFs();
    private NetworkIF rede = redes.get(redes.size() - 1);
    private String macAddress;

    public void cadastroRede() {
        TbRede validacao = new TbRede();
        TbRedeCrud redeCrud = new TbRedeCrud();
        ConexaoAzure con = new ConexaoAzure();
        JdbcTemplate cursor = con.getConexao();

        validacao = redeCrud.selecionar(this.serialNumber);

        if (validacao == null) {
            cursor.update(String.format("INSERT INTO tbRede(macAddress, ipv4, ipv6, netmask4, fkMaquina) VALUES ('%s', '%s', '%s', '%s', '%s');", getMac(), getIpv4(), getIpv6(), getNetmask4(), serialNumber));
            System.out.println("Rede cadastrada");
        } else {
            String macSelect = validacao.getMacAddress();
            cursor.update(String.format("DELETE FROM tbRegistroRede WHERE fkPlaca = '%s'", macSelect));
            cursor.update(String.format("UPDATE tbRede SET macAddress = '%s', ipv4 = '%s', ipv6 = '%s' ,netmask4 = '%s' WHERE fkMaquina = '%s';", getMac(), getIpv4(), getIpv6(), getNetmask4(), serialNumber));
            System.out.println("Rede atualizada");
        }

        ThreadInsert insert = new ThreadInsert();
    }

    public String getMac() {
        return rede.getMacaddr();
    }

    private String getIpv4() {
        String[] ipv4A = rede.getIPv4addr();
        String ipv4 = Arrays.deepToString(ipv4A).replace("[", "").replace("]", "");

        return ipv4;
    }

    private String getIpv6() {
        String[] ipv6A = rede.getIPv6addr();
        String ipv6 = Arrays.deepToString(ipv6A).replace("[", "").replace("]", "");

        return ipv6;

    }

    private String getNetmask4() {

        Short[] netmaskS = rede.getSubnetMasks();

        String netmask = Arrays.deepToString(netmaskS).replace("[", "").replace("]", "");

        return netmask;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
