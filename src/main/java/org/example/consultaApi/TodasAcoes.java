package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.AcoesStock;
import org.example.dadosApi.DadosAcoes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class TodasAcoes {

    private static Scanner teclado = new Scanner(System.in);

    public static void allStocks() throws IOException, InterruptedException {
        TodasAcoes newAcoes = new TodasAcoes();
        AcoesStock meuEstoque = newAcoes.consultaAcoesStock();
        int quantidade = 0;
        for (String acao : meuEstoque.getStocks()) {
            System.out.println("Informações da ação " + acao + ":");
            for (DadosAcoes.Resultado resultado : newAcoes.getDadosAcoes(acao).getResultados()) {
                System.out.println(resultado.toString());
            }
            quantidade++;
        }

        System.out.println(quantidade);
        System.out.println("Fim do loop For");
    }
    public AcoesStock consultaAcoesStock() throws IOException, InterruptedException {
        String endereco = "https://brapi.dev/api/available";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);
        Gson gson = new Gson();
        AcoesStock estoque = gson.fromJson(json, AcoesStock.class);
        System.out.println(estoque);
        return estoque;
    }

    DadosAcoes getDadosAcoes(String acao) throws IOException, InterruptedException {
        String endereco = "https://brapi.dev/api/quote/" + acao;
        //System.out.println("seilaaaaaaaa  " + acao);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        DadosAcoes dadosAcoes = gson.fromJson(json, DadosAcoes.class);
        return dadosAcoes;
    }

}
