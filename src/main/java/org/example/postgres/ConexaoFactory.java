package org.example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public Connection recuperaConexao() {
        try {
            return DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/unoescapi?user=postgres&password=System01"); //dados do banco
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
