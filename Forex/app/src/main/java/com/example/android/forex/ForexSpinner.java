package com.example.android.forex;

/**
 * Created by bhupeshr on 1/6/2017.
 */

public class ForexSpinner {

    public ForexSpinner(String symbol){
        this.symbol = symbol;
    }

    public ForexSpinner(String symbol, String name){
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return symbol + " - " + name;
    }

    private String symbol;
    private String name;

}
