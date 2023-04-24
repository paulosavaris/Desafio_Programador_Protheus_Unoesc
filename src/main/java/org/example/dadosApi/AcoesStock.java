package org.example.dadosApi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AcoesStock {
    @SerializedName("stocks")
    private ArrayList<String> stocks;

    public ArrayList<String> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<String> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for ( String stock : stocks){
            builder.append(stock).append("\n");
        }
        return builder.toString();
    }
}
