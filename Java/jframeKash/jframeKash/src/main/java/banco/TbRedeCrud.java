/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vinicius
 */
public class TbRedeCrud {
     public TbRede selecionar(String serial) {

        ConexaoAzure con = new ConexaoAzure();
        JdbcTemplate cursor = con.getConexao();

        List<TbRede> validacao = cursor.query(String.format("SELECT * FROM tbRede WHERE fkMaquina = '%s'", serial), new BeanPropertyRowMapper(TbRede.class));
        for (TbRede rede: validacao) {
            return rede;
        }
        return null;
    }
}
