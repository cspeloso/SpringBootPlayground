package com.chrispeloso.store.model;

public class StockHolding {

    private String symbol;
    private int quantity;
    private double currentPrice;
    private double value;

    public StockHolding(String symbol, int quantity, double currentPrice, double value){
        this.symbol = symbol;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        this.value= value;
    }

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getCurrentPrice() {return currentPrice;}
    public void setCurrentPrice(double currentPrice) {this.currentPrice = currentPrice;}

    public double getValue() {return value;}
    public void setValue(double value) {this.value = value;}
}
