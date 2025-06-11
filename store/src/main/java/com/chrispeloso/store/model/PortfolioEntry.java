package com.chrispeloso.store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PortfolioEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;

    private int quantity;

    public PortfolioEntry(){ }

    public PortfolioEntry(String symbol, int quantity){
        this.symbol = symbol;
        this.quantity = quantity;
    }

    public long getId() { return this.id; }

    public String getSymbol() { return this.symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public int getQuantity() {return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
