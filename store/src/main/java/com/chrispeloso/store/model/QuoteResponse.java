package com.chrispeloso.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {

    //region Parameters
    @JsonProperty("c")
    private double currentPrice;   //  current price

    @JsonProperty("h")
    private double highPrice;   //  high price

    @JsonProperty("l")
    private double lowPrice;   //  low price

    @JsonProperty("o")
    private double openPrice;   //  open price

    @JsonProperty("pc")
    private double previousClose;  //  previous close

    private String t;   //  timestamp
    //endregion

    //region Getters/Setters
    public double getC() {return currentPrice;}
    public void setC(double c) {this.currentPrice = c;}

    public double getH() {return highPrice;}
    public void setH(double h) {this.highPrice = h;}

    public double getL() {return lowPrice;}
    public void setL(double l) {this.lowPrice = l;}

    public double getO() {return openPrice;}
    public void setO(double open) {this.openPrice = open;}

    public double getPc() {return previousClose;}
    public void setPc(double pc) {this.previousClose = pc;}

    public String getT() { return t; }
    public void setT(String t) {this.t = t;}
    //endregion
}
