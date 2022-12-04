/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author User
 */
public class Conexao {

    private JdbcTemplate connection;

    public JdbcTemplate getConnection() {
        return connection;
    }

    public void setConnection(JdbcTemplate connection) {
        this.connection = connection;
    }


    public Conexao() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbkashplus");
        dataSource.setUsername("kashUser");
        dataSource.setPassword("kash");

        this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConexao() {
        return connection;
    }
}
