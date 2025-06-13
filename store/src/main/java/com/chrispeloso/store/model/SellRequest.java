package com.chrispeloso.store.model;

public class SellRequest {

    //#region Parameters
    //  stock symbol to sell
    private String symbol;

    //  # of shares to sell
    private int quantity;
    //#endregion

    //#region Constructors
    public SellRequest(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }
    //#endregion

    //#region Getters / Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //#endregion
}
