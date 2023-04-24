package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.DadosAcoes;
import org.example.postgres.InsercaoBanco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Scanner;

public class InformacoesDasAcoes {
    private static Scanner teclado = new Scanner(System.in);

    public String consultaDadosAcoes() throws IOException, InterruptedException, SQLException {
        System.out.println("Informe uma ou mais acoes separadas por vírgula (ex: PETR4,MGLU3,B3SA3): ");
        var acao = teclado.next();
        System.out.println("\n");
        String endereco = "https://brapi.dev/api/quote/" + String.join("%2C", acao.split(","));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = reponse.body();
        //System.out.println(json);
        Gson gson = new Gson();
        DadosAcoes minhaAcao = gson.fromJson(json, DadosAcoes.class);
        InsercaoBanco inserteBD = new InsercaoBanco();

        for (DadosAcoes.Resultado resultado : minhaAcao.getResultados()) { // percorre o Array para poder trazer mais de uma acao
            System.out.println("TEste 1  " + resultado.toString());
            inserteBD.inserirAcao(resultado);
            inserteBD.inserirCotacao(resultado);
        }
        System.out.println("Ações salvas no banco de dados");

        return acao;
    }

}
