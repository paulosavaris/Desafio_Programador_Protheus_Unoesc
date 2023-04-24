package org.example.postgres;

import org.example.DAO.AcoesDAO;
import org.example.DAO.CotacaoDAO;
import org.example.dadosApi.DadosAcoes;
import org.example.dadosApi.DadosAcoes;

import java.sql.Connection;

public class InsercaoBanco {

    private ConexaoFactory connection;
    public InsercaoBanco(){
        this.connection = new ConexaoFactory();
    }

    public void inserirAcao(DadosAcoes.Resultado resultado) {
        Connection conn = connection.recuperaConexao();
        new AcoesDAO(conn).insertAcoes(resultado);
    }

    public void inserirCotacao(DadosAcoes.Resultado resultado) {
        Connection conn = connection.recuperaConexao();
        new CotacaoDAO(conn).insertCotacoes(resultado);
    }

}
