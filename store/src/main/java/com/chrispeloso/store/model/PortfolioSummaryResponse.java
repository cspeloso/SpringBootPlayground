package com.chrispeloso.store.model;

import java.util.List;

public class PortfolioSummaryResponse {
    private List<StockHolding> holdings;

    private double totalValue;

    public PortfolioSummaryResponse(List<StockHolding> holdings, double totalValue){
        this.holdings = holdings;
        this.totalValue = totalValue;
    }

    public List<StockHolding> getHoldings() {return holdings;}
    public void setHoldings(List<StockHolding> holdings) {this.holdings = holdings;}

    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }

}
