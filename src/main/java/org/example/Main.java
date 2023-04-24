package org.example;

import org.example.consultaApi.AcoesStockParcial;
import org.example.consultaApi.InformacoesDasAcoes;
import org.example.consultaApi.TodasAcoes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        var opcao = exibirMenu();
        while (opcao != 7){
            try{
                switch (opcao){
                    case 1: consultarInformacaoAcoes();
                        break;
                    case 2: consultarTodasAcoes();
                        break;
                    case 3: consultarAcoesParciais();
                        break;
                }
            } catch (Exception e){
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizada a aplicacao.");
    }

    private static int exibirMenu() {
        System.out.println("""
                API brapi - ESCOLHA UMA OPÇÃO:
                1 - Consultar dados das acoes, individual ou em grupo.
                2 - Consulta todas as acoes ( recomendavel rodar a cada inicializacao do programa).
                3 - Consulta grupos de acoes com as mesma letras informadas.
                4 - Exportar Arquivo CSV.
                7 - Sair / finalizar aplicação 
                """);
        return teclado.nextInt();
    }

    private static void consultarInformacaoAcoes() throws IOException, InterruptedException, SQLException {

        InformacoesDasAcoes DadosAcoes = new InformacoesDasAcoes();
        DadosAcoes.consultaDadosAcoes();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void consultarTodasAcoes() throws IOException, InterruptedException {

        TodasAcoes stockAll = new TodasAcoes();
        stockAll.allStocks();


        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");teclado.next();
    }
    private static void consultarAcoesParciais() throws IOException, InterruptedException {

        AcoesStockParcial consultarStock = new AcoesStockParcial();
        consultarStock.acoesAvailableParcial();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");teclado.next();
    }

}