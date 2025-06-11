package com.chrispeloso.store.dto;

public class PortfolioSummaryDTO {

    //region Parameters
    private String symbol;
    private int quantity;
    private double currentPrice;
    private double totalValue;
    //endregion


    public PortfolioSummaryDTO(String symbol, int quantity, double currentPrice){
        this.symbol = symbol;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }


    //region Getters
    public String getSymbol() {return symbol;}
    public int getQuantity() {return quantity;}
    public double getCurrentPrice() {return currentPrice;}
    public double getTotalValue() {return totalValue;}
    //endregion

}
