package org.example.consultaApi;


import org.example.dadosApi.AcoesStock;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AcoesStockParcial {

    private static Scanner teclado = new Scanner(System.in);
    public static void acoesAvailableParcial() throws IOException, InterruptedException {
        AcoesStockParcial newAcoesAval = new AcoesStockParcial();
        AcoesStock meuEstoque = newAcoesAval.acoesParciais();

    }

    public AcoesStock acoesParciais() throws IOException, InterruptedException {
        System.out.println("Informe uma acao, nao precisa ser o nome inteiro");
        var acao = teclado.next();
        String endereco = "https://brapi.dev/api/available?search=" + acao;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        AcoesStock estoque = gson.fromJson(json, AcoesStock.class);
        System.out.println(estoque);
        return estoque;
    }
}
