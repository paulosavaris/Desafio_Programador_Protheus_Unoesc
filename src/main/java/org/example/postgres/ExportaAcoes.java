package org.example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExportaAcoes {

    private static Scanner teclado = new Scanner(System.in);
    public void exportaAcoes() {

        Connection conexao = recuperaConexao();
        System.out.println("Informe uma acao, nao precisa ser o nome inteiro.");
        System.out.println("Exemplo: PETR4 ou pet ou cog");
        var acao = teclado.next();
        List<String> acoesList = Arrays.asList(acao.split(","));
        String acoes = ("'" + String.join("', '", acoesList) + "'").toUpperCase();

        //System.out.println(acoes);

        System.out.println("escreva o nome do arquivo que gostaria de importar as acoes");
        System.out.println("O arquivo sera salvo no diretorio da aplicao ../ApiUnoesc");
        String arquivo = teclado.next();

        String dir = System.getProperty("user.dir").replaceAll("\\\\", "/");
        String caminhoSql = dir + "/" + arquivo + ".csv";

        String sql = "COPY "
                + "(SELECT a.simbolo as \"Símbolo\", a.nome as Nome, "
                + "to_char(c.cotacao, 'FM999G999D9999' ) AS \"Cotação\", "
                + "to_char(c.valormercado::numeric, 'FM999G999G999G999G900D09')  AS \"Valor de Mercado\", "
                + "to_char(c.volumetransacoes , 'FM999G999G999G999G900D09') AS \"Volume de Transações\", c.moeda as Moeda, "
                + "TO_CHAR(c.\"data\" ,'HH24:MI:SS DD/MM/YYYY') as Data "
                + "FROM acao a "
                + "JOIN (SELECT idacao, MAX(idcotacao) as idcotacao FROM cotacao GROUP BY idacao) cmax ON cmax.idacao = a.idacao "
                + "JOIN cotacao c ON c.idcotacao = cmax.idcotacao "
                + "WHERE UPPER(a.simbolo) in (" + acoes + ") ) "
                + "TO '" + caminhoSql +"' WITH (FORMAT CSV, HEADER, DELIMITER ';' , ENCODING 'UTF-8') ";
        //System.out.println(sql);

        try{
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.execute();
            stm.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection recuperaConexao() {
        try {
            return DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/unoescapi?user=postgres&password=System01");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
