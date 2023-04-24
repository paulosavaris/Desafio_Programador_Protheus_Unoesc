package org.example.DAO;

import org.example.dadosApi.DadosAcoes;
import org.example.dadosApi.DadosAcoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CotacaoDAO {

    private Connection conexao;

    public CotacaoDAO(Connection connection) {
        this.conexao = connection;
    }

    public void insertCotacoes(DadosAcoes.Resultado resultado) {
       // System.out.println("Inserindo cotação para a ação " + resultado.getSimbolo());
       // System.out.println(resultado.getCotacao());
        String sql = "INSERT INTO cotacao(idacao, cotacao, valormercado, volumetransacoes, moeda, data) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            // Obter o idAcao correspondente ao símbolo informado
            String sqlConsulta = "SELECT idacao FROM acao WHERE simbolo = ?";
            PreparedStatement pstmtConsulta = conexao.prepareStatement(sqlConsulta);
            pstmtConsulta.setString(1, resultado.getSimbolo());
            ResultSet rs = pstmtConsulta.executeQuery();
            if (rs.next()) {
                int idacao = rs.getInt("idAcao");
                // Inserir a nova cotação na tabela cotacao, associada à ação correspondente
                pstmt.setInt(1, idacao);
                pstmt.setFloat(2, resultado.getCotacao());
                pstmt.setFloat(3, resultado.getValorDeMercado());
                pstmt.setFloat(4,resultado.getVolumeDeTransacoes());
                pstmt.setString(5, resultado.getMoeda());
                pstmt.setTimestamp(6, resultado.getData());
                pstmt.execute();
            } else {
                throw new IllegalArgumentException("Ação não encontrada: " + resultado.getSimbolo());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
