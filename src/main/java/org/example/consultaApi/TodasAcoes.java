package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.AcoesStock;

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


}
